package com.custom.focuslauncher.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "launcher_components")
data class LauncherComponent(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val focusModeOwner: String,
    val componentType: String,
    val packageOrActionIdentifier: String,
    val xCoordinate: Float,
    val yCoordinate: Float,
    val layoutWidth: Int,
    val layoutHeight: Int,
    val angleRotation: Float = 0f,
    val alphaTransparency: Float = 1.0f,
    val customIconPath: String? = null,
    val textLabelOverride: String? = null,
    val customHexColor: String? = null
)