package com.example.mymoviedb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mymoviedb.data.response.MovieResponse
import com.example.mymoviedb.databinding.ItemMovieBinding

class MoviePagingAdapter(private val onItemClick: (MovieResponse.ResultsItem) -> Unit) : PagingDataAdapter<MovieResponse.ResultsItem, MoviePagingAdapter.MovieViewHolder>(DIFF_CALLBACK){

    inner class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieResponse.ResultsItem) {
            with(binding) {
                Glide.with(itemView)
                    .load("https://image.tmdb.org/t/p/w500/" + item.posterPath)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imgMoviePoster)
                tvMovieTitle.text = item.title
                tvRatting.text = item.voteAverage.toString()
            }

            itemView.setOnClickListener {
                onItemClick.invoke(item)
            }
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieResponse.ResultsItem>() {
            override fun areItemsTheSame(
                oldItem: MovieResponse.ResultsItem,
                newItem: MovieResponse.ResultsItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: MovieResponse.ResultsItem,
                newItem: MovieResponse.ResultsItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}