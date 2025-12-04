package com.example.dailypulse.articles.presentation

import com.example.dailypulse.BaseViewModel
import com.example.dailypulse.articles.application.ArticlesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ArticlesViewModel(
    private val useCase: ArticlesUseCase

) : BaseViewModel() {
    private val _articlesState: MutableStateFlow<ArticlesState> =
        MutableStateFlow(ArticlesState(loading = true))

    val articlesState: StateFlow<ArticlesState> get() = _articlesState

    init {
        getArticles()
    }

    fun getArticles(forceFetch: Boolean = false) {
        println(">>>>>>>>>>debug: fetchArticles forceFetch:$forceFetch")
        scope.launch {
            _articlesState.emit(
                ArticlesState(
                    loading = true,
                    articles = _articlesState.value.articles
                )
            )
            delay(3000)
            val articlesFetched = useCase.fetchArticles(forceFetch)
            _articlesState.emit(ArticlesState(articles = articlesFetched))
        }
    }


}