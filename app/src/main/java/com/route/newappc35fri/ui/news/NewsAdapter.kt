package com.route.newappc35fri.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.route.newappc35fri.R
import com.route.newappc35fri.databinding.ItemNewsBinding
import com.route.newappc35fri.model.ArticlesItem

class NewsAdapter(var items: List<ArticlesItem?>? = null) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemNewsBinding: ItemNewsBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_news, parent, false
            );
        return ViewHolder(itemNewsBinding);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items!![position];
        holder.bindData(item)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0;
    }

    fun changeData(data: List<ArticlesItem?>?) {
        items = data;
        notifyDataSetChanged()
    }

    class ViewHolder(val dataBinding: ItemNewsBinding) : RecyclerView.ViewHolder(dataBinding.root) {
        fun bindData(item: ArticlesItem?) {
            dataBinding.item = item;
            dataBinding.executePendingBindings()
        }
    }
}