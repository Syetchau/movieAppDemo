package com.example.fmovies.models

import java.io.Serializable

data class Network(
    val country: Country,
    val id: Int,
    val name: String
): Serializable