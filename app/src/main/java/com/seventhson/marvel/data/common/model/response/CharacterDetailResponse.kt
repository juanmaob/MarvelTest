package com.seventhson.marvel.data.common.model.response

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("data")
    val characterList: DataResponse?
)

data class DataResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("limit")
    val limit: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("results")
    val results: List<CharacterDetailResponse>?,
    @SerializedName("total")
    val total: Int?
)

data class CharacterDetailResponse(
    @SerializedName("comics")
    val comics: ComicsResponse?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("modified")
    val modified: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse?
)

data class ComicsResponse(
    @SerializedName("available")
    val available: Int?
)

data class ThumbnailResponse(
    @SerializedName("extension")
    val extension: String?,
    @SerializedName("path")
    val path: String?
)

