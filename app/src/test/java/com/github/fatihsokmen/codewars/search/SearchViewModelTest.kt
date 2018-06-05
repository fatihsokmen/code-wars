package com.github.fatihsokmen.codewars.search

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.flextrade.jfixture.FixtureAnnotations
import com.flextrade.jfixture.JFixture
import com.flextrade.jfixture.annotations.Fixture
import com.github.fatihsokmen.codewars.TestScheduler
import com.github.fatihsokmen.codewars.data.UserDomain
import io.reactivex.Flowable
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class SearchViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockSearchRepository: ISearchRepository
    @Mock
    private lateinit var mockUserHistoryDomainToModelMapper: UserHistoryDomainToModelMapper
    @Mock
    private lateinit var mockUserDomainToModelMapper: UserDomainToModelMapper
    @Mock
    private lateinit var mockRecentUsersObserver: Observer<List<UserModel>>
    @Mock
    private lateinit var mockSearchUserObserver: Observer<SearchResource<UserModel>>

    @Captor
    private lateinit var successCaptor: ArgumentCaptor<SearchResource<UserModel>>
    @Captor
    private lateinit var repoErrorCaptor: ArgumentCaptor<SearchResource<UserModel>>
    @Captor
    private lateinit var mapperErrorCaptor: ArgumentCaptor<SearchResource<UserModel>>


    @Fixture
    private lateinit var fixtUserDomains: List<UserDomain>
    @Fixture
    private lateinit var fixtUserModels: List<UserModel>
    @Fixture
    private lateinit var fixtUserDomain: UserDomain
    @Fixture
    private lateinit var fixtUserModel: UserModel


    private lateinit var sut: SearchViewModel

    private val fixture = JFixture()
    private val testScheduler = TestScheduler()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        FixtureAnnotations.initFixtures(this, fixture)
        sut = SearchViewModel(mockSearchRepository, mockUserHistoryDomainToModelMapper,
                mockUserDomainToModelMapper, testScheduler)
    }

    @Test
    fun loadRecent() {
        `when`(mockSearchRepository.getRecent())
                .thenReturn(Flowable.just(fixtUserDomains))
        `when`(mockUserHistoryDomainToModelMapper.apply(fixtUserDomains))
                .thenReturn(fixtUserModels)

        val liveData = sut.recentUsers()
        liveData.observeForever(mockRecentUsersObserver)

        verify<Observer<List<UserModel>>>(mockRecentUsersObserver).onChanged(fixtUserModels)
    }


    @Test
    fun searchUser_success() {
        // init
        val fixtUserName = fixture.create(String::class.java)

        `when`(mockSearchRepository.searchUser(fixtUserName))
                .thenReturn(Observable.just(fixtUserDomain))
        `when`(mockUserDomainToModelMapper.apply(fixtUserDomain))
                .thenReturn(fixtUserModel)

        // run
        sut.searchUser(fixtUserName)
        sut.searchedUser().observeForever(mockSearchUserObserver)

        // verify
        verify<Observer<SearchResource<UserModel>>>(mockSearchUserObserver)
                .onChanged(successCaptor.capture())
        assertEquals(successCaptor.value.status, Status.SUCCESS)
        assertEquals(successCaptor.value.data, fixtUserModel)
    }

    @Test
    fun searchUser_repository_error() {
        // init
        val fixtUserName = fixture.create(String::class.java)

        `when`(mockSearchRepository.searchUser(fixtUserName))
                .thenReturn(Observable.error(RuntimeException()))

        // run
        sut.searchedUser().observeForever(mockSearchUserObserver)
        sut.searchUser(fixtUserName)

        // verify
        verify<Observer<SearchResource<UserModel>>>(mockSearchUserObserver, atLeastOnce())
                .onChanged(repoErrorCaptor.capture())
        assertEquals(repoErrorCaptor.value.status, Status.ERROR)

    }

    @Test
    fun searchUser_mapper_error() {
        // init
        val fixtUserName = fixture.create(String::class.java)

        `when`(mockSearchRepository.searchUser(fixtUserName))
                .thenReturn(Observable.just(fixtUserDomain))
        `when`(mockUserDomainToModelMapper.apply(fixtUserDomain))
                .thenThrow(RuntimeException())

        // run
        sut.searchUser(fixtUserName)
        sut.searchedUser().observeForever(mockSearchUserObserver)

        verify<Observer<SearchResource<UserModel>>>(mockSearchUserObserver)
                .onChanged(mapperErrorCaptor.capture())
        assertEquals(mapperErrorCaptor.value.status, Status.ERROR)
    }
}
