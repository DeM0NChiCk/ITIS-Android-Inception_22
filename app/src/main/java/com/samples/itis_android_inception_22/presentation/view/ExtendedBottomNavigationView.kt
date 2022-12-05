package com.samples.itis_android_inception_22.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarMenuView

class ExtendedBottomNavigationView(ctx: Context, attrs: AttributeSet): BottomNavigationView(ctx, attrs) {

    @SuppressLint("RestrictedApi")
    override fun createNavigationBarMenuView(context: Context): NavigationBarMenuView {
        return ExtendedBottomNavigationMenuView(context)
    }

    override fun getMaxItemCount(): Int {
        return MAX_ITEM_COUNT
    }

    companion object {
        const val MAX_ITEM_COUNT = 6
    }
}