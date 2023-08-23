package com.wenubey.starwarswiki

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.wenubey.starwarswiki.data.local.StarWarsDao
import com.wenubey.starwarswiki.data.local.entities.CharacterEntity
import com.wenubey.starwarswiki.data.remote.SearchQueryProvider
import com.wenubey.starwarswiki.data.remote.StarWarsApi
import com.wenubey.starwarswiki.data.remote.StarWarsImageApi
import com.wenubey.starwarswiki.data.remote.StarWarsRemoteMediator
import com.wenubey.starwarswiki.data.remote.dto.CharacterDto
import com.wenubey.starwarswiki.data.remote.dto.ImageDto
import com.wenubey.starwarswiki.data.remote.dto.ListCharacterDto
import com.wenubey.starwarswiki.data.remote.dto.PlanetDto
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class RemoteMediatorTest {

    private val api: StarWarsApi = mockk()
    private val dao: StarWarsDao = mockk {
        coEvery { clearAll() } just Runs
        coEvery { upsertAll(any()) } just Runs
    }
    private val imageApi: StarWarsImageApi = mockk()
    private val searchQueryProvider: SearchQueryProvider = mockk()
    private val pagingState = mockk<PagingState<Int, CharacterEntity>> {
        every { config } returns PagingConfig(10)
    }
    private lateinit var testDispatcher: TestDispatcher

    private val fakeData = ListCharacterDto(
        listOf(
            CharacterDto(name = "Luke Skywalker"),
            CharacterDto(name = "Darth Vader"),
            CharacterDto(name = "Obi-Wan Kenobi"),
            CharacterDto(name = "Princess Leia"),
        ),
        next = "page=2",
        previous = null,
    )

    private val fakeSearchQuery = "fakeSearchQuery"

    private val fakePlanet = PlanetDto(name = "Earth")
    private val fakeImage = ImageDto("fakeImage")

    @Before
    fun setUp() {
        testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        clearAllMocks()

    }


    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `Remote Mediator Load Success - Refresh`() = runBlocking {
        // Given
        every { searchQueryProvider.getSearchQuery() } returns ""
        coEvery { api.getCharacters(1) } coAnswers { fakeData }
        coEvery { api.getPlanet(1) } coAnswers { fakePlanet }
        coEvery { imageApi.getImageFromId(1) } coAnswers { fakeImage }

        // When
        val result = createRemoteMediator().load(LoadType.REFRESH, pagingState)

        // Then
        assertTrue(result is RemoteMediator.MediatorResult.Success)
        assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }


    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `Remote Mediator Load Success - Append`() = runBlocking {
        // Given
        // I gave 11 to ID because our pageSize is 10
        val lastCharacter = CharacterEntity(id = 11, name = "Last Character")

        every { searchQueryProvider.getSearchQuery() } returns ""
        coEvery { pagingState.lastItemOrNull() } coAnswers { lastCharacter }
        coEvery { api.getCharacters(2) } coAnswers { fakeData }
        coEvery { api.getPlanet(1) } coAnswers { fakePlanet }
        coEvery { imageApi.getImageFromId(1) } coAnswers { fakeImage }


        // When
        val result = createRemoteMediator().load(LoadType.APPEND, pagingState)

        // Then
        assert(result is RemoteMediator.MediatorResult.Success)
        coVerify { api.getCharacters(2) }
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `Remote Mediator Load Success - Prepend`() = runBlocking {
        // Given
        val firstCharacter = CharacterEntity(id = 1, name = "First Character")

        every { searchQueryProvider.getSearchQuery() } returns ""
        coEvery { pagingState.firstItemOrNull() } coAnswers { firstCharacter }
        coEvery { api.getCharacters(2) } coAnswers { fakeData }
        coEvery { api.getPlanet(1) } coAnswers { fakePlanet }
        coEvery { imageApi.getImageFromId(1) } coAnswers { fakeImage }


        // When
        val result = createRemoteMediator().load(LoadType.PREPEND, pagingState)

        // Then
        assert(result is RemoteMediator.MediatorResult.Success)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `Remote Mediator Load Success - End Of Pagination`() = runBlocking {
        // Given
        every { searchQueryProvider.getSearchQuery() } returns ""
        coEvery { api.getCharacters(1) } coAnswers { fakeData.copy(next = null) }
        coEvery { api.getPlanet(1) } coAnswers { fakePlanet }
        coEvery { imageApi.getImageFromId(1) } coAnswers { fakeImage }

        // When
        val result = createRemoteMediator().load(LoadType.REFRESH, pagingState)

        // Then
        assert(result is RemoteMediator.MediatorResult.Success)
        assert((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun `Remote Mediator Load Success - Search Query`() = runBlocking {
        // Given
        coEvery { searchQueryProvider.getSearchQuery() } returns fakeSearchQuery
        coEvery { api.searchCharacter(fakeSearchQuery) } returns fakeData
        coEvery { api.getPlanet(1) } coAnswers { fakePlanet }
        coEvery { imageApi.getImageFromId(1) } coAnswers { fakeImage }

        // When
        val result = createRemoteMediator().load(LoadType.REFRESH, pagingState)

        // Then
        assert(result is RemoteMediator.MediatorResult.Success)
        coVerify(exactly = 1) { api.searchCharacter(fakeSearchQuery) }
    }






    private fun createRemoteMediator() =
        StarWarsRemoteMediator(api, dao, imageApi, searchQueryProvider)


}

