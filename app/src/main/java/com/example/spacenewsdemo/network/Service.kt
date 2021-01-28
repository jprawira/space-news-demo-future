package com.example.spacenewsdemo.network

import com.example.spacenewsdemo.BuildConfig
import com.example.spacenewsdemo.util.News
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//Moving API retrieval to separated class

object Network {
    const val API_KEY = BuildConfig.API_KEY
    val retrofit = Retrofit.Builder()
        .baseUrl("https://test.spaceflightnewsapi.net/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build().create(NewsApi::class.java)

    fun getAllNews(): Single<List<News>> {
        return retrofit.getNews(API_KEY)
    }
}