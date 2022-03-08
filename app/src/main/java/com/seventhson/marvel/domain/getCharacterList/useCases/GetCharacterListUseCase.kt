package com.seventhson.marvel.domain.getCharacterList.useCases

import com.seventhson.marvel.domain.common.CharacterDetail
import com.seventhson.marvel.domain.getCharacterList.model.CharacterListParams
import com.seventhson.marvel.domain.repository.CharacterListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharacterListUseCase @Inject constructor(
    private val repository: CharacterListRepository
) {

    fun executeCall(params: CharacterListParams): Flow<List<CharacterDetail>> = repository.getCharacterList(params)

}