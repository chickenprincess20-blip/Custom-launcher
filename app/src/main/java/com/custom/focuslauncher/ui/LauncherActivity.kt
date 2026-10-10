package com.custom.focuslauncher

import android.content.Context
import android.content.SharedPreferences

class LauncherStateController(private val context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("FocusLauncherPrefs", Context.MODE_PRIVATE)

    fun setCurrentFocusMode(type: FocusType, onProfileSwapped: (com.custom.focuslauncher.data.FocusProfile) -> Unit) {
        prefs.edit().putString("ACTIVE_FOCUS_MODE", type.name).apply()
        val profile = loadProfileData(type)
        onProfileSwapped(profile)
    }

    fun getCurrentFocusMode(): FocusType {
        val name = prefs.getString("ACTIVE_FOCUS_MODE", FocusType.DEFAULT.name)
        return FocusType.valueOf(name ?: FocusType.DEFAULT.name)
    }

    private fun loadProfileData(type: FocusType): com.custom.focuslauncher.data.FocusProfile {
        return when(type) {
            FocusType.WORK -> com.custom.focuslauncher.data.FocusProfile(type, "Work Focus", null, listOf("com.google.android.gm"))
            FocusType.PERSONAL -> com.custom.focuslauncher.data.FocusProfile(type, "Personal Focus", null, listOf("com.instagram.android"))
            else -> com.custom.focuslauncher.data.FocusProfile(type, "Default Screen", null, emptyList())
        }
    }
}
