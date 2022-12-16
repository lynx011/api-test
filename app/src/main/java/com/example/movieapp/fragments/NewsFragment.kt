package com.example.movieapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.adapter.NewsAdapter
import com.example.movieapp.R
import com.example.movieapp.adapter.LoadMoreAdapter
import com.example.movieapp.api_data.NewsApi
import com.example.movieapp.databinding.FragmentNewsBinding
import com.example.movieapp.listener.NewsListener
import com.example.movieapp.model.ArticleModel
import com.example.movieapp.network_connection.CheckConnection
import com.example.movieapp.repository.NewsRepository
import com.example.movieapp.view_model.NewsViewModel
import com.example.movieapp.view_model.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest

class NewsFragment : Fragment(), NewsListener {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var postViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private val checkConnection by lazy { context?.let { CheckConnection(it) } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        newsAdapter = NewsAdapter(this)
        binding.recyclerView.adapter = newsAdapter

        val retrofitService = NewsApi.getInstance()
        val mainRepository = NewsRepository(retrofitService)
        postViewModel =
            ViewModelProvider(this, ViewModelFactory(mainRepository))[NewsViewModel::class.java]

        postViewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
        binding.apply {
            newsAdapter.addLoadStateListener { state ->
                errorView.isVisible = state.source.refresh is LoadState.Error
            }

            // loading paging data
            loadData()

            lifecycleScope.launchWhenCreated {
                newsAdapter.loadStateFlow.collectLatest { isRefresh ->
                    val state = isRefresh.refresh
                    progressBar.isVisible = state is LoadState.Loading
                }
            }
            recyclerView.adapter = newsAdapter.withLoadStateFooter(
                LoadMoreAdapter {
                    newsAdapter.retry()
                }
            )
            swipeToRefresh.setOnRefreshListener {
                newsAdapter.refresh()
                swipeToRefresh.isRefreshing = false
                lifecycleScope.launchWhenCreated {
                    newsAdapter.loadStateFlow.collectLatest { isRefresh ->
                        val state = isRefresh.refresh
                        recyclerView.isVisible = state !is LoadState.Loading
                        errorView.isVisible = false
                        errorView.isVisible = newsAdapter.itemCount < 1
                        if (state is LoadState.Error) {
                            recyclerView.isVisible = true
                        } else {
                            recyclerView.isVisible = true
                            errorView.isVisible = false
                        }
                    }
                }
            }
            checkConnection!!.observe(viewLifecycleOwner) { isConnected ->
                if (isConnected) {
                    Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show()
                } else {
                    errorView.isVisible = newsAdapter.itemCount < 1
                    Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show()
                    recyclerView.isVisible = newsAdapter.itemCount > 0
                }
            }
            tryAgainBtn.setOnClickListener {
                loadData()
                lifecycleScope.launchWhenCreated {
                    newsAdapter.loadStateFlow.collectLatest { isRefresh ->
                        val state = isRefresh.refresh
                        recyclerView.isVisible = state !is LoadState.Error
                    }
                }
            }
        }
    }

    private fun loadData() {
        lifecycleScope.launchWhenCreated {
            postViewModel.getMovieList().observe(viewLifecycleOwner) {
                it?.let { pagingData ->
                    newsAdapter.submitData(lifecycle, pagingData)
                }
            }
        }
    }

    override fun itemClick(news_items: ArticleModel) {
        findNavController().navigate(
            R.id.newsDetailsFragment, bundleOf(
                news_item to news_items
            )
        )
    }
}