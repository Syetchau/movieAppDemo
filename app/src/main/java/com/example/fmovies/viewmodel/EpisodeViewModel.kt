package com.example.fmovies.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fmovies.helper.Constant
import com.example.fmovies.models.EpisodeItem
import com.example.fmovies.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(private val movieRepository: MovieRepository): ViewModel() {

    private val _response = MutableLiveData<List<EpisodeItem>>()
    val episodeResponse: LiveData<List<EpisodeItem>> get() = _response

    init {
        getEpisodeListOfMovie(Constant.MOVIE_ID)
    }

    private fun getEpisodeListOfMovie(id: Int) = viewModelScope.launch {

        movieRepository.getEpisodeListByMovie(id).let { response ->
            if (response.isSuccessful) {
                _response.postValue(response.body())
                Log.d("Response", ""+response.body())
            } else {
                Log.d("Response Error", "getEpisodeList: ${response.code()}")
            }
        }
    }
}