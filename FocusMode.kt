package com.custom.focuslauncher

enum class FocusType {
    DEFAULT, WORK, PERSONAL, FITNESS
}

data class FocusProfile(
    val type: FocusType,
    val profileName: String,
    val wallpaperResId: Int?,
    val allowedAppsList: List<String>
)