package com.github.fatihsokmen.codewars.data.local

import com.flextrade.jfixture.FixtureAnnotations
import com.flextrade.jfixture.annotations.Fixture
import com.github.fatihsokmen.codewars.data.remote.search.Ranks
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before


class RanksTypeConvertersTest {

    @Fixture
    private lateinit var fixtRanks: Ranks

    private lateinit var sut: RanksTypeConverter


    @Before
    fun setup() {
        FixtureAnnotations.initFixtures(this)
        sut = RanksTypeConverter
    }

    @Test
    fun convert() {
        val value = sut.toString(fixtRanks)
        val list = sut.toRanks(value)

        assertEquals(fixtRanks, list)
    }
}