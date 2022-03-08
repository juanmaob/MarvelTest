package com.seventhson.marvel.ui.main

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.seventhson.marvel.domain.common.CharacterDetail
import com.seventhson.marvel.domain.getCharacterList.model.CharacterListParams
import com.seventhson.marvel.domain.getCharacterList.useCases.GetCharacterListUseCase
import com.seventhson.marvel.domain.getCharacterList.useCases.ReloadCharacterListUseCase
import com.seventhson.marvel.ui.common.BaseViewModel
import com.seventhson.marvel.utils.CustomException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCharacterListUseCase: GetCharacterListUseCase,
    private val reloadCharacterListUseCase: ReloadCharacterListUseCase
) : BaseViewModel() {

    private var _characterListData = listOf<CharacterDetail>()

    var isRefreshing = mutableStateOf(false)
        private set

    var characterListState = mutableStateListOf<CharacterDetail>()
        private set

    init {
        getCharacterList()
    }


    fun getCharacterList() {
        loading.value = SHOW
        viewModelScope.launch {
            getCharacterListUseCase.executeCall(
                CharacterListParams(
                    offset = _characterListData.size
                )
            )
                .catch {
                    val ex = it as CustomException
                    errorMessage.value = mapOf(ex.code to (ex.msg))
                }
                .onCompletion { loading.value = DISMISS }
                .collect { characterList ->
                    _characterListData = _characterListData.plus(characterList)
                    characterListState.addAll(characterList)
                }
        }
    }

    fun reloadCharacterList() {
        viewModelScope.launch {
            fetchCharacters()
        }
    }

    fun refreshCharacterList() {
        viewModelScope.launch {
            isRefreshing.value = true
            fetchCharacters()
            isRefreshing.value = false
        }
    }

    private suspend fun fetchCharacters() {
        loading.value = SHOW
        reloadCharacterListUseCase.executeCall(
            CharacterListParams(
                offset = _characterListData.size
            )
        )
            .catch {
                val ex = it as CustomException
                errorMessage.value = mapOf(ex.code to (ex.msg))
            }
            .onCompletion { loading.value = DISMISS }
            .collect { characterList ->
                _characterListData = _characterListData.plus(characterList)
                characterListState.addAll(characterList)
            }
    }

}