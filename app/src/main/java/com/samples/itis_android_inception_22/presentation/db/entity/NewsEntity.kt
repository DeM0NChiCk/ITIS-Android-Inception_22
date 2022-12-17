package com.samples.itis_android_inception_22.presentation.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey val id: Long,
    val title: String,
    val description: String,
    val imageUrl: String?
)
