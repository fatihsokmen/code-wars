package com.github.fatihsokmen.codewars.challengedetails

import com.flextrade.jfixture.FixtureAnnotations
import com.flextrade.jfixture.JFixture
import com.flextrade.jfixture.annotations.Fixture
import com.github.fatihsokmen.codewars.data.ChallengeDetailsDomain
import com.github.fatihsokmen.codewars.data.mapper.challenges.ChallengeDetailsDtoToDomainMapper
import com.github.fatihsokmen.codewars.data.remote.challengedetails.ChallengeDetailsDto
import com.github.fatihsokmen.codewars.data.remote.challenges.ChallengeApiService
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class ChallengeDetailsRepositoryTest {

    @Mock
    private lateinit var mockChallengeApiService: ChallengeApiService
    @Mock
    private lateinit var mockChallengeDetailsDtoToDomainMapper: ChallengeDetailsDtoToDomainMapper

    @Fixture
    private lateinit var fixtChallengeId: String
    @Fixture
    private lateinit var fixtDto: ChallengeDetailsDto
    @Fixture
    private lateinit var fixtDomain: ChallengeDetailsDomain

    private lateinit var sut: ChallengeDetailsRepository

    private val fixture = JFixture()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        FixtureAnnotations.initFixtures(this, fixture)
        sut = ChallengeDetailsRepository(mockChallengeApiService, mockChallengeDetailsDtoToDomainMapper)
    }

    @Test
    fun getChallengeDetails() {
        // init
        Mockito.`when`(mockChallengeApiService.getChallengeDetails(fixtChallengeId))
            .thenReturn(Observable.just(fixtDto))
        Mockito.`when`(mockChallengeDetailsDtoToDomainMapper.apply(fixtDto))
                .thenReturn(fixtDomain)

        // run
        val testSubscriber = sut.getChallengeDetails(fixtChallengeId).test()

        // verify
        testSubscriber.assertValue(fixtDomain)
    }

}