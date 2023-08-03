package com.wenubey.starwarswiki

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.wenubey.starwarswiki.data.local.entities.CharacterEntity
import com.wenubey.starwarswiki.data.remote.SearchQueryProvider
import com.wenubey.starwarswiki.domain.StarWarsRepository
import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.presentation.StarWarsViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


@FlowPreview
@OptIn(ExperimentalCoroutinesApi::class)
class ViewModelTest {

    private lateinit var viewModel: StarWarsViewModel
    private lateinit var starWarsRepository: StarWarsRepository
    private lateinit var searchQueryProvider: SearchQueryProvider
    private lateinit var testDispatcher: TestDispatcher
    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        starWarsRepository = mockk(relaxed = true)
        searchQueryProvider = mockk(relaxed = true)

        viewModel = StarWarsViewModel(starWarsRepository, searchQueryProvider)
    }

    @After
    fun tearDown() {
        clearAllMocks()

    }


    @Test
    fun `setSearchQuery should update searchQuery and call searchQueryProvider`() = runTest {
        // Given
        val query = "Luke"

        // When
        viewModel.setSearchQuery(query)

        // Then
        assertEquals(query, viewModel.searchQuery.value)
        coVerify { searchQueryProvider.setSearchQuery(query) }
    }


    @Test
    fun `setSearchQuery should filtered data`() = runTest {
        val initialData = listOf(
            CharacterEntity(name = "Luke Skywalker", id = 1),
            CharacterEntity(name = "Darth Vader", id = 2),
            CharacterEntity(name = "Obi-Wan Kenobi", id = 3),
            CharacterEntity(name = "Princess Leia", id = 4),
        )
        val query = "Luke"
        val filteredData = initialData.filter { it.name!!.contains(query, ignoreCase = true) }
        val pagingData: PagingData<CharacterModel> = PagingData.from(filteredData.map { it.mapToDomainModel() })

        coEvery { starWarsRepository.getCharacterPagingFlow(any()) } returns flowOf(pagingData)

        println(viewModel.characterPagingFlow)

        // When
        viewModel.setSearchQuery(query)
        val collectedData = viewModel.characterPagingFlow

        // Then
        assertEquals(query, viewModel.searchQuery.value)
        //assertEquals(filteredData, collectedData)
        coVerify { searchQueryProvider.setSearchQuery(query) }
        coVerify { starWarsRepository.getCharacterPagingFlow(query) }

    }
}


