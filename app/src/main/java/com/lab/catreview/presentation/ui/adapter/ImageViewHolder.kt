package com.lab.mycats.presentation.ui.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lab.catreview.R

import com.lab.catreview.domain.entity.Image


class ImageViewHolder(
    itemView: View,
    private val onFavClicked: (adapterPos: Int, image: Image, isFavoured: Boolean) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val catImageView: ImageView
        get() = itemView.findViewById(R.id.iv_cat_image)

    private val favIcon: ImageView
        get() = itemView.findViewById<ImageView>(R.id.iv_fav)

    fun reset() {
        catImageView.setImageDrawable(null)
        favIcon.apply {
            setImageResource(R.drawable.ic_favorite_black)
            setOnClickListener(null)
        }
    }

    fun bindTo(image: Image) {
        Glide.with(catImageView)
            .load(image.url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(catImageView)

        favIcon.apply {
            setImageResource(
                image.favId?.let { R.drawable.ic_favorite_purple } ?: R.drawable.ic_favorite_black
            )
            setOnClickListener {
                val setFavoured = image.favId == null
                onFavClicked(bindingAdapterPosition, image, setFavoured)
            }
        }
    }

}