package com.aditya.hellonews.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aditya.hellonews.databinding.ActivityRowBinding
import com.aditya.hellonews.diffutil.NewsDiffutil
import com.aditya.hellonews.models.Article
import com.squareup.picasso.Picasso


class BreakingNewsAdapter(private val onItemClicked: OnItemClicked) : RecyclerView.Adapter<BreakingNewsAdapter.BreakingNewsViewHolder>() {
    private var articles: MutableList<Article> = mutableListOf()
    private lateinit var binding: ActivityRowBinding

    interface OnItemClicked{
        fun itemClicked(position: Int)
    }

    class BreakingNewsViewHolder(private val binding: ActivityRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindNews(article: Article) {
            binding.tvDescription.text = article.description
            binding.tvPublishedAt.text = article.publishedAt
            binding.tvSource.text = article.source.name
            binding.tvTitle.text = article.title
            val imageUri = article.urlToImage
            Picasso.get().load(imageUri).into(binding.ivArticleImage);

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreakingNewsViewHolder {
        binding = ActivityRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BreakingNewsViewHolder(binding)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: BreakingNewsViewHolder, position: Int) {
        val article = articles[position]
        holder.bindNews(article)
        binding.clRowNews.setOnClickListener {
            onItemClicked.itemClicked(position)
        }
    }

    fun setData(articlesUpdated: MutableList<Article>) {
       val diffUtil = NewsDiffutil(articles,articlesUpdated)
       val diffResults =DiffUtil.calculateDiff(diffUtil)
       articles = articlesUpdated
       diffResults.dispatchUpdatesTo(this)
    }

}