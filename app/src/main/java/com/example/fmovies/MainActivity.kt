package com.example.fmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fmovies.adapter.MovieAdapter
import com.example.fmovies.databinding.ActivityMainBinding
import com.example.fmovies.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        initData()
    }

    private fun initData() {
        movieAdapter = MovieAdapter()

        binding.rvMovie.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(this@MainActivity,2)
            setHasFixedSize(true)
        }

        viewModel.movieResponse.observe(this, { moviesList->
            movieAdapter.movie = moviesList
        })
    }
}