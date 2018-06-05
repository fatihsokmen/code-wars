package com.github.fatihsokmen.codewars.data.mapper.challenges

import com.flextrade.jfixture.FixtureAnnotations
import com.flextrade.jfixture.annotations.Fixture
import com.github.fatihsokmen.codewars.data.remote.challenges.AuthoredChallengesResponseDto
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class AuthoredChallengesDtoToDomainMapperTest {

    @Fixture
    private lateinit var fixtChallengesDto: AuthoredChallengesResponseDto

    private lateinit var sut: AuthoredChallengesDtoToDomainMapper

    @Before
    fun setup() {
        FixtureAnnotations.initFixtures(this)
        sut = AuthoredChallengesDtoToDomainMapper()
    }

    @Test
    fun apply() {
        val actual = sut.apply(fixtChallengesDto)

        actual.forEachIndexed { i, domain ->
            assertEquals(fixtChallengesDto.data[i].id, domain.id)
            assertEquals(fixtChallengesDto.data[i].name, domain.name)
        }
    }
}