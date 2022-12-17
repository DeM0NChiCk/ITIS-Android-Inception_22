package com.samples.itis_android_inception_22.presentation.db.model

import androidx.room.ColumnInfo

data class AddressModel(
    val country: String,
    @ColumnInfo(name = "inner_address") val innerAddress: String,
    @ColumnInfo(name = "post_code") val postCode: String
)
