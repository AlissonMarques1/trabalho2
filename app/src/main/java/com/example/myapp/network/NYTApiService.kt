package com.example.myapp.network

import BooksResponse
import com.example.myapp.models.NYTResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NYTApiService {
    @GET("topstories/v2/home.json")
    suspend fun getTopStories(
        @Query("api-key") apiKey: String
    ): NYTResponse
}
