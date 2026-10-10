package com.custom.focuslauncher.ui

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView

class LauncherPager @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val snapHelper = PagerSnapHelper()
    private val canvasLayoutManager = SnapCanvasLayoutManager(context)
    private var currentPageIndex = 0

    init {
        layoutManager = canvasLayoutManager
        snapHelper.attachToRecyclerView(this)

        addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == SCROLL_STATE_IDLE) {
                    val centerView = snapHelper.findSnapView(canvasLayoutManager)
                    centerView?.let {
                        currentPageIndex = canvasLayoutManager.getPosition(it)
                    }
                }
            }
        })
    }

    override fun fling(velocityX: Int, velocityY: Int): Boolean {
        val targetPosition = if (velocityX > 0) currentPageIndex + 1 else currentPageIndex - 1
        if (targetPosition in 0 until (adapter?.itemCount ?: 0)) {
            smoothScrollToPosition(targetPosition)
            return true
        }
        return false
    }
}
