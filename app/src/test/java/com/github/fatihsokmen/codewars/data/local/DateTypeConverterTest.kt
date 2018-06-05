package com.github.fatihsokmen.codewars.data.local

import com.flextrade.jfixture.FixtureAnnotations
import com.flextrade.jfixture.annotations.Fixture
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.util.*


class DateTypeConverterTest {

    @Fixture
    private lateinit var fixtDate: Date

    private lateinit var sut: DateTypeConverter

    @Before
    fun setup() {
        FixtureAnnotations.initFixtures(this)
        sut = DateTypeConverter
    }
    @Test
    fun convert() {
        val value = sut.toLong(fixtDate)
        val date = sut.toDate(value)

        Assert.assertEquals(fixtDate.time, date?.time)
    }

}