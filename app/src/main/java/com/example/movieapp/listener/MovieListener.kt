package com.example.movieapp.listener

import com.example.movieapp.data.MovieData

interface MovieListener {
    fun itemClick(movie_items: MovieData)
}