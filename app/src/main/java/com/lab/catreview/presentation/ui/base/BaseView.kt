package com.lab.catreview.presentation.ui.base


interface BaseView {
    fun showProgress()
    fun hideProgress()
    fun showError(e: Throwable)
}
