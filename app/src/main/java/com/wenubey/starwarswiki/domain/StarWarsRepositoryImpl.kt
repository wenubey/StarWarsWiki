package com.wenubey.starwarswiki.domain

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.wenubey.starwarswiki.data.local.entities.CharacterEntity
import com.wenubey.starwarswiki.domain.models.CharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StarWarsRepositoryImpl @Inject constructor(
    private val pager: Pager<Int, CharacterEntity>
): StarWarsRepository {

    override fun getCharacterPagingFlow(query: String): Flow<PagingData<CharacterModel>> {
        return pager.flow
            .map { pagingData ->
                pagingData.filter { character ->
                    character.name!!.contains(query, ignoreCase = true)
                }
                pagingData.map { it.mapToDomainModel() }
            }
    }
}