package com.example.dailypulse.articles

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ArticleRaw(
    @SerialName("title")
    val title: String? = null,

    @SerialName("description")
    val desc: String? = null,

    @SerialName("publishedAt")
    val publishedAt: String? = null,

    @SerialName("urlToImage")
    val imageUrl: String? = null
)
