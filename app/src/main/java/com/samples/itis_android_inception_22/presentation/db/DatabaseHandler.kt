package com.samples.itis_android_inception_22.presentation.db

import android.content.Context
import androidx.room.Room
import com.samples.itis_android_inception_22.presentation.db.mapper.UserMappers
import com.samples.itis_android_inception_22.presentation.db.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object DatabaseHandler {

    const val databaseVersion: Int = 2

    private var roomDatabase: InceptionDatabase? = null

    fun dbInitialize(appContext: Context) {
        if (roomDatabase == null) {
            roomDatabase =
                Room.databaseBuilder(appContext, InceptionDatabase::class.java, "appDBName")
                    .addMigrations(Migrations.MIGRATION_1_2)
                    .build()
        }
    }

    suspend fun createUser(user: UserModel) {
        withContext(Dispatchers.IO) {
            roomDatabase?.getUserDao()?.createUser(UserMappers.map(user))
        }
    }
}