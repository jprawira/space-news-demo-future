package com.example.spacenewsdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.spacenewsdemo.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), NewsAdapter.Interface {
  private lateinit var b: ActivityMainBinding
  private var a: NewsAdapter? = null

  companion object {
    const val API_KEY = "3tscd3r2fVYrfCwwftMZlaCdUScZqvSl"
    val retrofit = Retrofit.Builder()
      .baseUrl("https://test.spaceflightnewsapi.net/api/v2/")
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
      .build().create(NewsApi::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    b = ActivityMainBinding.inflate(layoutInflater)
    setContentView(b.root)
    getAllNews().subscribeOn(Schedulers.newThread())
      .observeOn(AndroidSchedulers.mainThread()).subscribe({
        a = NewsAdapter(this@MainActivity, it)
        b.recyclerView1.adapter = a
      }, {})
  }

  override fun c(id: String) {
    var i = Intent(applicationContext, NewsActivity::class.java)
    i.putExtra("ID", id)
    startActivity(i)
  }

  fun getAllNews(): Single<List<News>> {
    return retrofit.getNews(API_KEY)
  }
}