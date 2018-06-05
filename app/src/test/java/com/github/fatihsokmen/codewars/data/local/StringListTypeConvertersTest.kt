package com.github.fatihsokmen.codewars.data.local

import com.flextrade.jfixture.FixtureAnnotations
import com.flextrade.jfixture.annotations.Fixture
import org.hamcrest.CoreMatchers.hasItems as hasItems
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat as assertThat
import org.junit.Before
import org.junit.Test


class StringListTypeConvertersTest {

    @Fixture
    private lateinit var fixtList: List<String>

    private lateinit var sut: StringListTypeConverter


    @Before
    fun setup() {
        FixtureAnnotations.initFixtures(this)
        sut = StringListTypeConverter
    }

    @Test
    fun convert() {
        val value = sut.toString(fixtList)
        val list = sut.toList(value)

        assertEquals(fixtList[0], list?.get(0))
        assertEquals(fixtList[1], list?.get(1))
        assertEquals(fixtList[2], list?.get(2))
    }
}