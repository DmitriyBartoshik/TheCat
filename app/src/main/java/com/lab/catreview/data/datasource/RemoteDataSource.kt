package com.lab.catreview.data.datasource

import android.util.Log
import com.lab.catreview.data.api.RemoteApi
import com.lab.catreview.data.entity.network.FavouriteItemResponse
import com.lab.catreview.data.entity.network.FavouriteRequest
import com.lab.catreview.data.entity.network.FavouriteResponse
import com.lab.catreview.data.entity.network.ImageResponse
import io.reactivex.Observable
import javax.inject.Inject


class RemoteDataSource @Inject constructor(private val remoteApi: RemoteApi) {
    fun loadRemoteCats(limit:Int?,page:Int?): Observable<List<ImageResponse>> {
        return remoteApi.searchImages(limit,page,"DESC")
    }

    fun loadFavoriteCats(subId: String?, limit: Int?, page: Int?): Observable<List<FavouriteItemResponse>> {
        return remoteApi.getFavourites(subId,limit,page)
    }

    fun addFavoriteCat(imageId: String?, subId: String?): Observable<FavouriteResponse> {
        Log.d("datarepo", "imageId = $imageId")
        Log.d("datarepo", "subId = $subId")

        return remoteApi.addFavourite(FavouriteRequest(imageId,subId))
    }
}