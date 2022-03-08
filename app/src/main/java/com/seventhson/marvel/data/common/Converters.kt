package com.seventhson.marvel.data.common

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.seventhson.marvel.data.common.model.entities.CharacterDetailEntity

inline fun <reified T> Gson.fromJson(json: String): T = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

class Converters {

    var gson = Gson()

    @TypeConverter
    fun toCharacterListDatabase(data: String?): List<CharacterDetailEntity>? {
        return data?.let { gson.fromJson<List<CharacterDetailEntity>>(it) }
    }

    @TypeConverter
    fun fromCharacterListDatabase(obj: List<CharacterDetailEntity>?): String? {
        return obj?.let { gson.toJson(obj) }
    }

    @TypeConverter
    fun toCharacterDetailDatabase(data: String?): CharacterDetailEntity? {
        return data?.let { gson.fromJson<CharacterDetailEntity>(it) }
    }

    @TypeConverter
    fun fromCharacterDetailDatabase(obj: CharacterDetailEntity?): String? {
        return obj?.let { gson.toJson(obj) }
    }
}