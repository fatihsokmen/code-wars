package com.github.fatihsokmen.codewars.data.mapper.challenges

import com.flextrade.jfixture.FixtureAnnotations
import com.flextrade.jfixture.annotations.Fixture
import com.github.fatihsokmen.codewars.data.remote.challenges.CompletedChallengesResponseDto
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class CompletedChallengesDtoToDomainMapperTest {

    @Fixture
    private lateinit var fixtChallengesDto: CompletedChallengesResponseDto

    private lateinit var sut: CompletedChallengesDtoToDomainMapper

    @Before
    fun setup() {
        FixtureAnnotations.initFixtures(this)
        sut = CompletedChallengesDtoToDomainMapper()
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