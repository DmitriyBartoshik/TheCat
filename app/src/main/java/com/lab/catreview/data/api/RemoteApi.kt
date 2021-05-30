package com.lab.catreview.data.api

import com.lab.catreview.data.constant.Constants
import com.lab.catreview.data.entity.network.FavouriteItemResponse
import com.lab.catreview.data.entity.network.FavouriteRequest
import com.lab.catreview.data.entity.network.FavouriteResponse
import com.lab.catreview.data.entity.network.ImageResponse
import io.reactivex.Observable
import retrofit2.http.*

interface RemoteApi {

    @GET("/v1/images/search")
    fun searchImages(
        @Query("limit") limit: Int? = Constants.PAGE_SIZE,
        @Query("page") page: Int? = 0,
        @Query("order") order: String = "random"
    ): Observable<List<ImageResponse>>

    @POST("/v1/favourites")
    fun addFavourite(
        @Body favouriteRequest: FavouriteRequest?
    ): Observable<FavouriteResponse>

    @GET("/v1/favourites")
    fun getFavourites(
        @Query("sub_id") subId: String?,
        @Query("limit") limit: Int? = Constants.PAGE_SIZE,
        @Query("page") page: Int? = 0
    ): Observable<List<FavouriteItemResponse>>

    @DELETE("/v1/favourites/{favourite_id}")
    fun removeFavourite(
        @Path("favourite_id") favouriteId: String
    )
}
