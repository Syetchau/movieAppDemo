package com.example.fmovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fmovies.databinding.MovieEpisodeAdapterBinding
import com.example.fmovies.models.EpisodeItem
import java.lang.StringBuilder

class EpisodeAdapter: RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    inner class EpisodeViewHolder(val binding: MovieEpisodeAdapterBinding):
            RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object: DiffUtil.ItemCallback<EpisodeItem>() {
        override fun areItemsTheSame(oldItem: EpisodeItem, newItem: EpisodeItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EpisodeItem, newItem: EpisodeItem): Boolean {
            return newItem == oldItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var episode: List<EpisodeItem>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(MovieEpisodeAdapterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
       val currentEpisode = episode[position]

        holder.binding.apply {
            val episodeId = StringBuilder()
                .append(currentEpisode.season)
                .append(" - ")
                .append(currentEpisode.number)
            tvMovieEpisode.text = episodeId
            tvMovieEpisodeTitle.text = currentEpisode.name
        }
    }

    override fun getItemCount() = episode.size
}