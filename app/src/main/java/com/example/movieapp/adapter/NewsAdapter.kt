package com.example.movieapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.fragments.NewsFragment
import com.example.movieapp.R
import com.example.movieapp.databinding.NewsItemsBinding
import com.example.movieapp.fragments.news_item
import com.example.movieapp.model.ArticleModel

class NewsAdapter(private val listener: NewsFragment) :
    PagingDataAdapter<ArticleModel, NewsAdapter.NewsViewHolder>(NewsComparator) {

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)!!
        holder.itemsBinding.titleText.text = news.title
        holder.itemsBinding.descText.text = news.description
        Glide.with(holder.itemView.context)
            .load(news.urlToImage)
            .placeholder(R.drawable.btc)
            .into(holder.itemsBinding.imgView)
        holder.itemView.setOnClickListener {
            listener.itemClick(news)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemsBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    class NewsViewHolder(val itemsBinding: NewsItemsBinding) :
        RecyclerView.ViewHolder(itemsBinding.root) {

    }

    object NewsComparator : DiffUtil.ItemCallback<ArticleModel>() {
        override fun areItemsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
            // Id is unique.
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ArticleModel, newItem: ArticleModel): Boolean {
            return oldItem == newItem
        }
    }
}