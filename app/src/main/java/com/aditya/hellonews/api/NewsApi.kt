package com.aditya.hellonews.api

import com.aditya.hellonews.models.News
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsApi {

@GET("/v2/top-headlines")
suspend fun getBreakingNews(
@Header("Authorization") Authorization: String,
@Query("country") country: String,
@Query("pageSize") pageSize: Int
):Response<News>


}