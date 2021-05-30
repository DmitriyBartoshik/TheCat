package com.lab.catreview.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lab.catreview.R
import com.lab.catreview.domain.entity.Image

class CatImageAdapter(
    private var images: MutableList<Image>,
    private val onFavClicked: (adapterPos: Int, image: Image, isFavoured: Boolean) -> Unit
) : RecyclerView.Adapter<CatImageAdapter.ViewHolder>() {


    fun appendImages(images: List<Image>) {
        this.images.addAll(images)
        notifyItemRangeInserted(
            this.images.size,
            images.size - 1
        )
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_cat_image,
            parent,
            false
        )
        return ViewHolder(itemView, onFavClicked)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position], onFavClicked)
    }

    class ViewHolder(
        itemView: View,
        private val onFavClicked: (adapterPos: Int, image: Image, isFavoured: Boolean) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val catImageView: ImageView
            get() = itemView.findViewById(R.id.iv_cat_image)

        private val favIcon: ImageView
            get() = itemView.findViewById<ImageView>(R.id.iv_fav)


        fun bind(
            image: Image,
            onFavClicked: (adapterPos: Int, image: Image, isFavoured: Boolean) -> Unit,
        ) {
            Glide.with(catImageView)
                .load(image.url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(catImageView)

            favIcon.apply {
                setImageResource(
                    image.favId?.let { R.drawable.ic_favorite_purple }
                        ?: R.drawable.ic_favorite_black
                )
                setOnClickListener {
                    val setFavoured = image.favId == null
                    this@ViewHolder.onFavClicked(bindingAdapterPosition, image, setFavoured)
                }
            }
        }

    }
}
