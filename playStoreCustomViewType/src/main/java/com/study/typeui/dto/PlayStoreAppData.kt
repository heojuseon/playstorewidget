package com.study.typeui.dto

import com.google.gson.annotations.SerializedName

data class PlayStoreAppData(

    @SerializedName("content")
    val content: List<PlayStoreContent>
)

data class PlayStoreContent(
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("title")
    val title: String = "",

    @SerializedName("url")
    val url: String = "",

    @SerializedName("category")
    val category: Int = 0,

    @SerializedName("score")
    val score: String = ""
)
