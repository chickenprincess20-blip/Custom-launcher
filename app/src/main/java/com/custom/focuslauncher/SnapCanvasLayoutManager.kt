package com.custom.focuslauncher.ui

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

class SnapCanvasLayoutManager(context: Context) : LinearLayoutManager(context, HORIZONTAL, false) {

    private val DECREASED_VELOCITY_RATIO = 0.15f 

    override fun smoothScrollToPosition(recyclerView: RecyclerView, state: RecyclerView.State?, position: Int) {
        val smoothScroller = object : LinearSmoothScroller(recyclerView.context) {
            override fun calculateSpeedPerPixel(displayMetrics: android.util.DisplayMetrics): Float {
                return (25f / displayMetrics.densityDpi) * (1f / DECREASED_VELOCITY_RATIO)
            }
        }
        smoothScroller.targetPosition = position
        startSmoothScroll(smoothScroller)
    }
}
