package com.samples.itis_android_inception_22.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component
interface RegistrationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(ctx: Context): Builder

        fun build(): RegistrationComponent
    }
}