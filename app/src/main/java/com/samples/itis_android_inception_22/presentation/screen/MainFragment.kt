package com.samples.itis_android_inception_22.presentation.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.FragmentMainBinding
import com.samples.itis_android_inception_22.presentation.base.BaseFragment

class MainFragment: BaseFragment(R.layout.fragment_main) {

    private val viewBinding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun getInstance() = MainFragment()
    }
}