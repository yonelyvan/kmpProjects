package com.example.dailypulse.articles.application

import com.example.dailypulse.articles.data.ArticleRaw
import com.example.dailypulse.articles.data.ArticlesRepository
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

class ArticlesUseCase(
    private val repo: ArticlesRepository
) {
    suspend fun fetchArticles(forceFetch: Boolean = false): List<Article> {
        val articleRaw = repo.getArticles(forceFetch)
        return mapArticles(articleRaw)
    }


    private fun mapArticles(articleRaw: List<ArticleRaw>): List<Article> {
        return articleRaw.map { raw ->
            Article(
                title = raw.title ?: "Click to find more",
                desc = raw.desc ?: "Click to find more",
                //date = raw.publishedAt ?: "Click to find more",
                getDaysAgoString(raw.publishedAt),
                imageUrl = raw.imageUrl
                    ?: "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?w=600"
            )
        }
    }


    private fun getDaysAgoString(date: String?): String {
        val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
        val days = today.daysUntil(
            Instant.parse(date ?: today.toString())
                .toLocalDateTime(TimeZone.currentSystemDefault()).date
        )

        val result = when {
            abs(days) > 1 -> "${abs(days)} days ago"
            abs(days) == 1 -> "Yesterday"
            else -> "Today"
        }

        return result
    }


}