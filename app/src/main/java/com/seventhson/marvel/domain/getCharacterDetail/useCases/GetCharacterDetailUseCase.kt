package com.seventhson.marvel.domain.getCharacterDetail.useCases

import com.seventhson.marvel.domain.common.CharacterDetail
import com.seventhson.marvel.domain.getCharacterDetail.model.CharacterDetailParams
import com.seventhson.marvel.domain.repository.CharacterDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterDetailUseCase @Inject constructor(
    private val repository: CharacterDetailRepository
) {

    private lateinit var _params: CharacterDetailParams

    fun setParams(id: Int) {
        _params = CharacterDetailParams(
            characterId = id
        )
    }

    fun executeCall(): Flow<CharacterDetail> = repository.getCharacterDetail(_params)

}