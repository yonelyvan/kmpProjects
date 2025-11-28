package com.example.dailypulse.articles

import com.example.dailypulse.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ArticlesViewModel(
    private val useCase: ArticlesUseCase

) : BaseViewModel() {
    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(loading = true))

    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    ///private val useCase: ArticlesUseCase

    init {
        /*
        val httpClient = HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
        }
        val service = ArticlesService(httpClient)
        useCase = ArticlesUseCase(service)
        */

        getArticles()
    }

    private fun getArticles() {
        scope.launch {
            val articlesFetched = useCase.fetchArticles()
            _articlesState.emit(ArticlesState(articles = articlesFetched))
        }
    }


}