package com.example.spacenewsdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spacenewsdemo.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), NewsAdapter.INewsAdapter{

  // change variable name
  private lateinit var binding: ActivityMainBinding
  private var newsAdapter: NewsAdapter? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    NewsApiService.getAllNews().subscribeOn(Schedulers.newThread())
      .observeOn(AndroidSchedulers.mainThread()).subscribe({
        newsAdapter = NewsAdapter(this@MainActivity, it)
        binding.recyclerView1.adapter = newsAdapter
      }, {})
  }

  override fun clickListener(id: String) {
    // change intent name variable and context
    var intent = Intent(this, NewsActivity::class.java)
    intent.putExtra("ID", id)
    startActivity(intent)
  }

}