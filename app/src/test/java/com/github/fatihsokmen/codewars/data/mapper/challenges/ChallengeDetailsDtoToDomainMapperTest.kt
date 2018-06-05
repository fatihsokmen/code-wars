package com.github.fatihsokmen.codewars.data.mapper.challenges

import com.flextrade.jfixture.FixtureAnnotations
import com.flextrade.jfixture.annotations.Fixture
import com.github.fatihsokmen.codewars.data.remote.challengedetails.ChallengeDetailsDto
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat as assertThat
import org.junit.Before
import org.junit.Test


class ChallengeDetailsDtoToDomainMapperTest {

    @Fixture
    private lateinit var fixtChallengesDetailsDto: ChallengeDetailsDto

    private lateinit var sut: ChallengeDetailsDtoToDomainMapper

    @Before
    fun setup() {
        FixtureAnnotations.initFixtures(this)
        sut = ChallengeDetailsDtoToDomainMapper()
    }

    @Test
    fun apply() {
        val actual = sut.apply(fixtChallengesDetailsDto)

        assertEquals(fixtChallengesDetailsDto.id, actual.id)
        assertEquals(fixtChallengesDetailsDto.name, actual.name)
        assertEquals(fixtChallengesDetailsDto.slug, actual.slug)
        assertEquals(fixtChallengesDetailsDto.category, actual.category)
        assertEquals(fixtChallengesDetailsDto.languages, actual.languages)
        assertEquals(fixtChallengesDetailsDto.description, actual.description)
        assertEquals(fixtChallengesDetailsDto.tags, actual.tags)
    }

}