package com.example.spacenewsdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.viewbinding.BuildConfig
import com.example.spacenewsdemo.adapter.NewsAdapter
import com.example.spacenewsdemo.databinding.ActivityMainBinding
import com.example.spacenewsdemo.model.News
import com.example.spacenewsdemo.viewmodel.MainViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), NewsAdapter.INewsAdapter {
  private lateinit var activityMainBinding: ActivityMainBinding
  private var newsAdapter: NewsAdapter? = null
  private val viewModel by lazy {
    ViewModelProviders.of(this).get(MainViewModel::class.java)
  };

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(activityMainBinding.root)
    viewModel.newsList.subscribeOn(Schedulers.newThread())
      .observeOn(AndroidSchedulers.mainThread()).subscribe({
        newsAdapter = NewsAdapter(this@MainActivity, it)
        activityMainBinding.recyclerView1.adapter = newsAdapter
      }, {})
  }

  override fun redirectToNewsActivity(id: String) {
    var intent = Intent(applicationContext, NewsActivity::class.java)
    intent.putExtra("ID", id)
    startActivity(intent)
  }

}