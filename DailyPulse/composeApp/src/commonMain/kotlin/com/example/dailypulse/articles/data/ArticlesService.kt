package com.example.dailypulse.articles.data


import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ArticlesService(
    private val httpClient: HttpClient
) {
    private val country = "us";
    private val category = "business"
    private val apiKey = "40e095b3d80b490a8107ca0d5b3d2e0e"

    suspend fun fetchArticles(): List<ArticleRaw> {
        val response: ArticlesResponse =
            httpClient.get("https://newsapi.org/v2/top-headlines?country=$country&category=$category&apiKey=$apiKey")
                .body()
        return response.articles
    }
}