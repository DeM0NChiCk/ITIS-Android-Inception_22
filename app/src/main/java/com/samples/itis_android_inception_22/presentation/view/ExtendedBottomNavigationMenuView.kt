package com.samples.itis_android_inception_22.presentation.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.view.menu.MenuBuilder
import com.google.android.material.R
import com.google.android.material.bottomnavigation.BottomNavigationMenuView

@SuppressLint("RestrictedApi", "PrivateResource")
class ExtendedBottomNavigationMenuView(ctx: Context) : BottomNavigationMenuView(ctx) {

    private val inactiveItemMaxWidth: Int
    private val inactiveItemMinWidth: Int
    private val activeItemMaxWidth: Int
    private val activeItemMinWidth: Int

    private val eTempChildWidths: IntArray

    init {

        val layoutParams =
            FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                gravity = Gravity.CENTER
            }
        this.layoutParams = layoutParams

        inactiveItemMaxWidth =
            resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_max_width)
        inactiveItemMinWidth =
            resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_item_min_width)
        activeItemMaxWidth =
            resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_max_width)
        activeItemMinWidth =
            resources.getDimensionPixelSize(R.dimen.design_bottom_navigation_active_item_min_width)

        eTempChildWidths = IntArray(ExtendedBottomNavigationView.MAX_ITEM_COUNT)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val menu: MenuBuilder = menu ?: return
        val width = MeasureSpec.getSize(widthMeasureSpec)
        // Use visible item count to calculate widths
        val visibleCount = menu.visibleItems.size
        // Use total item counts to measure children
        val totalCount = childCount

        val parentHeight = MeasureSpec.getSize(heightMeasureSpec)
        val heightSpec = MeasureSpec.makeMeasureSpec(parentHeight, MeasureSpec.EXACTLY)

        if (isShifting(labelVisibilityMode, visibleCount) && isItemHorizontalTranslationEnabled) {
            val activeChild = getChildAt(selectedItemPosition)
            var activeItemWidth = activeItemMinWidth

            if (activeChild.visibility != View.GONE) {
                // Do an AT_MOST measure pass on the active child to get its desired width, and resize the
                // active child view based on that width
                activeChild.measure(
                    MeasureSpec.makeMeasureSpec(
                        activeItemMaxWidth,
                        MeasureSpec.AT_MOST
                    ), heightSpec
                )
                activeItemWidth = activeItemWidth.coerceAtLeast(activeChild.measuredWidth)
            }

            val inactiveCount = visibleCount - (if (activeChild.visibility != View.GONE) 1 else 0)
            val activeMaxAvailable = width - (inactiveCount * inactiveItemMinWidth)
            val activeWidth =
                activeMaxAvailable.coerceAtMost(activeItemWidth.coerceAtMost(activeItemMaxWidth))
            val inactiveMaxAvailable =
                (width - activeWidth) / (if (inactiveCount == 0) 1 else inactiveCount)
            val inactiveWidth = inactiveMaxAvailable.coerceAtMost(inactiveItemMaxWidth)
            var extra = width - activeWidth - (inactiveWidth * inactiveCount)

            for (index in 0 until totalCount) {
                if (getChildAt(index).visibility != View.GONE) {
                    eTempChildWidths[index] =
                        if (index == selectedItemPosition) activeWidth else inactiveWidth
                    if (extra > 0) {
                        eTempChildWidths[index]++
                        extra--
                    }
                } else {
                    eTempChildWidths[index] = 0
                }
            }
        } else {
            val maxAvailable = width / (if (visibleCount == 0) 1 else visibleCount)
            val childWidth = maxAvailable.coerceAtMost(activeItemMaxWidth)
            var extra = width - (childWidth * visibleCount)

            for (index in 0 until totalCount) {
                if (getChildAt(index).visibility != View.GONE) {
                    eTempChildWidths[index] = childWidth
                    if (extra > 0) {
                        eTempChildWidths[index]++
                        extra--
                    }
                } else {
                    eTempChildWidths[index] = 0
                }
            }
        }

        var totalWidth = 0
        for (index in 0 until totalCount) {
            val child: View = getChildAt(index)
            if (child.visibility == View.GONE) {
                continue
            }
            child.measure(
                MeasureSpec.makeMeasureSpec(eTempChildWidths[index], MeasureSpec.EXACTLY),
                heightSpec
            )
            child.layoutParams.width = child.measuredWidth
            totalWidth += child.measuredWidth
        }
        setMeasuredDimension(
            View.resolveSizeAndState(
                totalWidth,
                MeasureSpec.makeMeasureSpec(totalWidth, MeasureSpec.EXACTLY),
                0
            ),
            View.resolveSizeAndState(parentHeight, heightMeasureSpec, 0)
        )
    }
}