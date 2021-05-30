package com.lab.catreview.presentation.ui.fragment.favorite

import com.lab.catreview.domain.entity.Image
import com.lab.catreview.presentation.ui.base.BaseView
import com.lab.catreview.presentation.ui.base.IBasePresenter


interface FavoriteContract {
    interface View : BaseView {
        fun addImages(cats: List<Image>)
    }

    interface Presenter : IBasePresenter<View> {
        fun loadFavoriteImage(page: Int)
    }
}