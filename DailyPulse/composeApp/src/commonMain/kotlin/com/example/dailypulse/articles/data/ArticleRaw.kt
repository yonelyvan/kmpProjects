package com.example.dailypulse.articles.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ArticleRaw(
    @SerialName("title")
    val title: String? = null,

    @SerialName("description")
    val desc: String? = null,

    @SerialName("publishedAt")
    val publishedAt: String?,

    @SerialName("urlToImage")
    val imageUrl: String? = null
)