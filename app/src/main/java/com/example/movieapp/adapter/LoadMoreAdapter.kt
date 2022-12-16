package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.databinding.LoadMoreBinding

class LoadMoreAdapter(private val reload : () -> Unit) : LoadStateAdapter<LoadMoreAdapter.LoadViewHolder>() {
    lateinit var binding: LoadMoreBinding

    inner class LoadViewHolder(reload: () -> Unit) : RecyclerView.ViewHolder(binding.root){
        init {
            binding.retryBtn.setOnClickListener { reload() }
        }
        fun setData(state : LoadState){
            binding.apply {
                lmProgressBar.isVisible = state is LoadState.Loading
                lmText.isVisible = state is LoadState.Error
                retryBtn.isVisible = state is LoadState.Error
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadViewHolder {
        binding = LoadMoreBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LoadViewHolder(reload)
    }
    override fun onBindViewHolder(holder: LoadViewHolder, loadState: LoadState) {
        holder.setData(loadState)
    }

}