package com.example.dailypulse.sources.presentation

import com.example.dailypulse.sources.application.Source

class SourcesState(
    val sources: List<Source> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)