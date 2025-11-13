package com.example.dailypulse.articles

import com.example.dailypulse.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ArticlesViewModel : BaseViewModel() {
    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(loading = true))

    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    init {
        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            delay(3000)
            _articlesState.emit(ArticlesState(error = "Something went wrong"))
            delay(1500)
            val articlesFetched = fetchArticles()
            _articlesState.emit(ArticlesState(articles = articlesFetched))
        }
    }


    suspend fun fetchArticles(): List<Article> {
        return mockArticles
    }

    private val mockArticles = listOf(
        Article(
            id = "ar01",
            title = "Stock market today: Live update - CNBC",
            desc = "Futures were hiher in premarket traing was Wall Streed tried to regain its footing.",
            date = "2023-11-09",
            imageUrl = "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?w=600"
        ),
        Article(
            id = "ar02",
            title = "Best iPhone Deals (2023): Carrier Deals, Unloked iPhones",
            desc = "Description 2",
            date = "2023-11-09",
            imageUrl = "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?w=600"
        ),
        Article(
            id = "ar03",
            title = "Article 3",
            desc = "Description 3",
            date = "2023-11-09",
            imageUrl = "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?w=600"
        )
    )

}