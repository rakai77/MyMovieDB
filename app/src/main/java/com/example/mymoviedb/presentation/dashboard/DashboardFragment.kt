package com.example.mymoviedb.presentation.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymoviedb.databinding.FragmentDashboardBinding
import com.example.mymoviedb.utils.LoadingStateAdapter
import com.example.mymoviedb.presentation.detail.DetailMovieActivity
import com.example.mymoviedb.presentation.home.MoviePagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var adapter: MoviePagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupAction()
        setupViewModel()
        setupListener()
    }

    private fun setupAction() {
        binding.apply {
            adapter = MoviePagingAdapter(onItemClick = {
                val intent = Intent(requireContext(), DetailMovieActivity::class.java)
                    .putExtra(DetailMovieActivity.MOVIE_ID, it.id.toString())
                startActivity(intent)

            })
            rvSearchMovie.adapter = adapter.withLoadStateFooter(
                footer =  LoadingStateAdapter { adapter.retry() }
            )
            rvSearchMovie.setHasFixedSize(true)

            rvSearchMovie.layoutManager = GridLayoutManager(requireContext(), 2)

            adapter.addLoadStateListener { loadState ->
                progressbar.isVisible = loadState.source.refresh is LoadState.Loading
                rvSearchMovie.isVisible = loadState.source.refresh is LoadState.NotLoading

                adapter.let {
                    if (loadState.source.refresh is LoadState.NotLoading && it.itemCount < 1) {
                        rvSearchMovie.isVisible = false
                        emptyHome.root.isVisible = true
                    } else {
                        emptyHome.root.isVisible = false
                    }
                }
            }
        }
    }

    private fun setupListener() {
        binding.apply {
            svHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    viewModel.onSearch(newText)
                    return true
                }
            })
        }
    }

    private fun setupViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadMovieBySearch().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}