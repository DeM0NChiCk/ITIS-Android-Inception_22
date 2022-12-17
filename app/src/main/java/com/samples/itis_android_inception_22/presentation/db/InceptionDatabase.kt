package com.samples.itis_android_inception_22.presentation.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.samples.itis_android_inception_22.presentation.db.dao.UserDao
import com.samples.itis_android_inception_22.presentation.db.entity.NewsEntity
import com.samples.itis_android_inception_22.presentation.db.entity.PetsEntity
import com.samples.itis_android_inception_22.presentation.db.entity.UserEntity

@Database(
    entities = [UserEntity::class, NewsEntity::class, PetsEntity::class],
    version = DatabaseHandler.databaseVersion
)
abstract class InceptionDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao
}