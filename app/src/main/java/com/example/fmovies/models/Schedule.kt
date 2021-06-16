package com.example.fmovies.models

import java.io.Serializable

data class Schedule(
    val days: List<String>,
    val time: String
): Serializable