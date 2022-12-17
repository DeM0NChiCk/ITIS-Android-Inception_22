package com.samples.itis_android_inception_22.presentation.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.samples.itis_android_inception_22.presentation.db.entity.UserEntity
import com.samples.itis_android_inception_22.presentation.db.model.UserUpdateModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createUser(user: UserEntity)

    @Update(entity = UserEntity::class)
    suspend fun updateUserData(userDataModel: UserUpdateModel)

    @Transaction
    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<UserEntity>?

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun getById(id: Int): UserEntity?

    // Пример удаления всех данных из таблицы через Query
    @Query("DELETE FROM users")
    suspend fun deleteAllDataWithQuery()

    // Пример удаления данных через @Delete
    @Delete(entity = UserEntity::class)
    suspend fun deleteDataById(id: Int)

    @Insert
    suspend fun insertSomeData()

    @Transaction
    suspend fun updateAllData() {
        deleteAllDataWithQuery()
        insertSomeData()
    }
}