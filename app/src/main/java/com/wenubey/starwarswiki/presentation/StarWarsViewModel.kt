package com.wenubey.starwarswiki.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.wenubey.starwarswiki.data.local.entities.CharacterEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class StarWarsViewModel @Inject constructor(
    pager: Pager<Int, CharacterEntity>,
): ViewModel() {




    val characterPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.mapToDomainModel() }
        }
        .cachedIn(viewModelScope)
}