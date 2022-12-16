package com.example.movieapp.api_data

import com.example.movieapp.model.NewsModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getPosts(
        @Query("q") uid: String,
        @Query("apiKey") latitude: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") position: Int
    ): Response<NewsModel>

    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"
        private var newsApi: NewsApi? = null
        fun getInstance(): NewsApi? {
            if (newsApi == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                newsApi = retrofit.create(NewsApi::class.java)
            }
            return newsApi
        }
    }
}