package com.seventhson.marvel.data.getCharacterList.dataSource

import com.seventhson.marvel.data.AppDatabase
import com.seventhson.marvel.data.common.base.BaseLocalDataSource
import com.seventhson.marvel.data.common.model.entities.CharacterDetailEntity

class CharacterLocalDataSource(
    private val database: AppDatabase,
): BaseLocalDataSource() {

    suspend fun getCharacters(offSet: Int): List<CharacterDetailEntity> {
        return dbCall { database.characterDao.getCharacterList(offSet) }
    }

    suspend fun saveCharacters(characterListEntity: List<CharacterDetailEntity>) {
        dbCall { database.characterDao.insert(characterListEntity) }
    }
}