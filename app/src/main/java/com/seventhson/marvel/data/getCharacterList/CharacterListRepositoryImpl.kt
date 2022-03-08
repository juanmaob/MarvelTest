package com.seventhson.marvel.data.getCharacterList

import com.seventhson.marvel.data.common.mappers.toDatabase
import com.seventhson.marvel.data.common.mappers.toDomain
import com.seventhson.marvel.data.getCharacterList.dataSource.CharacterLocalDataSource
import com.seventhson.marvel.data.getCharacterList.dataSource.CharacterRemoteDataSource
import com.seventhson.marvel.domain.common.CharacterDetail
import com.seventhson.marvel.domain.getCharacterList.model.CharacterListParams
import com.seventhson.marvel.domain.repository.CharacterListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class CharacterListRepositoryImpl @Inject constructor(
    private val characterRemoteDataSource: CharacterRemoteDataSource,
    private val characterLocalDataSource: CharacterLocalDataSource
) : CharacterListRepository {


    override fun getCharacterList(params: CharacterListParams): Flow<List<CharacterDetail>> = flow {
        emit(characterLocalDataSource.getCharacters(params.offset).toDomain())
        reloadCharacterList(params).collect { emit(it) }
    }

    override fun reloadCharacterList(params: CharacterListParams): Flow<List<CharacterDetail>> =
        flow {
            characterRemoteDataSource.getCharacters(params)
                .transform { characterResponse ->

                    characterResponse.characterList?.results?.toDatabase()
                        ?.let { characterLocalDataSource.saveCharacters(it) }
                    emit(characterLocalDataSource.getCharacters(params.offset).toDomain())
                }
                .collect { characterList ->
                    emit(characterList)
                }
        }


}