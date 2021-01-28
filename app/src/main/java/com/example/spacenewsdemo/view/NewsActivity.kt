package com.example.spacenewsdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.spacenewsdemo.databinding.ActivityNewsBinding
import com.example.spacenewsdemo.model.News
import com.example.spacenewsdemo.repo.Repository
import com.example.spacenewsdemo.viewmodel.MainViewModel
import com.example.spacenewsdemo.viewmodel.NewsViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsActivity : AppCompatActivity() {
    private lateinit var activityNewsBinding: ActivityNewsBinding
    private lateinit var viewModel: NewsViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNewsBinding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(activityNewsBinding.root)
        var id = intent.getStringExtra("ID")
        if (id != null) {
            viewModel = NewsViewModel(id);
            viewModel.news.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe({
                    Glide.with(applicationContext).load(it.imageUrl).into(activityNewsBinding.imageView1)
                    activityNewsBinding.textView1.text = it.title
                    activityNewsBinding.textView2.text = it.summary
                }, {})
        }
    }

}