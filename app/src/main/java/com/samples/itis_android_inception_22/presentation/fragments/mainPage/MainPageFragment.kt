package com.samples.itis_android_inception_22.presentation.fragments.mainPage

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.FragmentMainPageBinding
import com.samples.itis_android_inception_22.presentation.base.BaseActivity
import com.samples.itis_android_inception_22.presentation.base.BaseFragment
import com.samples.itis_android_inception_22.presentation.fragments.secondPage.SecondPageFragment

class MainPageFragment : BaseFragment(R.layout.fragment_main_page) {

    private val viewBinding: FragmentMainPageBinding by viewBinding(FragmentMainPageBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("TEST TAG - MainFragment onViewCreated")
        setToolbarTitle(R.string.main_fragment_title)
        initClickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("TEST TAG - MainFragment onDestroyView")
    }

    override fun onResume() {
        super.onResume()
        println("TEST TAG - MainFragment onResume")
    }

    override fun onPause() {
        super.onPause()
        println("TEST TAG - MainFragment onPause")
    }

    private fun initClickListeners() {
        with(viewBinding) {
            addFragmentBtn.setOnClickListener {
                (requireActivity() as? BaseActivity)?.addFragment(
                    SecondPageFragment.getInstance(),
                    SecondPageFragment.SECOND_PAGE_FRAGMENT_TAG,
                    detachCurrent = true
                )
            }
            replaceFragmentBtn.setOnClickListener {
                (requireActivity() as? BaseActivity)?.replaceFragment(
                    SecondPageFragment.getInstance(),
                    SecondPageFragment.SECOND_PAGE_FRAGMENT_TAG
                )
            }
        }
    }

    companion object {
        const val MAIN_PAGE_FRAGMENT_TAG = "MAIN_PAGE_FRAGMENT_TAG"

        fun getInstance() = MainPageFragment()
    }
}