package com.aditya.hellonews.viewmodels.breakingNews

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.hellonews.models.News
import com.aditya.hellonews.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response

private const val TAG = "BreakingNewsViewModel"

class BreakingNewsViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _breakingNews = MutableLiveData<List<News>>()
    val breakingNews: LiveData<List<News>>
        get() = _breakingNews

    fun getBreakingNews(auth: String) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(6000)
            try {
                val response: Response<List<News>> = repository.getBreakingNews(auth, "in", 20)
                if (response.isSuccessful) {
                    _breakingNews.value = response.body()
                } else {
                    Log.i(TAG, "code: ${response.code()} message: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.i(TAG, "${e.message}")
            }
        }
    }
}