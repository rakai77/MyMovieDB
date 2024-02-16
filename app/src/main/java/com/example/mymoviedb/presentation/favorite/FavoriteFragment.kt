package com.example.mymoviedb.presentation.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviedb.core.domain.Movies
import com.example.mymoviedb.databinding.FragmentFavoriteBinding
import com.example.mymoviedb.utils.gone
import com.example.mymoviedb.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var adapter: MovieFavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observer()
        initView()

        viewModel.getMovieFavorite()
    }

    private fun initView() {
        binding.apply {
            adapter = MovieFavoriteAdapter(
                onClicked = {
                    viewModel.removeMovieFavorite(it)
                }
            )
            rvMovieList.adapter = adapter
            rvMovieList.layoutManager = LinearLayoutManager(requireContext())
            rvMovieList.setHasFixedSize(true)
        }
    }

    private fun observer() {
        viewModel.favMovie.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> favoriteUiState(state) }
            .launchIn(lifecycleScope)
    }

    private fun favoriteUiState(state: FavoriteMovieUiState) {
        when(state) {
            is FavoriteMovieUiState.Loading -> handleLoading(state.loading)
            is FavoriteMovieUiState.Success -> initializeData(state.movies)
            is FavoriteMovieUiState.SuccessDelete -> {
                Log.d("cek", "favoriteUiState: masuk?")
                Toast.makeText(requireContext(), "Item Deleted", Toast.LENGTH_SHORT).show()
                viewModel.getMovieFavorite()
            }
            else -> Unit
        }
    }

    private fun initializeData(movies: List<Movies>) {
        if (movies.isEmpty()) {
            binding.rvMovieList.gone()
            binding.emptyHome.root.visible()
        } else {
            binding.emptyHome.root.gone()
            binding.rvMovieList.visible()
            adapter.submitList(movies)
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        when(isLoading){
            true -> binding.pbLoading.visible()
            false -> binding.pbLoading.gone()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}