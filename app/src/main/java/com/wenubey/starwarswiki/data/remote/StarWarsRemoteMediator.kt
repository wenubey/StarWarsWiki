package com.wenubey.starwarswiki.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.wenubey.starwarswiki.core.getIdFromUrl
import com.wenubey.starwarswiki.data.local.StarWarsDatabase
import com.wenubey.starwarswiki.data.local.entities.CharacterEntity
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class StarWarsRemoteMediator @Inject constructor(
    private val api: StarWarsApi,
    private val db: StarWarsDatabase
): RemoteMediator<Int, CharacterEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        try {
            val page = when(loadType) {
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

            val characters = api.getCharacters(page)

            val characterEntities = characters.results.map { result ->
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
                    }
                )
            }


            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.dao.clearAll()
                }

                db.dao.upsertAll(characters = characterEntities)
            }
            val endOfPaginationReached = characters.next == null
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }
}