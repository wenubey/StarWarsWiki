package com.wenubey.starwarswiki.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.wenubey.starwarswiki.core.Constants.TAG
import com.wenubey.starwarswiki.core.Constants.UNDEFINED
import com.wenubey.starwarswiki.core.getIdFromUrl
import com.wenubey.starwarswiki.data.local.StarWarsDatabase
import com.wenubey.starwarswiki.data.local.entities.CharacterEntity
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class StarWarsRemoteMediator @Inject constructor(
    private val api: StarWarsApi,
    private val db: StarWarsDatabase
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
        } catch (e: IOException) {
            Log.e(TAG, "IOError: ${e.localizedMessage}")
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            Log.e(TAG, "HTTPError: ${e.message}")
            return MediatorResult.Error(e)
        }
    }
}