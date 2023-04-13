package com.aditya.hellonews.viewmodels.breakingNews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aditya.hellonews.repository.NewsRepository

class BreakingNewsViewModelFactory(private val repository: NewsRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BreakingNewsViewModel(repository) as T
    }
}