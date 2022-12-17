package com.samples.itis_android_inception_22.presentation.db

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migrations {

    val MIGRATION_1_2 = object : Migration(1, 2) {

        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE 'user_pets' ('name' TEXT NOT NULL,'type' TEXT NOT NULL,'pet_id' INTEGER NOT NULL PRIMARY KEY)")
        }
    }
}