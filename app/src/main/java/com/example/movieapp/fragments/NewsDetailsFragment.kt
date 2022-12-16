package com.example.movieapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.databinding.FragmentNewsDetailsBinding
import com.example.movieapp.model.ArticleModel
import com.example.movieapp.view_model.NewsViewModel

const val news_item = "news_items"

class NewsDetailsFragment : Fragment() {
    private var news: ArticleModel? = null
    private lateinit var webView: WebView
    private lateinit var webViewClient: WebViewClient
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var binding: FragmentNewsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            news = it.getParcelable(news_item)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsDetailsBinding.inflate(layoutInflater)
        return binding.root
        newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (news != null) {
            webView = binding.webView
            val webSettings = webView.settings
            webSettings.javaScriptEnabled = true
            webViewClient = WebViewClient()

            webViewClient = object : WebViewClient() {
                @Deprecated("Deprecated in Java")
                override fun shouldOverrideUrlLoading(
                    view: WebView,
                    url: String
                ): Boolean {
                    view.loadUrl(news!!.url)
                    return true
                }

                override fun onPageFinished(view: WebView, url: String?) {
                    super.onPageFinished(view, url)
                    binding.progressBar1.visibility = View.GONE
                }
            }
            webView.loadUrl(news!!.url)
            binding.progressBar1.visibility = View.GONE

            webView.canGoBack()
            webView.setOnKeyListener(View.OnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                    webView.goBack()
                    return@OnKeyListener true
                }
                return@OnKeyListener false
            })
        }

    }
}





