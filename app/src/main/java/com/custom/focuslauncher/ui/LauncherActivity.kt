package com.custom.focuslauncher.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.custom.focuslauncher.R
import com.custom.focuslauncher.LauncherStateController
import com.custom.focuslauncher.data.FocusType

class LauncherActivity : Activity() {

    private lateinit var stateController: LauncherStateController
    private var launcherPager: LauncherPager? = null
    private var commandCenter: CommandCenterView? = null
    private var toggleButton: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Inflate the xml layout interface first so the system can map components
        setContentView(R.layout.activity_launcher)

        stateController = LauncherStateController(this)
        
        // Capture the UI elements using safe optional bindings
        launcherPager = findViewById(R.id.home_screen_pager)
        commandCenter = findViewById(R.id.command_center_overlay)
        toggleButton = findViewById(R.id.btn_command_center_toggle)

        // Attach click configurations safely
        toggleButton?.setOnClickListener {
            commandCenter?.let { cc ->
                cc.visibility = if (cc.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            }
        }

        commandCenter?.onFocusSelectedListener = { selectedFocus ->
            stateController.setCurrentFocusMode(selectedFocus) { profile ->
                // Layout swap routines live here
            }
            commandCenter?.visibility = View.GONE
        }

        val currentMode = stateController.getCurrentFocusMode()
        stateController.setCurrentFocusMode(currentMode) { }
    }

    override fun onConfigurationChanged(newConfig: android.content.res.Configuration) {
        super.onConfigurationChanged(newConfig)
        // Prevents the canvas layout from breaking when the Motorola razr folds/unfolds
    }
}
