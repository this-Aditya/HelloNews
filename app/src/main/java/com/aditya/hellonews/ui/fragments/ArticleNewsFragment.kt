package com.aditya.hellonews.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.aditya.hellonews.R
import com.aditya.hellonews.databinding.FragmentArticleNewsBinding


class ArticleNewsFragment : Fragment() {
    val args: ArticleNewsFragmentArgs by navArgs()
    lateinit var binding: FragmentArticleNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticleNewsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = args.webURL
        binding.webView.loadUrl(url)
    }

}