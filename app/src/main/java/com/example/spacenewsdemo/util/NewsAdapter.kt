package com.example.spacenewsdemo.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spacenewsdemo.databinding.ItemNewsListBinding

class NewsAdapter(private val i: Interface, private var data: List<News>) :
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
                textViewTitle.text = item.title
                textViewDetail.text = item.summary
                root.setOnClickListener {
                    if (item.id != null) {
                        i.openNewsDetail(item.id)
                    }
                }
            }
        }
    }

    interface Interface {
        // Better use meaningful name for method
        fun openNewsDetail(id: String)
    }

}