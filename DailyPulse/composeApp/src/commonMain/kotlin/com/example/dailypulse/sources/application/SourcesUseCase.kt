package com.example.dailypulse.sources.application

import com.example.dailypulse.sources.data.SourceRaw
import com.example.dailypulse.sources.data.SourcesRepository

class SourcesUseCase(
    private val repo: SourcesRepository
) {

    suspend fun getAllSources(forceFetch: Boolean = false): List<Source> {
        val sourceRaw = repo.getSources(forceFetch)
        return mapSources(sourceRaw)
    }


    private fun mapSources(sourceRaw: List<SourceRaw>): List<Source> {
        return sourceRaw.map { raw ->
            Source(
                raw.id ?: "Name",
                raw.name ?: "Name",
                raw.desc ?: "description",
                raw.language ?: "Language",
                raw.country ?: "Country"
            )
        }
    }
}