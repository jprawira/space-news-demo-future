package com.example.spacenewsdemo

import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiService {
    companion object {
        // Move API key
        val retrofit = Retrofit.Builder()
            .baseUrl("https://test.spaceflightnewsapi.net/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build().create(NewsApi::class.java)

        fun getAllNews(): Single<List<News>> {
            // use build config
            return retrofit.getNews(BuildConfig.API_KEY)
        }

        fun getNews(id: String): Single<News> {
            // use build config
            return retrofit.getNewsDetail(BuildConfig.API_KEY, id)
        }
    }
}