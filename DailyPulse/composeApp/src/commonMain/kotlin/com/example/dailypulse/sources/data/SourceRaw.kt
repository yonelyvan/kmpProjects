package com.example.dailypulse.sources.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SourceRaw(
    
    @SerialName("name")
    val name: String?,

    @SerialName("description")
    val desc: String?,

    @SerialName("url")
    val url: String?,

    @SerialName("category")
    val category: String?,

    @SerialName("language")
    val language: String?,

    @SerialName("country")
    val country: String?
)


