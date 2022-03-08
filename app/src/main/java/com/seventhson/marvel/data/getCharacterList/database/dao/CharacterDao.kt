package com.seventhson.marvel.data.getCharacterList.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.seventhson.marvel.data.common.base.BaseDao
import com.seventhson.marvel.data.common.model.entities.CharacterDetailEntity

@Dao
interface CharacterDao : BaseDao<CharacterDetailEntity> {

    @Query("SELECT * FROM CharacterDetailEntity LIMIT 10 OFFSET :offSet")
    fun getCharacterList(offSet: Int): List<CharacterDetailEntity>

}