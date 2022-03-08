package com.seventhson.marvel.ui.detail

import androidx.lifecycle.SavedStateHandle
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.seventhson.marvel.MainCoroutineRule
import com.seventhson.marvel.domain.getCharacterDetail.useCases.GetCharacterDetailUseCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var sut: DetailViewModel
    @Mock
    lateinit var getCharacterDetailUseCase: GetCharacterDetailUseCase
    @Mock
    lateinit var savedStateHandle: SavedStateHandle

    @Before
    fun setUp() {
        sut = DetailViewModel(getCharacterDetailUseCase, savedStateHandle)
    }

    @Test
    fun getCharacterDetail_callsSetParams() {
        sut.getCharacterDetail(1)
        verify(getCharacterDetailUseCase).setParams(eq(1))
    }

    @Test
    fun getCharacterDetail_callsExecuteCall() {
        sut.getCharacterDetail(1)
        verify(getCharacterDetailUseCase).executeCall()
    }
}
