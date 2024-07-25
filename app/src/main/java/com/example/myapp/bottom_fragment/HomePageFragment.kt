package com.example.myapp.bottom_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.R
import com.example.myapp.adapters.NewsAdapter
import com.example.myapp.models.NYTArticle
import com.example.myapp.network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePageFragment : Fragment() {

    private lateinit var newsAdapter: NewsAdapter
    private val newsList = mutableListOf<NYTArticle>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        newsAdapter = NewsAdapter(newsList)
        recyclerView.adapter = newsAdapter

        fetchNews()
    }

    private fun fetchNews() {
        val apiKey = "7FVvG2oWP8kep9HHBixt7vKuARZx18Qz"
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.api.getTopStories(apiKey)
                withContext(Dispatchers.Main) {
                    newsList.clear()
                    newsList.addAll(response.results)
                    newsAdapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
