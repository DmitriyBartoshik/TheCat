package com.lab.catreview.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.lab.catreview.R
import com.lab.catreview.domain.entity.Image
import com.lab.mycats.presentation.ui.adapter.ImageViewHolder

class CatImageAdapter(
    private val onFavClicked: (adapterPos: Int, image: Image, isFavoured: Boolean) -> Unit
) : ListAdapter<Image, ImageViewHolder>(ImageDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_cat_image,
            parent,
            false
        )
        return ImageViewHolder(itemView, onFavClicked)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        getItem(position)?.let { holder.bindTo(it) }
    }
}

object ImageDiffer : DiffUtil.ItemCallback<Image>() {

    override fun areItemsTheSame(oldItem: Image, newItem: Image) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Image, newItem: Image) = oldItem == newItem

}

//fun setData(position: Int, repo: Repo) {
//    data[position] = repo
//    notifyItemChanged(position)
//}