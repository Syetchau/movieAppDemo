package com.example.fmovies.api

import com.example.fmovies.helper.Constant.END_POINT
import com.example.fmovies.models.Episode
import com.example.fmovies.models.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(END_POINT)
    suspend fun getMoviesList(): Response<TvShowResponse>

    @GET("shows/{id}/episodes")
    suspend fun getMovieEpisodeList(@Path("id") id: Int): Response<Episode>
}