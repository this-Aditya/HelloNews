package com.aditya.hellonews.repository

import com.aditya.hellonews.api.RetrofitInstance
import com.aditya.hellonews.models.Article
import com.aditya.hellonews.models.News
import retrofit2.Response

class NewsRepository {

    suspend fun getBreakingNews(auth: String, country: String, pageSize: Int):Response<News> =
        RetrofitInstance.api.getBreakingNews(auth, country, pageSize)
}