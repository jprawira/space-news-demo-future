package com.example.spacenewsdemo

import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkApiClient {
    const val API_KEY = BuildConfig.ApiKey
    private const val BASE_URL = "https://test.spaceflightnewsapi.net/api/v2/"
    private fun getClient(): NewsApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        return retrofit.create(NewsApi::class.java)
    }

    fun getNews(): Single<List<News>> {
        return getClient().getNews(API_KEY)
    }
    fun getNewsDetail(id: String): Single<News> {
        return getClient().getNewsDetail(API_KEY, id)
    }
}