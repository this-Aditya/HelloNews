package com.aditya.hellonews.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.aditya.hellonews.R
import com.aditya.hellonews.adapters.BreakingNewsAdapter
import com.aditya.hellonews.databinding.FragmentBreakingNewsBinding
import com.aditya.hellonews.models.Article
import com.aditya.hellonews.repository.NewsRepository
import com.aditya.hellonews.utils.constants.Companion.API_KEY
import com.aditya.hellonews.utils.constants.Companion.BASE_URL
import com.aditya.hellonews.viewmodels.breakingNews.BreakingNewsViewModel
import com.aditya.hellonews.viewmodels.breakingNews.BreakingNewsViewModelFactory

private const val TAG = "BreakingNewsFragment"

class BreakingNewsFragment : Fragment() {
    private lateinit var binding: FragmentBreakingNewsBinding
    private lateinit var viewModel: BreakingNewsViewModel
    private val articles = (mutableListOf<Article>())
    private lateinit var breakingNewsAdapter: BreakingNewsAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBreakingNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = NewsRepository()
        val viewModelfactory = BreakingNewsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelfactory).get(BreakingNewsViewModel::class.java)
        createAdapter()
        viewModel.getBreakingNews("Bearer $API_KEY")

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { news ->
            articles.clear()
            articles.addAll(news)
            breakingNewsAdapter.setData(articles)
        })
    }

    private fun createAdapter() {
        breakingNewsAdapter = BreakingNewsAdapter(object : BreakingNewsAdapter.OnItemClicked {
            override fun itemClicked(position: Int) {
               val action = BreakingNewsFragmentDirections.actionBreakingNewsFragmentToArticleNewsFragment(
                    articles[position].url)
                view?.findNavController()?.navigate(action)
            }

        })
        binding.rvBreakingNews.adapter = breakingNewsAdapter
        binding.rvBreakingNews.layoutManager = LinearLayoutManager(requireContext())
    }
}