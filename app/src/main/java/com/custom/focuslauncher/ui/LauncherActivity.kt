package com.custom.focuslauncher.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.custom.focuslauncher.R
import com.custom.focuslauncher.LauncherStateController
import com.custom.focuslauncher.FocusType
import com.custom.focuslauncher.data.FocusProfile

class LauncherActivity : Activity() {

    private lateinit var stateController: LauncherStateController
    private var launcherPager: LauncherPager? = null
    private var commandCenter: CommandCenterView? = null
    private var toggleButton: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        stateController = LauncherStateController(this)
        
        launcherPager = findViewById(R.id.home_screen_pager)
        commandCenter = findViewById(R.id.command_center_overlay)
        toggleButton = findViewById(R.id.btn_command_center_toggle)

        toggleButton?.setOnClickListener {
            commandCenter?.let { cc ->
                cc.visibility = if (cc.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            }
        }

        commandCenter?.onFocusSelectedListener = { selectedFocus ->
            stateController.setCurrentFocusMode(selectedFocus) { profile ->
                val name = profile.profileName
                android.util.Log.d("FocusLauncher", "Active Profile Name: $name")
            }
            commandCenter?.visibility = View.GONE
        }

        val currentMode = stateController.getCurrentFocusMode()
        stateController.setCurrentFocusMode(currentMode) { _ ->
            // Base initialization block setup clear
        }
    }

    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        // Keeps layouts locked safe during folding shifts on your Razr
    }
}
