package com.example.spacenewsdemo.viewmodel

import androidx.lifecycle.ViewModel
import com.example.spacenewsdemo.model.News
import com.example.spacenewsdemo.repo.Repository
import io.reactivex.rxjava3.core.Single

class NewsViewModel(var id: String) : ViewModel() {
    private var _news: Single<News> = Repository().getNews(id);
    val news get() = _news
}