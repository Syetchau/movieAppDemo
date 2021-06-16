package com.example.fmovies.models

import java.io.Serializable

data class TvShowItem(
    val _links: Links,
    val averageRuntime: Int,
    val dvdCountry: Any,
    val externals: Externals,
    val genres: List<String>,
    val id: Int,
    val image: ImageX,
    val language: String,
    val name: String,
    val network: Network,
    val officialSite: String,
    val premiered: String,
    val rating: Rating,
    val runtime: Int,
    val schedule: Schedule,
    val status: String,
    val summary: String,
    val type: String,
    val updated: Int,
    val url: String,
    val webChannel: Any,
    val weight: Int
): Serializable