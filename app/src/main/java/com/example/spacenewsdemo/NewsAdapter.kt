package com.example.spacenewsdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spacenewsdemo.databinding.ItemNewsListBinding

class NewsAdapter(private val interface_: Interface, private var data: List<News>) :
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
                        interface_.click(item.id)
                    }
                }
            }
        }
    }

    interface Interface {
        fun click(id: String)
    }

}