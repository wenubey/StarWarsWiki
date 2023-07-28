package com.wenubey.starwarswiki

import androidx.paging.Pager
import androidx.paging.PagingData
import com.wenubey.starwarswiki.data.local.entities.CharacterEntity
import com.wenubey.starwarswiki.data.remote.SearchQueryProvider
import com.wenubey.starwarswiki.presentation.StarWarsViewModel
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test


class ViewModelTest {

    private lateinit var viewModel: StarWarsViewModel
    private lateinit var searchQueryProvider: SearchQueryProvider
    private lateinit var pager: Pager<Int, CharacterEntity>

    @Before
    fun setUp() {
        // Mock the dependencies
        searchQueryProvider = mockk()

        every { searchQueryProvider.setSearchQuery(any()) } just Runs

        pager = mockk()

        every { pager.flow } returns flowOf(PagingData.empty())

        viewModel = StarWarsViewModel(pager, searchQueryProvider)
    }

    @After
    fun tearDown() {
        // Clean up mocks
        clearAllMocks()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `setSearchQuery should update searchQuery and call searchQueryProvider`() = runTest {
        // Given
        val query = "Luke"

        // When
        viewModel.setSearchQuery(query)

        // Then
        assert(viewModel.searchQuery.value == query)
        coVerify { searchQueryProvider.setSearchQuery(query) }
    }
}