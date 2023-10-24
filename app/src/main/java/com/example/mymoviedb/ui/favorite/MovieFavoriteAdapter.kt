package com.example.mymoviedb.ui.favorite

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mymoviedb.base.BaseAdapter
import com.example.mymoviedb.data.local.MovieEntity
import com.example.mymoviedb.databinding.ItemMovieBinding

class MovieFavoriteAdapter : BaseAdapter<MovieEntity, ItemMovieBinding>(
    ItemMovieBinding::inflate,
    onItemBind = {item, binding, itemView ->
        Glide.with(itemView)
            .load("https://image.tmdb.org/t/p/w500/" + item.posterPath)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.imgMoviePoster)
        binding.tvMovieTitle.text = item.title
        binding.tvRatting.text = item.voteAverage.toString()
    }
)