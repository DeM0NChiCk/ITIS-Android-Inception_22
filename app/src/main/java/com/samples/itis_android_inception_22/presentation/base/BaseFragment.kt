package com.samples.itis_android_inception_22.presentation.base

import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.samples.itis_android_inception_22.R

abstract class BaseFragment(layout: Int) : Fragment(layout) {

    open fun getToolbar(): Toolbar? = view?.findViewById(R.id.toolbar)

    protected fun setToolbarTitle(@StringRes titleRes: Int) {
        getToolbar()?.setTitle(titleRes)
    }

    protected fun setToolbarTitle(title: String) {
        getToolbar()?.title = title
    }

    open fun onFragmentBackPressed(): Boolean {
        (requireActivity() as? BaseActivity)?.onBackPressed()
        return false
    }
}