package com.seventhson.marvel.data.common.mappers


import com.seventhson.marvel.data.common.model.entities.CharacterDetailEntity
import com.seventhson.marvel.data.common.model.response.CharacterDetailResponse
import com.seventhson.marvel.domain.common.CharacterDetail
import com.seventhson.marvel.utils.extensions.SERVER_FORMAT
import com.seventhson.marvel.utils.extensions.STANDART_FORMAT
import com.seventhson.marvel.utils.extensions.toFormatStringDate
import com.seventhson.marvel.utils.setImageUrl

fun List<CharacterDetailResponse>.toDatabase() : List<CharacterDetailEntity> =
    map {
        CharacterDetailEntity(
            id = it.id,
            image = setImageUrl(it.thumbnail?.path ?: "", it.thumbnail?.extension ?: ""),
            modifiedDate = it.modified?.toFormatStringDate(SERVER_FORMAT, STANDART_FORMAT) ?: "",
            name = it.name ?: "",
            comicsNum = it.comics?.available?.toString() ?: "0",
            description = it.description ?: ""
        )
    }

fun List<CharacterDetailEntity>.toDomain() : List<CharacterDetail> =
    map {
        CharacterDetail(
            id = it.id,
            image = it.image,
            modifiedDate = it.modifiedDate,
            name = it.name,
            comicsNum = it.comicsNum,
            description = it.description
        )
    }

fun CharacterDetailResponse.toDatabase() : CharacterDetailEntity =
        CharacterDetailEntity(
            id = this.id,
            image = setImageUrl(this.thumbnail?.path ?: "", this.thumbnail?.extension ?: ""),
            modifiedDate = this.modified?.toFormatStringDate(SERVER_FORMAT, STANDART_FORMAT) ?: "",
            name = this.name ?: "",
            comicsNum = this.comics?.available?.toString() ?: "0",
            description = this.description ?: ""
        )

fun CharacterDetailEntity.toDomain() : CharacterDetail =
    CharacterDetail(
        id = this.id,
        image = this.image,
        modifiedDate = this.modifiedDate,
        name = this.name,
        comicsNum = this.comicsNum,
        description = this.description
    )
