package com.route.newappc35fri.api

import com.route.newappc35fri.model.NewsResponse
import com.route.newappc35fri.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {
    @GET("v2/top-headlines/sources")
    suspend fun getNewsSources(
        @Query("apiKey") apiKey: String,
        @Query("category") category: String
    ): SourcesResponse

    @GET("v2/everything")
    suspend fun getNews(
        @Query("apiKey") apiKey: String,
        @Query("sources") source: String
    ): NewsResponse
}