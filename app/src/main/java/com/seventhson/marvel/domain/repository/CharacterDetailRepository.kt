package com.seventhson.marvel.domain.repository

import com.seventhson.marvel.domain.common.CharacterDetail
import com.seventhson.marvel.domain.getCharacterDetail.model.CharacterDetailParams
import kotlinx.coroutines.flow.Flow

interface CharacterDetailRepository {

    fun reloadCharacterDetail(params: CharacterDetailParams): Flow<CharacterDetail>

    fun getCharacterDetail(params: CharacterDetailParams): Flow<CharacterDetail>

}