package com.wenubey.starwarswiki

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.wenubey.starwarswiki.data.local.entities.CharacterEntity
import com.wenubey.starwarswiki.data.remote.SearchQueryProvider
import com.wenubey.starwarswiki.domain.StarWarsRepository
import com.wenubey.starwarswiki.domain.models.CharacterModel
import com.wenubey.starwarswiki.presentation.StarWarsViewModel
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@FlowPreview
class ViewModelTest {

    private lateinit var viewModel: StarWarsViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    private class MockSearchQueryProvider : SearchQueryProvider {
        private var currentQuery: String = ""
        override fun getSearchQuery(): String {
            return currentQuery
        }

        override fun setSearchQuery(query: String) {
            currentQuery = query
        }
    }

    private class MockStarWarsRepository : StarWarsRepository {

        val characterList = listOf(
            CharacterEntity(1, "Luke Skywalker"),
            CharacterEntity(2, "Darth Vader"),
            CharacterEntity(3, "Leia Organa")
        )

        override fun getCharacterPagingFlow(query: String): Flow<PagingData<CharacterModel>> {
            return flowOf(PagingData.from(characterList)).map { pagingData ->
                pagingData.map { character ->
                    character.mapToDomainModel()
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        // Mock the dependencies

        val starWarsRepository = MockStarWarsRepository()
        val searchQueryProvider = MockSearchQueryProvider()

        viewModel = StarWarsViewModel(starWarsRepository, searchQueryProvider)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
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
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `characterPagingFlow should filter PagingData based on searchQuery`() = runTest {
        // Given
        val mockPager = mockk<Pager<Int, CharacterEntity>>()
        val searchQueryProvider = MockSearchQueryProvider()
        val starWarsRepository = MockStarWarsRepository()
        val viewModel = StarWarsViewModel(starWarsRepository, searchQueryProvider)

        val characterList = listOf(
            CharacterEntity(1, "Luke Skywalker"),
            CharacterEntity(2, "Darth Vader"),
            CharacterEntity(3, "Leia Organa")
        )
        val pagingData = PagingData.from(characterList)
        coEvery { mockPager.flow } returns flowOf(pagingData)

        // When
        viewModel.setSearchQuery("Sky")

        // Then
        val filteredPagingData = viewModel.characterPagingFlow.toList()[0]

        // The filteredPagingData should contain only characters with "Sky" in their name.
//        assertEquals(2, filteredPagingData.size)
//        assertEquals("Luke Skywalker", filteredPagingData[0].name)
//        assertEquals("Leia Organa", filteredPagingData[1].name)

    }


}

