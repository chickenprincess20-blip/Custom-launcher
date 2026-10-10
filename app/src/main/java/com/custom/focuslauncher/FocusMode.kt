package com.custom.focuslauncher

import androidx.room.TypeConverter

enum class FocusType {
    DEFAULT, WORK, PERSONAL, FITNESS
}

data class FocusProfile(
    val type: FocusType,
    val profileName: String,
    val wallpaperResId: Int?,
    val allowedAppsList: List<String>
)

class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>): String = value.joinToString(",")

    @TypeConverter
    fun toStringList(value: String): List<String> = if (value.isEmpty()) emptyList() else value.split(",")
}
