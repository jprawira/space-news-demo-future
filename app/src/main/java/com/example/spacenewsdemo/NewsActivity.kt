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
    // change variable name
    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var id = intent.getStringExtra("ID")
        if (id != null) {
            NewsApiService.getNews(id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    Glide.with(applicationContext).load(it.imageUrl).into(binding.imageViewNews)
                    binding.textViewTitle.text = it.title
                    binding.textViewSummary.text = it.summary
                }, {})
        }
    }

}