package com.samples.itis_android_inception_22.utils

import android.content.Context
import android.util.TypedValue

/**  Сначала указывается название класса, который вы расширяете,
  *  далее через точку указываете название функции, аргументы (как при ообычном объявлении функции) */

fun Context.dpToPx(valueInDp: Float): Float {
    val metrics = this.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics)
}

/**  Альтернативный вариант объявления той же функции, если вы не хотите расширять какой-то из классов.
  *  Иногда бывает удобен такой вариант записи
  *
fun dpToPx(ctx: Context, valueInDp: Float): Float {
    val metrics = ctx.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics)
} */