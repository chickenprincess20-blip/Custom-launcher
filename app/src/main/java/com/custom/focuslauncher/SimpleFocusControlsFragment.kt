package com.custom.focuslauncher.ui

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.custom.focuslauncher.FocusType

class SimpleFocusControlsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val root = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setBackgroundColor(Color.parseColor("#121212"))
            setPadding(64, 128, 64, 64)
        }

        val headline = TextView(context).apply {
            text = "SELECT FOCUS MODE"
            setTextColor(Color.WHITE)
            textSize = 22f
            paint.isFakeBoldText = true
            setPadding(0, 0, 0, 64)
        }
        root.addView(headline)

        root.addView(createMinimalButton("STANDARD MODE", "#2C2C2E") { updateLauncherFocus(FocusType.DEFAULT) })
        root.addView(createMinimalButton("💼 WORK FOCUS", "#0A84FF") { updateLauncherFocus(FocusType.WORK) })
        root.addView(createMinimalButton("🏡 PERSONAL FOCUS", "#30D158") { updateLauncherFocus(FocusType.PERSONAL) })

        return root
    }

    private fun createMinimalButton(title: String, hexColor: String, onClick: () -> Unit): Button {
        return Button(context).apply {
            text = title
            setTextColor(Color.WHITE)
            textSize = 16f
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 160
            ).apply { setMargins(0, 24, 0, 24) }
            
            background = GradientDrawable().apply {
                setColor(Color.parseColor(hexColor))
                cornerRadius = 32f
            }
            setOnClickListener { onClick() }
        }
    }

    private fun updateLauncherFocus(mode: FocusType) {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}
