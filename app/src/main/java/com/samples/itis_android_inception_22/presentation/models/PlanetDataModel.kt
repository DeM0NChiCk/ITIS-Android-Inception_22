package com.samples.itis_android_inception_22.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlanetDataModel(
    val planetName: String,
    val descriptionText: String,
    val imageUrl: String,
    var isChecked: Boolean = false
) : Parcelable
