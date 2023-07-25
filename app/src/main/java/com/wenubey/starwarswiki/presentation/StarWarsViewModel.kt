package com.wenubey.starwarswiki.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.filter
import androidx.paging.map
import com.wenubey.starwarswiki.data.local.entities.CharacterEntity
import com.wenubey.starwarswiki.data.remote.SearchQueryProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class StarWarsViewModel @Inject constructor(
    private val pager: Pager<Int, CharacterEntity>,
    private val searchQueryProvider: SearchQueryProvider
) : ViewModel() {

    var searchQuery = MutableStateFlow("")

    @OptIn(FlowPreview::class)
    val characterPagingFlow =
        searchQuery
            .debounce(500)
            .distinctUntilChanged()
            .flatMapLatest { query ->
                pager.flow
                    .map { pagingData ->
                        pagingData.filter { character ->
                            character.name!!.contains(query, ignoreCase = true)
                        }
                        pagingData.map { it.mapToDomainModel() }
                    }.cachedIn(viewModelScope)
            }

    fun setSearchQuery(query: String) {
        searchQuery.value = query
        searchQueryProvider.setSearchQuery(query)
    }
}