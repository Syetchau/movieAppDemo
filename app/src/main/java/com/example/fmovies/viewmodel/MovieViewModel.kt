package com.example.fmovies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fmovies.models.TvShowItem
import com.example.fmovies.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val movieRepository: MovieRepository): ViewModel() {

    private val _response = MutableLiveData<List<TvShowItem>>()
    val movieResponse: LiveData<List<TvShowItem>> get() = _response

    init {
        getMovieList()
    }

    private fun getMovieList() = viewModelScope.launch {

        movieRepository.getMoviesList().let { response ->
            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                Log.d("Response Error", "getMoviesList: ${response.code()}")
            }
        }
    }
}