package com.samples.itis_android_inception_22.presentation.fragments.secondPage

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.FragmentSecondPageBinding
import com.samples.itis_android_inception_22.presentation.base.BaseFragment

class SecondPageFragment : BaseFragment(R.layout.fragment_second_page) {

    private val viewBinding: FragmentSecondPageBinding by viewBinding(FragmentSecondPageBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("TEST TAG - SecondFragment onViewCreated")
        setToolbarTitle(R.string.second_fragment_title)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("TEST TAG - SecondFragment onDestroyView")
    }

    override fun onResume() {
        super.onResume()
        println("TEST TAG - SecondFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("TEST TAG - SecondFragment onPause")
    }

    companion object {
        const val SECOND_PAGE_FRAGMENT_TAG = "SECOND_PAGE_FRAGMENT_TAG"

        fun getInstance() = SecondPageFragment()
    }
}