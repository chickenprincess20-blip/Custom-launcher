package com.custom.focuslauncher.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "launcher_pages")
data class LauncherPage(
    @PrimaryKey val pageIndex: Int,
    val customThemeIdentifier: String,
    val assignedFocusModes: List<String>
)
