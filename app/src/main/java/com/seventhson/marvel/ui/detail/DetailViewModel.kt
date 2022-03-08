package com.seventhson.marvel.ui.detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.seventhson.marvel.domain.common.CharacterDetail
import com.seventhson.marvel.domain.getCharacterDetail.useCases.GetCharacterDetailUseCase
import com.seventhson.marvel.ui.common.BaseViewModel
import com.seventhson.marvel.utils.CustomException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCharacterDetailUseCase: GetCharacterDetailUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    val characterDetailState =  mutableStateOf(CharacterDetail())

    init {
        val id = savedStateHandle.get<Int>("id")
        id?.let { getCharacterDetail(it) }
    }

    fun getCharacterDetail(characterId: Int) {
        loading.value = SHOW
        viewModelScope.launch {
            getCharacterDetailUseCase.setParams(characterId)
            getCharacterDetailUseCase.executeCall()
                .catch {
                    val ex = it as CustomException
                    errorMessage.value = mapOf(ex.code to (ex.msg))
                }
                .onCompletion { loading.value = DISMISS }
                .collect { characterDetail ->
                    characterDetailState.value = characterDetail
                }
        }
    }



}
