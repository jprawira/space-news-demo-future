package com.example.spacenewsdemo.repo

import com.example.spacenewsdemo.model.News
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Repository {
    companion object {
        //const val API_KEY = "3tscd3r2fVYrfCwwftMZlaCdUScZqvSl"
        const val API_KEY = com.example.spacenewsdemo.BuildConfig.api_secret;
        val retrofit = Retrofit.Builder()
            .baseUrl("https://test.spaceflightnewsapi.net/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build().create(NewsApi::class.java)
    }

    fun getAllNews(): Single<List<News>> {
        return retrofit.getNews(API_KEY)
    }
}