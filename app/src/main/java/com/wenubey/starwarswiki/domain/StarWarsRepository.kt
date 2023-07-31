package com.wenubey.starwarswiki.domain

import androidx.paging.PagingData
import com.wenubey.starwarswiki.domain.models.CharacterModel
import kotlinx.coroutines.flow.Flow

interface StarWarsRepository {
    fun getCharacterPagingFlow(query: String): Flow<PagingData<CharacterModel>>
}