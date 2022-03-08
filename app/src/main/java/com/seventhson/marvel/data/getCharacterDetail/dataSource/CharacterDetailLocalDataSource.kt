package com.seventhson.marvel.data.getCharacterDetail.dataSource

import com.seventhson.marvel.data.AppDatabase
import com.seventhson.marvel.data.common.base.BaseLocalDataSource
import com.seventhson.marvel.data.common.model.entities.CharacterDetailEntity

class CharacterDetailLocalDataSource(
    private val database: AppDatabase
): BaseLocalDataSource() {

    suspend fun getCharacterDetail(characterId: Int): CharacterDetailEntity {
        return dbCall { database.characterDetailDao.getCharacterDetail(characterId) }
    }

    suspend fun saveCharacterDetail(characterDetailEntity: CharacterDetailEntity) {
        dbCall { database.characterDetailDao.insert(characterDetailEntity) }
    }
}