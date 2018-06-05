package com.github.fatihsokmen.codewars.search

import com.flextrade.jfixture.FixtureAnnotations
import com.flextrade.jfixture.annotations.Fixture
import com.github.fatihsokmen.codewars.data.UserDomain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.spy

class UserDomainToModelMapperTest {

    @Fixture
    private lateinit var fixtUserDomain: UserDomain

    private lateinit var sut: UserDomainToModelMapper

    @Before
    fun setup() {
        FixtureAnnotations.initFixtures(this)
        sut = spy(UserDomainToModelMapper())
    }

    @Test
    fun apply() {
        val actual = sut.apply(fixtUserDomain)

        assertEquals(fixtUserDomain.name, actual.name)
        assertEquals(fixtUserDomain.userName, actual.userName)
        assertEquals(UserModel.SEARCH, actual.type)
        assertEquals(fixtUserDomain.leaderboardPosition, actual.leaderboardPosition)
    }

}