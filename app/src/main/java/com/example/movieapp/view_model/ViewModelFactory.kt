package com.example.movieapp.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.repository.NewsRepository
import java.lang.IllegalArgumentException

class ViewModelFactory(private val mainRepository: NewsRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>) : T{
        return if(modelClass.isAssignableFrom(NewsViewModel::class.java)){
            NewsViewModel(this.mainRepository) as T
        }
        else{
            throw IllegalArgumentException("Not Found")
        }
    }
}