package com.aditya.hellonews.models

data class News(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)