package com.lab.catreview.presentation.ui.fragment.common

import com.lab.catreview.domain.entity.Image
import com.lab.catreview.presentation.ui.base.BaseView
import com.lab.catreview.presentation.ui.base.IBasePresenter


interface CommonContract {
    interface View : BaseView {
        fun addImage(cats: List<Image>)
        fun likeDislike(position:Int)
    }

    interface Presenter : IBasePresenter<View> {
        fun loadImage(page: Int)
        fun addFavorite(position: Int, imageId: String, userId: String)
        fun deleteFavorite(position:Int,image:Image)
    }
}
