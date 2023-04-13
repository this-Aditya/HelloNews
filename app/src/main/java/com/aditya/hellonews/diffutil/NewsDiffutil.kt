package com.aditya.hellonews.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.aditya.hellonews.models.Article

class NewsDiffutil(
    private val oldNews: List<Article>,
    private val newNews: List<Article>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldNews.size
    override fun getNewListSize(): Int = newNews.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNews[oldItemPosition].title == newNews[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldNews[oldItemPosition] == newNews[newItemPosition]
    }
}