package com.custom.focuslauncher

import android.content.Context
import android.content.SharedPreferences

class LauncherStateController(private val context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("FocusLauncherPrefs", Context.MODE_PRIVATE)
    private var currentFocus: FocusType = FocusType.DEFAULT

    fun setCurrentFocusMode(type: FocusType, onProfileSwapped: (FocusProfile) -> Unit) {
        currentFocus = type
        prefs.edit().putString("ACTIVE_FOCUS_MODE", type.name).apply()
        val profile = loadProfileData(type)
        onProfileSwapped(profile)
    }

    fun getCurrentFocusMode(): FocusType {
        val name = prefs.getString("ACTIVE_FOCUS_MODE", FocusType.DEFAULT.name)
        return FocusType.valueOf(name ?: FocusType.DEFAULT.name)
    }

    private fun loadProfileData(type: FocusType): FocusProfile {
        return when(type) {
            FocusType.WORK -> FocusProfile(type, "Work Focus", null, listOf("com.google.android.gm", "com.microsoft.teams"))
            FocusType.PERSONAL -> FocusProfile(type, "Personal Focus", null, listOf("com.instagram.android", "com.spotify.music"))
            else -> FocusProfile(type, "Default Screen", null, emptyList())
        }
    }
}
