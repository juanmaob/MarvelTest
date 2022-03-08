package com.seventhson.marvel.utils

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NetworkUtilsTest {

    @Mock
    lateinit var sut: NetworkUtils

    @Before
    fun setUp() {
    }

    @Test
    fun hasConnection_true_returnTrue() {
        Mockito.`when`(sut.hasConnection()).thenReturn(true)
        assertThat(sut.hasConnection(), equalTo(true))
    }

    @Test
    fun hasConnection_false_returnFalse() {
        Mockito.`when`(sut.hasConnection()).thenReturn(false)
        assertThat(sut.hasConnection(), equalTo(false))
    }
}