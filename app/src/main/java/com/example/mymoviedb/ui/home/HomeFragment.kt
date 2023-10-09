package com.example.mymoviedb.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymoviedb.databinding.FragmentHomeBinding
import com.example.mymoviedb.ui.LoadingStateAdapter
import com.example.mymoviedb.ui.detail.DetailMovieActivity
import com.example.mymoviedb.ui.detail.DetailMovieActivity.Companion.MOVIE_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: MoviePagingAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAction()
        setupViewModel()

    }

    private fun setupAction() {
        binding.apply {
            adapter = MoviePagingAdapter(onItemClick = {
                val intent = Intent(requireContext(), DetailMovieActivity::class.java)
                    .putExtra(MOVIE_ID, it.id.toString())
                startActivity(intent)

            })
            rvMovieList.adapter = adapter.withLoadStateFooter(
                footer =  LoadingStateAdapter { adapter.retry() }
            )
            rvMovieList.setHasFixedSize(true)
            btnErrorLoad.setOnClickListener { adapter.retry() }

            rvMovieList.layoutManager = GridLayoutManager(requireContext(), 2)

            adapter.addLoadStateListener { load ->
                progressbar.isVisible = load.source.refresh is LoadState.Loading
                tvErrorLoad.isVisible = load.source.refresh is LoadState.Error
                btnErrorLoad.isVisible = load.refresh is LoadState.Error
            }
        }
    }

    private fun setupViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movieList.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}