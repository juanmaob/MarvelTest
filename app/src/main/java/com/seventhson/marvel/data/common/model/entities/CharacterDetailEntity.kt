package com.seventhson.marvel.data.common.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterDetailEntity(
    @PrimaryKey
    val id: Int,
    val image: String,
    val modifiedDate: String,
    val name: String,
    val comicsNum: String,
    val description: String
)