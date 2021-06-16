package com.example.fmovies.models

import java.io.Serializable

data class Links(
    val previousepisode: Previousepisode,
    val self: Self
): Serializable