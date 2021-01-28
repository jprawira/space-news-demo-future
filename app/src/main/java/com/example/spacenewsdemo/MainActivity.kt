package com.example.spacenewsdemo


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spacenewsdemo.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity(), NewsAdapter.Interface {
  private lateinit var binding: ActivityMainBinding
  private var adapter: NewsAdapter? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    NetworkApiClient.getNews().subscribeOn(Schedulers.newThread())
      .observeOn(AndroidSchedulers.mainThread()).subscribe({
        adapter = NewsAdapter(this@MainActivity, it)
        binding.recyclerView1.adapter = adapter
      }, {})
  }

  override fun click(id: String) {
    val intent = Intent(applicationContext, NewsActivity::class.java)
    intent.putExtra("ID", id)
    startActivity(intent)
  }

}