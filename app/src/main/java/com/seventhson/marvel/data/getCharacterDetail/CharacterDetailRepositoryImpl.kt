package com.seventhson.marvel.data.getCharacterDetail

import com.seventhson.marvel.data.common.mappers.toDatabase
import com.seventhson.marvel.data.common.mappers.toDomain
import com.seventhson.marvel.data.getCharacterDetail.dataSource.CharacterDetailLocalDataSource
import com.seventhson.marvel.data.getCharacterDetail.dataSource.CharacterDetailRemoteDataSource
import com.seventhson.marvel.domain.common.CharacterDetail
import com.seventhson.marvel.domain.getCharacterDetail.model.CharacterDetailParams
import com.seventhson.marvel.domain.repository.CharacterDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class CharacterDetailRepositoryImpl @Inject constructor(
    private val characterDetailRemoteDataSource: CharacterDetailRemoteDataSource,
    private val characterDetailLocalDataSource: CharacterDetailLocalDataSource
) : CharacterDetailRepository {

    override fun reloadCharacterDetail(params: CharacterDetailParams): Flow<CharacterDetail> =
        flow {
            emit(characterDetailLocalDataSource.getCharacterDetail(params.characterId).toDomain())
            reloadCharacterDetail(params).collect { emit(it) }
        }

    override fun getCharacterDetail(params: CharacterDetailParams): Flow<CharacterDetail> =
        flow {
            characterDetailRemoteDataSource.getCharacterDetail(params)
                .transform { characterDetailResponse ->

                    characterDetailLocalDataSource.saveCharacterDetail(characterDetailResponse.toDatabase())
                    emit(
                        characterDetailLocalDataSource.getCharacterDetail(params.characterId)
                            .toDomain()
                    )

                }
                .collect { characterDetail ->
                    emit(characterDetail)
                }
        }

}