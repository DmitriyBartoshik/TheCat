package com.lab.catreview.presentation.ui.fragment.favorite

import com.lab.catreview.data.constant.Constants
import com.lab.catreview.domain.entity.Image
import com.lab.catreview.presentation.ui.base.BasePresenter
import com.lab.catreview.presentation.ui.fragment.favorite.FavoriteContract
import com.lab.mycats.domain.usecase.GetFavoriteImagesUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject


class FavoritePresenter @Inject constructor() :
    BasePresenter<FavoriteContract.View>(), FavoriteContract.Presenter {

    @Inject
    lateinit var getFavoriteImagesUseCase: GetFavoriteImagesUseCase

    private val compositeDisposable = CompositeDisposable()

    override fun detachView() {
        super.detachView()

    }

    override fun loadFavoriteImage(page: Int) {
        val params = GetFavoriteImagesUseCase.GetFavoriteImagesUseCaseParams(
            Constants.USER_ID,
            Constants.PAGE_SIZE,
            page
        )
       val disposable:Disposable = getFavoriteImagesUseCase.execute(params)
           .subscribe({ mView?.addImages(it) }, { mView?.showError(it) })

        compositeDisposable.add(disposable)
    }

    private inner class GetFavoriteImageObserver : DisposableSingleObserver<List<Image>>() {
        override fun onSuccess(cats: List<Image>) {
            mView?.addImages(cats)
        }

        override fun onError(e: Throwable) {
            mView?.showError(e)
        }
    }
}
