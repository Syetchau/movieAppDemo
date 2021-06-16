package com.example.fmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.fmovies.adapter.EpisodeAdapter
import com.example.fmovies.databinding.ActivityMovieDetailBinding
import com.example.fmovies.helper.Constant
import com.example.fmovies.models.TvShowItem
import com.example.fmovies.viewmodel.EpisodeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val viewModel: EpisodeViewModel by viewModels()
    private lateinit var episodeAdapter: EpisodeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        initData()
    }

    private fun initData() {
        val movie = intent.getSerializableExtra(Constant.MOVIE) as? TvShowItem
        episodeAdapter = EpisodeAdapter()
        Constant.MOVIE_ID = movie!!.id

        binding.apply {
            val movieDuration = StringBuilder()
                .append(movie.runtime)
                .append(" ")
                .append("mins")

            val movieSchedule = StringBuilder()
                .append(movie.schedule.days[0])
                .append(",")
                .append(movie.schedule.time)

            ivMovieDetail.load(movie.image.original){
                crossfade(1000)
                crossfade(true)
            }

            tvMovieTitle.text = movie.name
            tvMovieRating.text = movie.rating.average.toString()
            tvMovieDuration.text = movieDuration

            tvMovieGenre.text  = movie.genres.toString()
                .replace("[", "")
                .replace("]", "")

            tvMovieCountry.text = movie.network.country.name
            tvMovieLanguage.text = movie.language
            tvMovieSchedule.text = movieSchedule
            tvMovieStatus.text = movie.status

            tvMovieDescription.text = movie.summary
                .replace("<p>", "")
                .replace("</p>", "")
                .replace("<b>", "")
                .replace("</b>", "")
                .replace("<i>","")
                .replace("</i>","")

            rvMovieEpisode.apply {
                adapter = episodeAdapter
                layoutManager = LinearLayoutManager(
                    this@MovieDetailActivity,
                    LinearLayoutManager.VERTICAL,
                    false)
                setHasFixedSize(true)
            }
        }

        viewModel.episodeResponse.observe(this, { episodeList ->
            episodeAdapter.episode = episodeList
        })
    }
}