package com.github.fatihsokmen.codewars.search

import com.flextrade.jfixture.FixtureAnnotations
import com.flextrade.jfixture.JFixture
import com.flextrade.jfixture.annotations.Fixture
import com.github.fatihsokmen.codewars.data.UserDomain
import com.github.fatihsokmen.codewars.data.local.UserDao
import com.github.fatihsokmen.codewars.data.local.UserEntity
import com.github.fatihsokmen.codewars.data.mapper.search.UserDtoToDomainMapper
import com.github.fatihsokmen.codewars.data.mapper.search.UserDtoToEntityMapper
import com.github.fatihsokmen.codewars.data.mapper.search.UserEntityToDomainMapper
import com.github.fatihsokmen.codewars.data.remote.search.UserDto
import com.github.fatihsokmen.codewars.data.remote.search.UserSearchService
import io.reactivex.Flowable
import io.reactivex.Observable
import org.hamcrest.CoreMatchers.hasItems
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class SearchRepositoryTest {

    @Mock
    private lateinit var mockUserDao: UserDao
    @Mock
    private lateinit var mockUserSearchService: UserSearchService
    @Mock
    private lateinit var mockUserDtoToEntityMapper: UserDtoToEntityMapper
    @Mock
    private lateinit var mockUserEntityToDomainMapper: UserEntityToDomainMapper
    @Mock
    private lateinit var mockUserDtoToDomainMapper: UserDtoToDomainMapper

    @Fixture
    private lateinit var fixtUserEntities: List<UserEntity>
    @Fixture
    private lateinit var fixtUserDomains: List<UserDomain>


    private lateinit var sut: SearchRepository

    private val fixture = JFixture()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        FixtureAnnotations.initFixtures(this, fixture)
        sut = SearchRepository(mockUserDao, mockUserSearchService, mockUserDtoToEntityMapper,
                mockUserEntityToDomainMapper, mockUserDtoToDomainMapper)
    }

    @Test
    fun getRecent() {
        // init
        Mockito.`when`(mockUserDao.getUsers())
                .thenReturn(Flowable.just(fixtUserEntities))
        Mockito.`when`(mockUserEntityToDomainMapper.apply(fixtUserEntities))
                .thenReturn(fixtUserDomains)

        // run
        val testSubscriber = sut.getRecent().test()

        // verify
        val actual = testSubscriber.values()

        assertThat(actual, hasItems(fixtUserDomains))
    }

    @Test
    fun searchUser() {
        val fixtUserName = fixture.create(String::class.java)
        val fixtUserDto = fixture.create(UserDto::class.java)
        val fixtUserDomain = fixture.create(UserDomain::class.java)
        val fixtUserEntity = fixture.create(UserEntity::class.java)

        Mockito.`when`(mockUserDtoToEntityMapper.apply(fixtUserDto))
                .thenReturn(fixtUserEntity)
        Mockito.`when`(mockUserSearchService.getUser(fixtUserName))
                .thenReturn(Observable.just(fixtUserDto))
        Mockito.`when`(mockUserDtoToDomainMapper.apply(fixtUserDto))
                .thenReturn(fixtUserDomain)
        // run
        val testSubscriber = sut.searchUser(fixtUserName).test()

        // verify
        verify(mockUserDao).insert(fixtUserEntity)

        testSubscriber.assertValue(fixtUserDomain)
    }

}