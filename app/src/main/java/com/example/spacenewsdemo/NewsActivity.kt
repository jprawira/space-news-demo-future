package com.example.spacenewsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.spacenewsdemo.databinding.ActivityMainBinding
import com.example.spacenewsdemo.databinding.ActivityNewsBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsActivity : AppCompatActivity() {
    private lateinit var b: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(b.root)
        var id = intent.getStringExtra("ID")
        if (id != null) {
            getNews(id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    Glide.with(applicationContext).load(it.imageUrl).into(b.imageView1)
                    b.textView1.text = it.title
                    b.textView2.text = it.summary
                }, {})
        }
    }

    fun getNews(id: String): Single<News> {
        return MainActivity.retrofit.getNewsDetail(MainActivity.API_KEY, id)
    }
}