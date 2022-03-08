package com.seventhson.marvel.data.getCharacterDetail.dataSource

import com.seventhson.marvel.data.ApiInterface
import com.seventhson.marvel.data.common.base.BaseRemoteDataSource
import com.seventhson.marvel.data.common.model.response.CharacterDetailResponse
import com.seventhson.marvel.domain.getCharacterDetail.model.CharacterDetailParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterDetailRemoteDataSource @Inject constructor(
    private val apiInterface: ApiInterface
) : BaseRemoteDataSource() {

    suspend fun getCharacterDetail(params: CharacterDetailParams): Flow<CharacterDetailResponse> =
        flow {
            apiCall {
                apiInterface.getCharacterDetail(params.characterId)
            }.collect { emit(it) }
        }

}