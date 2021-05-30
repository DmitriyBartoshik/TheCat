package com.lab.catreview.presentation.ui.fragment.common

import com.lab.catreview.data.constant.Constants
import com.lab.catreview.domain.entity.Image
import com.lab.catreview.domain.usecase.AddFavoriteUseCase
import com.lab.catreview.domain.usecase.GetCatImagesUseCase
import com.lab.catreview.presentation.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class CommonPresenter @Inject constructor() :
    BasePresenter<CommonContract.View>(), CommonContract.Presenter {

    @Inject
    lateinit var getCatImagesUseCase: GetCatImagesUseCase

    @Inject
    lateinit var addFavoriteUseCase: AddFavoriteUseCase


    private val compositeDisposable = CompositeDisposable()


    override fun detachView() {
        super.detachView()
        compositeDisposable.clear()
    }

    override fun loadImage(page: Int) {
        mView?.showProgress()
        val params = GetCatImagesUseCase.GetCatImagesUseCaseParams(Constants.PAGE_SIZE, page)
        val disposable: Disposable = getCatImagesUseCase.execute(params)
            .subscribe({ mView?.addImage(it) }, { mView?.showError(it) })

        compositeDisposable.add(disposable)
    }

//    override fun addFavorite(imageId:String,userId:String) {
//        val params = AddFavoriteUseCase.AddFavoriteUseCaseParams(imageId, userId)
//        addFavoriteUseCase.execute(AddFavoriteObserver(), params )
//    }

    override fun addFavorite(position: Int, imageId: String, userId: String) {
        val params = AddFavoriteUseCase.AddFavoriteUseCaseParams(imageId, userId)
        val disposable: Disposable =
            addFavoriteUseCase.execute(params).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ mView?.likeDislike(position) }, { e -> mView?.showError(e) })

        compositeDisposable.add(disposable)
    }

    override fun deleteFavorite() {
        TODO("Not yet implemented")
    }

    private fun onImageFavouredChanged(position: Int, image: Image, isFavoured: Boolean) {
        if (isFavoured) {
            favourImage(position, image)
        } else {
            unfavourImage(position, image)
        }
    }

    private fun favourImage(position: Int, image: Image) {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val id = CatRepository.addFavourite(
//                    imageId = image.id,
//                    userId = Constants.USER_ID
//                )
//                id?.let { image.favId = it }
//                viewState.postValue(ExploreState.Load(position))
//            } catch (e: Exception) {
//                viewState.postValue(ExploreState.Error())
//            }
//        }
    }

    private fun unfavourImage(position: Int, image: Image) {
        val favId = image.favId ?: return
    }
}
