package com.example.dailypulse.sources.presentation

import com.example.dailypulse.BaseViewModel
import com.example.dailypulse.sources.application.SourcesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SourcesViewModel(
    private val useCase: SourcesUseCase
) : BaseViewModel() {
    private val _sourcesState: MutableStateFlow<SourcesState> =
        MutableStateFlow(SourcesState(isLoading = true, sources = emptyList(), error = null))

    val sourcesState: StateFlow<SourcesState> get() = _sourcesState

    init {
        getSources()
    }

    fun getSources(forceFetch: Boolean = false) {
        println(">>>>>>>>>>debug: fetchSources forceFetch:$forceFetch")
        scope.launch {
            _sourcesState.emit(
                SourcesState(
                    isLoading = true,
                    sources = _sourcesState.value.sources,
                )
            )
            delay(3000)
            val sourcesFetched = useCase.getAllSources(forceFetch)
            _sourcesState.emit(SourcesState(sources = sourcesFetched))
        }
    }
}