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
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
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

    private val starWarsRepository: StarWarsRepository = mockk(relaxed = true)
    private val searchQueryProvider: SearchQueryProvider  = mockk(relaxed = true)
    private lateinit var testDispatcher: TestDispatcher

    private val fakeData = listOf(
        CharacterEntity(name = "Luke Skywalker", id = 1),
        CharacterEntity(name = "Darth Vader", id = 2),
        CharacterEntity(name = "Obi-Wan Kenobi", id = 3),
        CharacterEntity(name = "Princess Leia", id = 4),
    )
    private val query = "Luke"
    private val filteredData = fakeData.filter { it.name!!.contains(query, ignoreCase = true) }
    private val pagingData: PagingData<CharacterModel> = PagingData.from(filteredData.map { it.mapToDomainModel() })

    @get:Rule
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }


    @Test
    fun `Given a new query, When input search query, Then query updated`() = runTest {
        // Given
        val viewModel = createViewModel()

        // When
        viewModel.setSearchQuery(query)

        // Then
        assertEquals(query, viewModel.searchQuery.value)
        coVerify { searchQueryProvider.setSearchQuery(query) }
    }

    @Test
    fun `viewModel setSearchQuery should update query and fetch data`() = runTest {
        val viewModel = createViewModel()
        coEvery { starWarsRepository.getCharacterPagingFlow(any()) } returns flowOf(pagingData)

        val job = launch { viewModel.characterPagingFlow.collect {} }

        // When
        viewModel.setSearchQuery(query)
        advanceTimeBy(1000)

        // Then
        coVerify { searchQueryProvider.setSearchQuery(query) }
        coVerify { starWarsRepository.getCharacterPagingFlow(query) }
        job.cancel()
    }

    // daha guzel bi test ismi yazabilecegine inaniyorum, yukardakinden kopya cekebilirsin

    private fun createViewModel(): StarWarsViewModel {
        return StarWarsViewModel(
            starWarsRepository = starWarsRepository,
            searchQueryProvider = searchQueryProvider,
        )
    }


}

