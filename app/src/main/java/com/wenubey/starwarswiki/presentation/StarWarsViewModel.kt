package com.wenubey.starwarswiki.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.wenubey.starwarswiki.data.remote.SearchQueryProvider
import com.wenubey.starwarswiki.domain.StarWarsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class StarWarsViewModel @Inject constructor(
    private val starWarsRepository: StarWarsRepository,
    private val searchQueryProvider: SearchQueryProvider
) : ViewModel() {

    var searchQuery = MutableStateFlow("")

    @OptIn(FlowPreview::class)
    val characterPagingFlow =
        searchQuery
            .debounce(500)
            .distinctUntilChanged()
            .flatMapLatest { query ->
                starWarsRepository.getCharacterPagingFlow(query)
            }.cachedIn(viewModelScope)

    fun setSearchQuery(query: String) {
        searchQuery.value = query
        searchQueryProvider.setSearchQuery(query)
    }
}