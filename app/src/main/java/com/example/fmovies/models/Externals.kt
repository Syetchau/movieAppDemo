package com.example.fmovies.models

import java.io.Serializable

data class Externals(
    val imdb: String,
    val thetvdb: Int,
    val tvrage: Int
): Serializable