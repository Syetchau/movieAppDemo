package com.example.fmovies.repository

import com.example.fmovies.api.ApiService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getMoviesList() = apiService.getMoviesList()

    suspend fun getEpisodeListByMovie(id: Int) = apiService.getMovieEpisodeList(id)
}