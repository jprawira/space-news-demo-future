package com.example.spacenewsdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spacenewsdemo.databinding.ItemNewsListBinding
import com.example.spacenewsdemo.model.News

class NewsAdapter(private val iNewsAdapter: INewsAdapter, private var data: List<News>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val binding: ItemNewsListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: News) {
            with(binding) {
                // TODO: INSERT IMAGE
                textView1.text = item.title
                textView2.text = item.summary
                root.setOnClickListener {
                    if (item.id != null) {
                        iNewsAdapter.redirectToNewsActivity(item.id)
                    }
                }
            }
        }
    }

    interface INewsAdapter {
        fun redirectToNewsActivity(id: String)
    }

}