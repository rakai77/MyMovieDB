package com.example.mymoviedb.presentation.home

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mymoviedb.base.BasePagingAdapter
import com.example.mymoviedb.core.domain.MovieItem
import com.example.mymoviedb.databinding.ItemMovieBinding

class MoviePagingAdapter(
    private val onItemClick: (MovieItem) -> Unit
) : BasePagingAdapter<MovieItem, ItemMovieBinding>(
    ItemMovieBinding::inflate,
    onItemBind = { item, binding, itemView ->
        Glide.with(itemView)
            .load("https://image.tmdb.org/t/p/w500/" + item.posterPath)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.imgMoviePoster)
        binding.tvMovieTitle.text = item.title
        binding.tvRatting.text = item.voteAverage.toString()

        itemView.setOnClickListener {
            onItemClick.invoke(item)
        }
    }
)