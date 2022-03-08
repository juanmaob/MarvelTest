package com.seventhson.marvel.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.seventhson.marvel.data.common.Converters
import com.seventhson.marvel.data.common.model.entities.CharacterDetailEntity
import com.seventhson.marvel.data.getCharacterDetail.database.dao.CharacterDetailDao
import com.seventhson.marvel.data.getCharacterList.database.dao.CharacterDao

@Database(
    entities = [
        CharacterDetailEntity::class
               ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val characterDao: CharacterDao

    abstract val characterDetailDao: CharacterDetailDao
}
