package com.custom.focuslauncher.ui

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.custom.focuslauncher.data.LauncherComponent

class AbsoluteCanvasLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var isEditModeActive: Boolean = true

    fun renderCanvasComponents(components: List<LauncherComponent>) {
        removeAllViews()
        for (component in components) {
            val elementView = when (component.componentType) {
                "APPLICATION" -> createApplicationNode(component)
                else -> createGenericCustomNode(component)
            }

            val params = LayoutParams(component.layoutWidth, component.layoutHeight).apply {
                leftMargin = component.xCoordinate.toInt()
                topMargin = component.yCoordinate.toInt()
            }

            elementView.rotation = component.angleRotation
            elementView.alpha = component.alphaTransparency
            
            if (isEditModeActive) {
                enableCanvasDraggableLogic(elementView, component)
            }
            addView(elementView, params)
        }
    }

    private fun createApplicationNode(component: LauncherComponent): View {
        val imageView = ImageView(context)
        try {
            val appIcon = context.packageManager.getApplicationIcon(component.packageOrActionIdentifier)
            imageView.setImageDrawable(appIcon)
        } catch (e: Exception) {
            imageView.setImageResource(android.R.drawable.sym_def_app_icon)
        }
        
        imageView.setOnClickListener {
            if (!isEditModeActive) {
                val launchIntent = context.packageManager.getLaunchIntentForPackage(component.packageOrActionIdentifier)
                launchIntent?.let { context.startActivity(it) }
            }
        }
        return imageView
    }

    private fun createGenericCustomNode(component: LauncherComponent): View {
        return View(context).apply {
            setBackgroundColor(android.graphics.Color.parseColor(component.customHexColor ?: "#FF0000"))
        }
    }

    private fun enableCanvasDraggableLogic(view: View, data: LauncherComponent) {
        view.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_MOVE -> {
                    v.x = event.rawX - (v.width / 2)
                    v.y = event.rawY - (v.height / 2)
                }
            }
            true
        }
    }
}