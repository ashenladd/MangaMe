package com.example.mangame.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mangame.databinding.ItemMangaBinding

class HomeAdapter(private val mangaList: ArrayList<MangaModel>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class HomeViewHolder(val binding: ItemMangaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemMangaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HomeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mangaList.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val (title, desc, thumbnail, views, comments, rating) = mangaList[position]
        holder.binding.apply {
            tvMangaName.text = title
            Glide.with(holder.itemView.context)
                .load(thumbnail)
                .into(ivManga)
            tvMangaDescription.text = desc
            tvViews.text = views
            tvComments.text = comments
            tvRating.text = rating
            cvManga.setOnClickListener {
                onItemClickCallback.onItemClicked(mangaList[holder.adapterPosition])
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: MangaModel)
    }
}