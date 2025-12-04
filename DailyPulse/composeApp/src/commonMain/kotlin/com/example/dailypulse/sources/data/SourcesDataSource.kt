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
            sourceRaw.name ?: "name",
            sourceRaw.desc ?: "Description ...",
            sourceRaw.url ?: "url",
            sourceRaw.category ?: "Category",
            sourceRaw.language ?: "Lan",
            sourceRaw.country ?: "PE"
        )
    }

    private fun mapToSourceRaw(
        name: String,
        desc: String?,
        url: String,
        category: String?,
        language: String?,
        country: String?
    ): SourceRaw =
        SourceRaw(name, desc, url, category, language, country)
}