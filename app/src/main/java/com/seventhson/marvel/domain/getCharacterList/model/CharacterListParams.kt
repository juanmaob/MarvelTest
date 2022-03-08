package com.seventhson.marvel.domain.getCharacterList.model

data class CharacterListParams(
    var limit: Int = 10,
    var offset: Int = 0
)