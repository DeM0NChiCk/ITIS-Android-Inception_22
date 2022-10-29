package com.samples.itis_android_inception_22.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GridItemDimensionsModel(
    val horizontalItemWidth: Int,
    val horizontalItemHeight: Int,
    val verticalItemWidth: Int,
    val verticalItemHeight: Int,
    val halfMargin: Int
): Parcelable
