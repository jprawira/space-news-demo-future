package com.example.spacenewsdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spacenewsdemo.databinding.ItemNewsListBinding

class NewsAdapter(private val interfaceNewsAdapter: INewsAdapter, private var data: List<News>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private lateinit var binding : ItemNewsListBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // change element passed to ViewHolder
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemNewsListBinding.inflate(inflater, parent, false)

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: News) {
            with(binding) {
                // TODO: INSERT IMAGE
                textViewTitle.text = item.title
                textViewSummary.text = item.summary
                root.setOnClickListener {
                    if (item.id != null) {
                        interfaceNewsAdapter.clickListener(item.id)
                    }
                }
            }
        }
    }

    // change interface name
    interface INewsAdapter {
        // change method name
        fun clickListener(id: String)
    }

}