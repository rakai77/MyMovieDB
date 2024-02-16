package com.example.mymoviedb.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mymoviedb.core.domain.MovieDetail
import com.example.mymoviedb.core.domain.Movies
import com.example.mymoviedb.databinding.ActivityDetailMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okio.IOException

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding

    private val viewModel: DetailMovieViewModel by viewModels()
    private var isLoading: Boolean? = false
    private var movieId: String? = null

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
        viewModel.state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> detailMovieUiState(state) }
            .launchIn(lifecycleScope)
    }

    private fun detailMovieUiState(state: DetailMovieUiState) {
        when(state) {
            is DetailMovieUiState.Loading -> isLoading(state.loading)
            is DetailMovieUiState.Success -> initializeData(state.movieDetail)
            is DetailMovieUiState.Error -> handleError(state.error)
            is DetailMovieUiState.SuccessFavorite -> addMovieToFavorite(state.movie)
            else -> Unit
        }
    }

    private fun addMovieToFavorite(movie: Movies) {
        Toast.makeText(this, "${movie.title} Added to Favorite", Toast.LENGTH_SHORT).show()
    }

    private fun handleError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    private fun initializeData(data: MovieDetail) {
        binding.apply {
            Glide.with(this@DetailMovieActivity)
                .load("https://image.tmdb.org/t/p/w500/" + data.posterPath)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imgMoviePoster)
            tvReleaseOn.text = data.releaseDate
            tvOverview.text = data.overview
            tvRatting.text = data.voteAverage.toString()

            imgFavorite.setOnClickListener {
                viewModel.addMovieFavorite(
                    Movies(
                        data.id,
                        data.title,
                        data.posterPath,
                        data.voteAverage,
                        true
                    )
                )
            }
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

    companion object {
        const val MOVIE_ID = "MOVIE-ID"
    }

}