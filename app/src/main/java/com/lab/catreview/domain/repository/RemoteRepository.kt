package com.lab.mycats.domain.repository

import com.lab.catreview.domain.entity.Image
import io.reactivex.Observable

interface RemoteRepository {
    fun loadCat(limit: Int?, page: Int?): Observable<List<Image>>
    fun loadFavoriteCat(subId: String?, limit: Int?, page: Int?): Observable<List<Image>>
    fun addFavorite(imageId: String?, subId: String?): Observable<String>
}