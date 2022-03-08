package com.seventhson.marvel.data.getCharacterDetail.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.seventhson.marvel.data.common.base.BaseDao
import com.seventhson.marvel.data.common.model.entities.CharacterDetailEntity

@Dao
interface CharacterDetailDao : BaseDao<CharacterDetailEntity> {

    @Query("SELECT * FROM CharacterDetailEntity WHERE id LIKE :characterId")
    fun getCharacterDetail(characterId: Int): CharacterDetailEntity

}