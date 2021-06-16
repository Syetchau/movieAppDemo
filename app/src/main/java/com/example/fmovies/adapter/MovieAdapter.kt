package com.example.fmovies.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.fmovies.MovieDetailActivity
import com.example.fmovies.databinding.MovieLayoutAdapterBinding
import com.example.fmovies.helper.Constant
import com.example.fmovies.models.TvShowItem

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding: MovieLayoutAdapterBinding):
        RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object: DiffUtil.ItemCallback<TvShowItem>() {
        override fun areItemsTheSame(oldItem: TvShowItem, newItem: TvShowItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvShowItem, newItem: TvShowItem): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var movie: List<TvShowItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(MovieLayoutAdapterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = movie[position]

        holder.binding.apply {
            tvMovie.text = currentMovie.name
            ivMovie.load(currentMovie.image.original) {
                crossfade(true)
                crossfade(1000)
            }
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MovieDetailActivity::class.java)
            intent.putExtra(Constant.MOVIE, movie[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = movie.size
}