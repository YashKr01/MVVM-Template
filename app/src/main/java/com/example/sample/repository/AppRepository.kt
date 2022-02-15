package com.example.sample.repository

import androidx.lifecycle.MutableLiveData
import com.example.sample.data.database.TaskDao
import com.example.sample.data.network.ApiInterface
import com.example.sample.model.CategoryEntity
import com.example.sample.model.NewsArticle
import com.example.sample.model.TaskEntity
import com.example.sample.model.WeatherResponse
import com.example.sample.utils.Constants.API_KEY
import com.example.sample.utils.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val api: ApiInterface,
    private val dao: TaskDao
) {

    private val newsLiveData = MutableLiveData<Resource<List<NewsArticle>>>()
    val newsList get() = newsLiveData

    private val weatherLiveData = MutableLiveData<Resource<WeatherResponse>>()
    val weatherResponse get() = weatherLiveData

    suspend fun getNewsList() {
        newsLiveData.postValue(Resource.Loading(null))
        try {
            val response =
                api.getTopHeadlines("https://newsapi.org/v2/top-headlines?country=in&apiKey=$API_KEY")
                    .articles
            newsLiveData.postValue(Resource.Success(response))
        } catch (e: IOException) {
            newsLiveData.postValue(Resource.Error("ERROR : ${e.message.toString()}", null))
        } catch (e: HttpException) {
            newsLiveData.postValue(Resource.Error("ERROR : ${e.message.toString()}", null))
        }
    }

    suspend fun getWeatherResponse(name: String) {
        newsLiveData.postValue(Resource.Loading(null))
        try {
            val response = api.getWeatherResponse(name)
            weatherLiveData.postValue(Resource.Success(response))
        } catch (e: IOException) {
            weatherLiveData.postValue(Resource.Error("ERROR : ${e.message.toString()}", null))
        } catch (e: HttpException) {
            weatherLiveData.postValue(Resource.Error("ERROR : ${e.message.toString()}", null))
        }
    }

    fun getAllTasks() = dao.getAllTasks()

    suspend fun insertArticle(task: TaskEntity) = dao.insertTask(task)

    suspend fun deleteArticle(task: TaskEntity) = dao.deleteTask(task)

    fun getAllCategories() = dao.getAllCategories()

    suspend fun insertCategory(categoryEntity: CategoryEntity) = dao.insertCategory(categoryEntity)

}