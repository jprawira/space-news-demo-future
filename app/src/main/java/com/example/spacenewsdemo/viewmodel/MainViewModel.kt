package com.example.spacenewsdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.spacenewsdemo.repo.NewsApi
import com.example.spacenewsdemo.model.News
import com.example.spacenewsdemo.repo.Repository
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel(){

    private var _newsList: Single<List<News>> = Repository().getAllNews();
    val newsList get() = _newsList

}