package com.example.dailypulse.sources.data

import example.dailypulse.db.DailyPulseDatabase

class SourcesDataSource(private val database: DailyPulseDatabase) {

    fun getAllSources(): List<SourceRaw> =
        database.dailyPulseDatabaseQueries.selectAllSources(::mapToSourceRaw).executeAsList()

    fun insertSources(sources: List<SourceRaw>) {
        database.dailyPulseDatabaseQueries.transaction {
            sources.forEach { sourceRaw ->
                insertSource(sourceRaw)
            }
        }
    }

    fun clearSources() =
        database.dailyPulseDatabaseQueries.removeAllSources()

    private fun insertSource(sourceRaw: SourceRaw) {
        database.dailyPulseDatabaseQueries.insertSource(
            sourceRaw.id ?: "id",
            sourceRaw.id ?: "name",
            sourceRaw.desc ?: "Description ...",
            sourceRaw.language ?: "Lan",
            sourceRaw.country ?: "Lan",
        )
    }

    private fun mapToSourceRaw(
        name: String,
        desc: String,
        url: String,
        category: String,
        language: String,
    ): SourceRaw =
        SourceRaw(name, desc, url, language, category)
}