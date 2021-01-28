package com.example.spacenewsdemo.network

import com.example.spacenewsdemo.util.News
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsApi {
    @GET("articles")
    fun getNews(
        @Header("apiKey") apiKey: String,
        @Query("_limit") itemLimit: Int = 50
    ): Single<List<News>>

    @GET("articles/{id}")
    fun getNewsDetail(@Header("apiKey") apiKey: String, @Path("id") id: String): Single<News>
}