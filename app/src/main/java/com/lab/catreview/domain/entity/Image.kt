package com.lab.catreview.domain.entity


data class Image(
    val id: String,
    val url: String,
    var favId: String? = null
)