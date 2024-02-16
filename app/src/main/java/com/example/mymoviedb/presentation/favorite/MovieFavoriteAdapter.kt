package com.example.mymoviedb.presentation.favorite

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mymoviedb.base.BaseAdapter
import com.example.mymoviedb.core.domain.Movies
import com.example.mymoviedb.databinding.ItemMovieFavoriteBinding

class MovieFavoriteAdapter(private val onClicked: (Int) -> Unit) :
    BaseAdapter<Movies, ItemMovieFavoriteBinding>(
        ItemMovieFavoriteBinding::inflate,
        onItemBind = { item, binding, itemView ->
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500/" + item.posterPath)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imgMoviePoster)
            binding.tvMovieTitle.text = item.title
            binding.tvRatting.text = item.voteAverage.toString()
            binding.btnRemove.setOnClickListener {
                onClicked.invoke(item.id)
            }
        }
    )