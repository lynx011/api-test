package com.example.movieapp.paging

import android.content.res.Resources
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.api_data.NewsApi
import com.example.movieapp.model.ArticleModel
import okio.IOException
import org.json.JSONException
import retrofit2.HttpException
import java.lang.Exception

class NewsPagingSource(private val newsApi: NewsApi) : PagingSource<Int, ArticleModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleModel> {
        val position = params.key ?: 1
        return try {
            val response =
                newsApi.getPosts("bitcoins", "c4809605dd6a44a2a8493f96a198acd7", 20, position)
            Log.d("News Count ", response.body()!!.articles.size.toString())
            LoadResult.Page(
                data = response.body()!!.articles,
                prevKey = if (position == 1) null else -1,
                nextKey = position.plus(1)
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (exception: JSONException) {
            LoadResult.Error(exception)
        } catch (exception: UnknownError) {
            LoadResult.Error(exception)
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: Resources.NotFoundException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}