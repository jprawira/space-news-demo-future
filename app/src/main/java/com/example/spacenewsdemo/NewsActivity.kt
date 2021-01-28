package com.example.spacenewsdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.spacenewsdemo.databinding.ActivityNewsBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getStringExtra("ID")
        if (id != null) {
            NetworkApiClient.getNewsDetail(id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    Glide.with(applicationContext).load(it.imageUrl).into(binding.imageView1)
                    binding.textView1.text = it.title
                    binding.textView2.text = it.summary
                }, {})
        }
    }
}