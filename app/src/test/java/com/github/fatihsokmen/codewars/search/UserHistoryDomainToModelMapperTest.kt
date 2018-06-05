package com.github.fatihsokmen.codewars.search

import com.flextrade.jfixture.FixtureAnnotations
import com.flextrade.jfixture.JFixture
import com.flextrade.jfixture.annotations.Fixture
import com.github.fatihsokmen.codewars.data.UserDomain
import org.hamcrest.Matchers
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

class UserHistoryDomainToModelMapperTest {

    @Fixture
    private lateinit var fixtUserDomains: List<UserDomain>

    private lateinit var sut: UserHistoryDomainToModelMapper

    private val fixture = JFixture()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        FixtureAnnotations.initFixtures(this, fixture)
        sut = UserHistoryDomainToModelMapper()
    }

    @Test
    fun apply() {
        val actual = sut.apply(fixtUserDomains)

        assertEquals(UserModel.RECENT, actual[0].type)
        assertThat(actual, Matchers.hasSize(fixtUserDomains.size + 1))

        for (i in 1 until actual.size) {
            assertEquals(UserModel.HISTORY, actual[i].type)
            assertEquals(fixtUserDomains[i-1].name, actual[i].name)
            assertEquals(fixtUserDomains[i-1].userName, actual[i].userName)
            assertEquals(fixtUserDomains[i-1].leaderboardPosition, actual[i].leaderboardPosition)
        }
    }
}