package com.example.movieapp.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.movieapp.model.ArticleModel
import com.example.movieapp.repository.NewsRepository

class NewsViewModel(private val mainRepository: NewsRepository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun getMovieList(): LiveData<PagingData<ArticleModel>> {
        return mainRepository.getPosts().cachedIn(viewModelScope)

    }
//    val postLiveData = MutableLiveData<List<ArticleModel>>()
//    val errorMessage = MutableLiveData<String>()
//    private var job : Job? = null
//    val loading = MutableLiveData<Boolean>()

//    private val exceptionHandler = CoroutineExceptionHandler{ _, throwable ->
//        errorPost("Exception Handled ${throwable.localizedMessage}")
//    }
//    init {
//        getPost()
//    }
//
//    fun getPost(){
//        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch{
//            val response = mainRepository.getPosts(
//                "bitcoins",
//                "c4809605dd6a44a2a8493f96a198acd7",
//                positions
//            )
//            withContext(Dispatchers.Main){
//                    if(response.isSuccessful){
//                            postLiveData.postValue(response.body()?.articles)
//                        loading.value = false
//                    } else{
//                        errorPost("Error : ${response.message()}")
//                    }
//            }
//        }
//
//    }
//    private fun errorPost(error : String){
//        errorMessage.value = error
//        loading.value = false
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        job?.cancel()
//    }

}