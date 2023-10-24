package com.example.mymoviedb.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mymoviedb.R
import com.example.mymoviedb.data.BaseResult
import com.example.mymoviedb.data.local.MovieEntity
import com.example.mymoviedb.data.response.MovieDetailResponse
import com.example.mymoviedb.databinding.ActivityDetailMovieBinding
import com.example.mymoviedb.databinding.ItemMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding

    private val viewModel: DetailMovieViewModel by viewModels()
    private var isLoading: Boolean? = false
    private var movieId: String? = null
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        movieId = intent.getStringExtra(MOVIE_ID)
        Log.d("cek id", "onCreate: $movieId")

        observer()

        viewModel.getMovieCast(movieId!!)
    }

    private fun observer() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collect {
                        when (it) {
                            is BaseResult.Loading -> isLoading(true)
                            is BaseResult.Success -> {
                                isLoading(false)
                                handleMovie(it.data)
                            }

                            is BaseResult.Error -> {
                                isLoading(false)
                                Toast.makeText(
                                    this@DetailMovieActivity,
                                    it.exception.message,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun handleMovie(data: MovieDetailResponse?) {
        binding.apply {
            Glide.with(this@DetailMovieActivity)
                .load("https://image.tmdb.org/t/p/w500/" + data?.posterPath)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imgMoviePoster)
            tvReleaseOn.text = data?.releaseDate
            tvOverview.text = data?.overview
            tvRatting.text = data?.voteAverage.toString()

            imgFavorite.setOnClickListener {
                if (data != null) {
                    viewModel.addFavorite(
                        MovieEntity(
                            id = data.id,
                            title = data.title,
                            posterPath = data.posterPath,
                            voteAverage = data.voteAverage,
                            isFavorite = true
                        )
                    )
                }
                isFavorite = true
            }

            setStatusFavorite(isFavorite)

        }

    }

    private fun isLoading(boolean: Boolean) {
        binding.apply {
            if (boolean) {
                isLoading = true
                progressbar.visibility = View.VISIBLE
            } else {
                isLoading = false
                progressbar.visibility = View.GONE
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.imgFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        } else {
            binding.imgFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border))
        }
    }

    companion object {
        const val MOVIE_ID = "MOVIE-ID"
    }

}