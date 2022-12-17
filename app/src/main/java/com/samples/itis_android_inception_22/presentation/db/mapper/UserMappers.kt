package com.samples.itis_android_inception_22.presentation.db.mapper

import com.samples.itis_android_inception_22.presentation.db.entity.UserEntity
import com.samples.itis_android_inception_22.presentation.db.model.AddressModel
import com.samples.itis_android_inception_22.presentation.db.model.UserModel

object UserMappers {

    fun map(user: UserModel): UserEntity {
        with(user) {
            return UserEntity(
                id, userName, userPassword, addressModel
            )
        }
    }
}