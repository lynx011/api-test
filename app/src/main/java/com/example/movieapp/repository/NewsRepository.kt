package com.example.movieapp.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.movieapp.api_data.NewsApi
import com.example.movieapp.model.ArticleModel
import com.example.movieapp.paging.NewsPagingSource

class NewsRepository(private val newsApi: NewsApi?) {

    fun getPosts(): LiveData<PagingData<ArticleModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                NewsPagingSource(newsApi!!)
            },
            initialKey = 1,
        ).liveData
    }
}