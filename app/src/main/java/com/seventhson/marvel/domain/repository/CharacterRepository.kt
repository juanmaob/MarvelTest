package com.seventhson.marvel.domain.repository

import com.seventhson.marvel.domain.common.CharacterDetail
import com.seventhson.marvel.domain.getCharacterList.model.CharacterListParams
import kotlinx.coroutines.flow.Flow

interface CharacterListRepository {

    fun getCharacterList(params: CharacterListParams): Flow<List<CharacterDetail>>

    fun reloadCharacterList(params: CharacterListParams): Flow<List<CharacterDetail>>

}