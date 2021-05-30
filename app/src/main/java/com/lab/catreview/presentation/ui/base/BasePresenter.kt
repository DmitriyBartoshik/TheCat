package com.lab.catreview.presentation.ui.base

open class BasePresenter<V : BaseView> : IBasePresenter<V> {

    protected var mView: V? = null

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }
}