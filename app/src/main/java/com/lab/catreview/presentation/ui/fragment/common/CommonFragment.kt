package com.lab.catreview.presentation.ui.fragment.common


import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lab.catreview.R
import com.lab.catreview.data.constant.Constants
import com.lab.catreview.domain.entity.Image
import com.lab.catreview.presentation.ui.adapter.CatImageAdapter
import com.lab.catreview.presentation.ui.base.BaseFragment
import javax.inject.Inject

class CommonFragment : BaseFragment(), CommonContract.View {
    @Inject
    lateinit var presenter: CommonPresenter

    private var page: Int = 0

    private val catImagesAdapter = CatImageAdapter(mutableListOf(), this::onImageFavouriteClicked)

    private lateinit var layoutManager: GridLayoutManager

    private var isLoading = false

    private val recyclerView: RecyclerView?
        get() = view?.findViewById(R.id.rv_random_cat_images)

    private val progressBar: ProgressBar?
        get() = view?.findViewById(R.id.common_progress_bar)

    companion object {
        private const val LAYOUT_ID = R.layout.fragment_common
    }


    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    //region Override BaseFragment
    override fun getLayoutId(): Int {
        return LAYOUT_ID
    }

    override fun initView() {
        presenter.attachView(this)
        initRecyclerView()
        loadImage(page)
        attachOnScrollListener()
    }

    //endregion

    //region CommonContract.View
    override fun addImage(cats: List<Image>) {
        isLoading = false
        catImagesAdapter.appendImages(cats)
    }

    override fun likeDislike(position: Int) {
    }

    override fun showProgress() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar?.visibility = View.GONE
    }

    override fun showError(e: Throwable) {
    }
    //endregion

    //region private
    private fun initRecyclerView() {
        recyclerView?.adapter = catImagesAdapter
        layoutManager =
            GridLayoutManager(context, Constants.SPAN_COUNT, GridLayoutManager.VERTICAL, false)

        recyclerView?.layoutManager = layoutManager
    }

    private fun onImageFavouriteClicked(position: Int, image: Image, isFavoured: Boolean) {
        if (isFavoured) {
            presenter.addFavorite(position, image.id, Constants.USER_ID)
        } else {
            presenter.deleteFavorite(position, image)
        }
    }

    private fun loadImage(page: Int) {
        isLoading = true;
        presenter.loadImage(page)
    }

    private fun attachOnScrollListener() {
        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                if ((totalItemCount / Constants.SPAN_COUNT < lastVisibleItem) && !isLoading) {
                    page++
                    loadImage(page)
                }
            }
        })
    }
//endregion
}