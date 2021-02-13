package com.example.photosandvideosapp.models

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Pagination class to add more items to the list when reach the last item.
 */
abstract class PaginationScrollListener
/**
 * Supporting only LinearLayoutManager for now.
 *
 * @param layoutManager
 */
    (var layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

    private val visibleThreshold = 5
    private var lastVisibleItem = 0
    private  var totalItemCount:Int = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        totalItemCount = layoutManager.itemCount
        lastVisibleItem = layoutManager
            .findLastVisibleItemPosition()

        if (!isLoading()
            && totalItemCount <= lastVisibleItem + visibleThreshold
        ) {
            // End has been reached
            // Do something
            loadMoreItems()
        }


    }
    abstract fun loadMoreItems()
}