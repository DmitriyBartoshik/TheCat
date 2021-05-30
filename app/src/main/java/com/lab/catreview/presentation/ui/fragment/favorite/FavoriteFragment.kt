package com.lab.catreview.presentation.ui.fragment.favorite

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lab.catreview.R
import com.lab.catreview.data.constant.Constants
import com.lab.catreview.domain.entity.Image
import com.lab.catreview.presentation.ui.adapter.FavoriteImageAdapter
import com.lab.catreview.presentation.ui.base.BaseFragment
import javax.inject.Inject


class FavoriteFragment : BaseFragment(), FavoriteContract.View {
    @Inject
    lateinit var presenter: FavoritePresenter

    private var page: Int = 0

    private val recyclerView: RecyclerView?
        get() = view?.findViewById(R.id.rv_favorite_cat_images)

    private lateinit var layoutManager: GridLayoutManager

    private val catImagesAdapter = FavoriteImageAdapter(mutableListOf())

    companion object {
        private const val LAYOUT_ID = R.layout.fragment_favorite
    }

    override fun getLayoutId(): Int {
        return LAYOUT_ID
    }

    override fun initView() {
        presenter.attachView(this)
        initRecyclerView()
        loadImage(page)
//        attachOnScrollListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun addImages(cats: List<Image>) {
        catImagesAdapter.appendImages(cats)
    }

    override fun showProgress() {
        TODO("Not yet implemented")
    }

    override fun hideProgress() {
        TODO("Not yet implemented")
    }

    override fun showError(e: Throwable) {
        TODO("Not yet implemented")
    }

    private fun initRecyclerView() {
        recyclerView?.adapter = catImagesAdapter
        layoutManager =
            GridLayoutManager(context, Constants.SPAN_COUNT, GridLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = layoutManager
    }

    private fun loadImage(page: Int) {
//        isLoading = true;
        presenter.loadFavoriteImage(page)
    }
}