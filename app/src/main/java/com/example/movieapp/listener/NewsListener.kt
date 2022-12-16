package com.example.movieapp.listener

import com.example.movieapp.model.ArticleModel

interface NewsListener {
    fun itemClick(news_items : ArticleModel)
}