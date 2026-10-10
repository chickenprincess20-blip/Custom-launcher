package com.custom.focuslauncher.engine

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import com.custom.focuslauncher.FocusType
import java.io.File

class AssetInjectionEngine(private val context: Context) {

    fun resolveFocusIcon(packageName: String, activeFocus: FocusType): Drawable {
        val customIconFile = File(context.filesDir, "icons/\${activeFocus.name}_\${packageName}.png")
        if (customIconFile.exists()) {
            val bitmap = BitmapFactory.decodeFile(customIconFile.absolutePath)
            return BitmapDrawable(context.resources, bitmap)
        }
        return context.packageManager.getApplicationIcon(packageName)
    }

    fun buildCustomWidgetNode(widgetId: String, activeFocus: FocusType): View {
        return when (widgetId) {
            "CLOCK_TEXT" -> TextView(context).apply {
                text = "12:00"
                textSize = 48f
                setTextColor(if (activeFocus == FocusType.WORK) android.graphics.Color.BLUE else android.graphics.Color.WHITE)
            }
            else -> View(context)
        }
    }
}