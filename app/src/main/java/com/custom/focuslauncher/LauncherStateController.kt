package com.custom.focuslauncher

import android.content.Context
import android.content.SharedPreferences
import com.custom.focuslauncher.data.FocusProfile

class LauncherStateController(private val context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("FocusLauncherPrefs", Context.MODE_PRIVATE)

    // Interface to handle profile swaps cleanly without lambda errors
    interface OnProfileSwappedListener {
        fun onProfileSwapped(profile: FocusProfile)
    }

    fun setCurrentFocusMode(type: FocusType, listener: OnProfileSwappedListener) {
        prefs.edit().putString("ACTIVE_FOCUS_MODE", type.name).apply()
        val profile = loadProfileData(type)
        listener.onProfileSwapped(profile)
    }

    fun getCurrentFocusMode(): FocusType {
        val name = prefs.getString("ACTIVE_FOCUS_MODE", FocusType.DEFAULT.name)
        return FocusType.valueOf(name ?: FocusType.DEFAULT.name)
    }

    private fun loadProfileData(type: FocusType): FocusProfile {
        return when(type) {
            FocusType.WORK -> FocusProfile(type, "Work Focus", null, listOf("com.google.android.gm"))
            FocusType.PERSONAL -> FocusProfile(type, "Personal Focus", null, listOf("com.instagram.android"))
            else -> FocusProfile(type, "Default Screen", null, emptyList())
        }
    }
}
