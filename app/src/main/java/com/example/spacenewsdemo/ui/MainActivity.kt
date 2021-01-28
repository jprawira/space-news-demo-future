package com.example.spacenewsdemo.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.spacenewsdemo.databinding.ActivityMainBinding
import com.example.spacenewsdemo.network.Network.getAllNews
import com.example.spacenewsdemo.util.NewsAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity(), NewsAdapter.Interface {
    private lateinit var binding: ActivityMainBinding
    private var adapter: NewsAdapter? = null

    // Move API transaction to network package
//  companion object {
//    const val API_KEY = "3tscd3r2fVYrfCwwftMZlaCdUScZqvSl"
//    val retrofit = Retrofit.Builder()
//      .baseUrl("https://test.spaceflightnewsapi.net/api/v2/")
//      .addConverterFactory(GsonConverterFactory.create())
//      .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//      .build().create(NewsApi::class.java)
//  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Better use meaningful variable name for binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Changing schedulers to background
        getAllNews().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe({
                // Better use meaningful name for adapter
                adapter =
                    NewsAdapter(this@MainActivity, it)
                binding.recyclerView1.adapter = adapter
            }, {})
    }

    // Better use meaningful name for method
    override fun openNewsDetail(id: String) {
        //Better use meaningful name for variable
        var intent = Intent(applicationContext, NewsActivity::class.java)
        intent.putExtra("ID", id)
        startActivity(intent)
    }
}