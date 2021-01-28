package com.example.spacenewsdemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.spacenewsdemo.databinding.ActivityNewsBinding
import com.example.spacenewsdemo.network.Network
import com.example.spacenewsdemo.util.News
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsActivity : AppCompatActivity() {
    // Better use meaningful name for variable
    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var id = intent.getStringExtra("ID")
        if (id != null) {
            // Changing schedulers to background
            getNews(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    Glide.with(applicationContext).load(it.imageUrl).into(binding.imageView1)
                    binding.textViewTitle.text = it.title
                    binding.textViewDetail.text = it.summary
                }, {})
        }
    }

    fun getNews(id: String): Single<News> {
        return Network.retrofit.getNewsDetail(Network.API_KEY, id)
    }
}