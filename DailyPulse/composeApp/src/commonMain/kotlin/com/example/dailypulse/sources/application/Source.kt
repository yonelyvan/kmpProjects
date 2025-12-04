package com.example.dailypulse.sources.application

data class Source(
    val name: String,
    val desc: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String
)