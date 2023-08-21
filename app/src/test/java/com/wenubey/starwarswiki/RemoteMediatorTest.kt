package com.wenubey.starwarswiki

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
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
    private val pagingState = mockk<PagingState<Int, CharacterEntity>>()
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
    fun `Remote Mediator Load Success` () = runBlocking {
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

    private fun createRemoteMediator() = StarWarsRemoteMediator(api, dao, imageApi, searchQueryProvider)


}






//@OptIn(ExperimentalCoroutinesApi::class)
//class RemoteMediatorTest {
//
//    private val api: StarWarsApi = mockk()
//    private val db: StarWarsDatabase = mockk()
//    private val imageApi: StarWarsImageApi = mockk()
//    private val searchQueryProvider: SearchQueryProvider = mockk()
//    private val remoteMediator: StarWarsRemoteMediator =
//        StarWarsRemoteMediator(api, db, imageApi, searchQueryProvider)
//    private lateinit var testDispatcher: TestDispatcher
//    private val pagingState = mockk<PagingState<Int, CharacterEntity>>()
//
//    private val fakeData = ListCharacterDto(
//        listOf(
//            CharacterDto(name = "Luke Skywalker"),
//            CharacterDto(name = "Darth Vader"),
//            CharacterDto(name = "Obi-Wan Kenobi"),
//            CharacterDto(name = "Princess Leia"),
//        ),
//        next = "page=2",
//        previous = null,
//    )
//
//    private val fakePlanet = PlanetDto(name = "Earth")
//    private val fakeImage = ImageDto("fakeImage")
//
//    @Before
//    fun setUp() {
//        testDispatcher = StandardTestDispatcher()
//        Dispatchers.setMain(testDispatcher)
//    }
//
//    @After
//    fun tearDown() {
//        clearAllMocks()
//    }
//
//    @OptIn(ExperimentalPagingApi::class)
//    @Test
//    fun `load success` () = runBlocking {
//
//        coEvery { api.getCharacters(1) } coAnswers {
//            fakeData
//        }
//
//        coEvery { api.getPlanet(1) } coAnswers {
//            fakePlanet
//        }
//        coEvery { imageApi.getImageFromId(1) } coAnswers {
//            fakeImage
//        }
//
//        every { searchQueryProvider.getSearchQuery() } returns ""
//
//        val result = remoteMediator.load(
//            loadType = LoadType.REFRESH,
//            state = pagingState
//        )
//        assertEquals(result, RemoteMediator.MediatorResult.Success(endOfPaginationReached = false))
//
//    }
//
//}