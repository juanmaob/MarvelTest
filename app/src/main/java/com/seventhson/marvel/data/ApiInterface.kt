package com.seventhson.marvel.data

import com.seventhson.marvel.data.common.model.response.CharacterDetailResponse
import com.seventhson.marvel.data.common.model.response.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("characters")
    suspend fun getCharacterList(
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
    ): Response<CharacterResponse>

    @GET("characters/{characterId}")
    suspend fun getCharacterDetail(
        @Path("characterId") id: Int
    ): Response<CharacterDetailResponse>
}