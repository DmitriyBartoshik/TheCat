package com.lab.catreview.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lab.catreview.R
import com.lab.catreview.domain.entity.Image
import kotlinx.android.synthetic.main.item_favorite_cat_image.view.*

class FavoriteImageAdapter(private var images: MutableList <Image>) : RecyclerView.Adapter<FavoriteImageAdapter.ViewHolder>() {

    fun appendImages(images: List<Image>) {
        this.images.addAll(images)
        notifyItemRangeInserted(
            this.images.size,
            images.size - 1)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_cat_image, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var image: Image = images[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int = images.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val catImageView: ImageView
            get() = itemView.findViewById(R.id.iv_favorite_cat)

        fun bind(image: Image) = with(itemView) {
            Glide.with(iv_favorite_cat)
                .load(image.url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(catImageView)
        }
    }
}