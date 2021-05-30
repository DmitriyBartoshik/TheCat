package com.lab.catreview.data.entity

import com.lab.catreview.data.entity.network.FavouriteItemResponse
import com.lab.catreview.data.entity.network.ImageResponse
import com.lab.catreview.domain.entity.Image

fun List<ImageResponse>.toImageList(): List<Image> =
    asSequence()
        .filterNotNull()
        .filter { it.id != null && it.url != null }
        .map { Image(id = it.id!!, url = it.url!!) }
        .toList()

fun List<FavouriteItemResponse>.toFavImageList(): List<Image> =
    asSequence()
        .filterNotNull()
        .filter { it.id != null && it.image?.id != null && it.image.url != null }
        .map {
            Image(
                favId = it.id,
                id = it.image!!.id!!,
                url = it.image.url!!
            )
        }
        .toList()