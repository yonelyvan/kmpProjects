package com.example.dailypulse.sources.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class SourceService(
    private val httpClient: HttpClient
) {
    private val server =
        "https://newsapi.org/v2/top-headlines/sources?apiKey=40e095b3d80b490a8107ca0d5b3d2e0e"

    suspend fun fetchSources(): List<SourceRaw> {
        val response: SourceResponse = httpClient.get(server).body()
        return response.sources
    }
}


