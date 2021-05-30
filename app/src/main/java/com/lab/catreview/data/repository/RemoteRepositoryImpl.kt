package com.lab.catreview.data.repository

import com.lab.catreview.data.datasource.RemoteDataSource
import com.lab.catreview.data.entity.toFavImageList
import com.lab.catreview.data.entity.toImageList
import com.lab.catreview.domain.entity.Image
import com.lab.mycats.domain.repository.RemoteRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    RemoteRepository {
    override fun loadCat(limit: Int?, page: Int?): Observable<List<Image>> {
        return remoteDataSource.loadRemoteCats(limit, page).map { it.toImageList() }
    }

    override fun loadFavoriteCat(subId: String?, limit: Int?, page: Int?): Observable<List<Image>> {
        return remoteDataSource.loadFavoriteCats(subId, limit, page).map { it.toFavImageList() }
    }

    override fun addFavorite(imageId: String?, subId: String?): Observable<String> {
        return remoteDataSource.addFavoriteCat(imageId, subId).map { it.id }
    }
}


