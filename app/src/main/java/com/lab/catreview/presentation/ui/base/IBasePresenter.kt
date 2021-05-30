package com.lab.catreview.presentation.ui.base

interface IBasePresenter<in V : BaseView> {

    fun attachView(view: V)

    fun detachView()
}