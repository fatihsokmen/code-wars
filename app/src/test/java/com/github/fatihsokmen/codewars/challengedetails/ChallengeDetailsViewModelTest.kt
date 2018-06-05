package com.github.fatihsokmen.codewars.challengedetails

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.flextrade.jfixture.FixtureAnnotations
import com.flextrade.jfixture.JFixture
import com.flextrade.jfixture.annotations.Fixture
import com.github.fatihsokmen.codewars.TestScheduler
import com.github.fatihsokmen.codewars.data.ChallengeDetailsDomain
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.*
import org.mockito.Mockito.verify

class ChallengeDetailsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockChallengeDetailsRepository: ChallengeDetailsRepository
    @Mock
    private lateinit var mockDetailsObserver: Observer<ChallengeDetailsResource<ChallengeDetailsDomain>>

    @Captor
    private lateinit var captor: ArgumentCaptor<ChallengeDetailsResource<ChallengeDetailsDomain>>


    @Fixture
    private lateinit var fixtChallengeId: String
    @Fixture
    private lateinit var fixtDetailsDomain: ChallengeDetailsDomain

    private lateinit var sut: ChallengeDetailsViewModel

    private val fixture = JFixture()
    private val testScheduler = TestScheduler()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        FixtureAnnotations.initFixtures(this, fixture)
        sut = ChallengeDetailsViewModel(fixtChallengeId, mockChallengeDetailsRepository, testScheduler)
    }

    @Test
    fun getDetails_success() {
        Mockito.`when`(mockChallengeDetailsRepository.getChallengeDetails(fixtChallengeId))
                .thenReturn(Observable.just(fixtDetailsDomain))

        sut.details().observeForever(mockDetailsObserver)

        // verify
        verify<Observer<ChallengeDetailsResource<ChallengeDetailsDomain>>>(mockDetailsObserver)
                .onChanged(captor.capture())
        assertEquals(captor.value.status, Status.SUCCESS)
        assertEquals(fixtDetailsDomain, captor.value.data)
    }

    @Test
    fun getDetails_error() {
        Mockito.`when`(mockChallengeDetailsRepository.getChallengeDetails(fixtChallengeId))
                .thenReturn(Observable.error(RuntimeException()))

        sut.details().observeForever(mockDetailsObserver)

        // verify
        verify<Observer<ChallengeDetailsResource<ChallengeDetailsDomain>>>(mockDetailsObserver)
                .onChanged(captor.capture())
        assertEquals(captor.value.status, Status.ERROR)
    }

}