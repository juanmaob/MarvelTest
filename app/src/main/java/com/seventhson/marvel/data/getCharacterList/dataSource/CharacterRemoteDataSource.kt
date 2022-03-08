package com.seventhson.marvel.data.getCharacterList.dataSource

import com.seventhson.marvel.data.ApiInterface
import com.seventhson.marvel.data.common.base.BaseRemoteDataSource
import com.seventhson.marvel.data.common.model.response.CharacterResponse
import com.seventhson.marvel.domain.getCharacterList.model.CharacterListParams
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val apiInterface: ApiInterface
) : BaseRemoteDataSource() {

    suspend fun getCharacters(params: CharacterListParams): Flow<CharacterResponse> = apiCall {
        apiInterface.getCharacterList(
            limit = params.limit,
            offset = params.offset
        )
    }

}