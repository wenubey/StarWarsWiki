package com.wenubey.starwarswiki.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.wenubey.starwarswiki.core.getIdFromUrl
import com.wenubey.starwarswiki.data.local.StarWarsDao
import com.wenubey.starwarswiki.data.local.entities.CharacterEntity
import com.wenubey.starwarswiki.data.remote.dto.ListCharacterDto
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class StarWarsRemoteMediator @Inject constructor(
    private val api: StarWarsApi,
    private val dao: StarWarsDao,
    private val imageApi: StarWarsImageApi,
    private val searchQueryProvider: SearchQueryProvider
) : RemoteMediator<Int, CharacterEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastCharacter = state.lastItemOrNull()
                    if (lastCharacter == null) {
                        1
                    } else {
                        (lastCharacter.id / state.config.pageSize) + 1
                    }
                }
            }

            val searchQuery = searchQueryProvider.getSearchQuery()

            val characters = if (searchQuery.isEmpty()) {
                api.getCharacters(page)
            } else {
                api.searchCharacter(searchQuery)
            }
            val characterEntities = getListCharacter(characters, api)

            if (loadType == LoadType.REFRESH) {
                dao.clearAll()
            }
            dao.upsertAll(characters = characterEntities)


            val endOfPaginationReached = characters.next == null
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getListCharacter(characters: ListCharacterDto, api: StarWarsApi): List<CharacterEntity> {
        return characters.results.map { result ->
            result.mapToEntity(
                films = result.films?.map { filmUrl ->
                    api.getFilm(filmUrl.getIdFromUrl())
                },
                homeWorld = api.getPlanet(result.homeWorld.getIdFromUrl()),
                vehicles = result.vehicles?.map { vehicleUrl ->
                    api.getVehicle(vehicleUrl.getIdFromUrl())
                },
                species = result.species?.map { specieUrl ->
                    api.getSpecie(specieUrl.getIdFromUrl())
                },
                starships = result.starships?.map { starshipUrl ->
                    api.getStarship(starshipUrl.getIdFromUrl())
                },
                photoUrl = imageApi.getImageFromId(result.url.getIdFromUrl())
            )
        }
    }
}