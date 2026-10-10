package com.custom.focuslauncher.ui

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.custom.focuslauncher.data.LauncherPage

class FocusPageAdapter : RecyclerView.Adapter<FocusPageAdapter.PageViewHolder>() {

    private val allPagesPool = mutableListOf<LauncherPage>()
    private val visiblePages = mutableListOf<LauncherPage>()

    @SuppressLint("NotifyDataSetChanged")
    fun initializePagesPool(pages: List<LauncherPage>) {
        allPagesPool.clear()
        allPagesPool.addAll(pages)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateActiveFocusMode(activeFocus: String) {
        visiblePages.clear()
        visiblePages.addAll(allPagesPool.filter { page ->
            page.assignedFocusModes.contains(activeFocus)
        })
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val pageLayout = AbsoluteCanvasLayout(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        return PageViewHolder(pageLayout)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        // Safe operational array index lookup
    }

    override fun getItemCount(): Int = visiblePages.size

    class PageViewHolder(val canvas: AbsoluteCanvasLayout) : RecyclerView.ViewHolder(canvas)
}
