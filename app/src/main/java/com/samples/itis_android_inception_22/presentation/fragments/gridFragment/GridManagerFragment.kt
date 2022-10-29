package com.samples.itis_android_inception_22.presentation.fragments.gridFragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.FragmentGridManagerBinding
import com.samples.itis_android_inception_22.presentation.base.BaseFragment

class GridManagerFragment : BaseFragment(R.layout.fragment_grid_manager) {

    private val viewBinding: FragmentGridManagerBinding by viewBinding(FragmentGridManagerBinding::bind)

    private var rvAdapter: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)

    }

    companion object {
        const val GRID_MANAGER_FRAGMENT_TAG = "GRID_MANAGER_FRAGMENT_TAG"

        fun getInstance() = GridManagerFragment()
    }
}