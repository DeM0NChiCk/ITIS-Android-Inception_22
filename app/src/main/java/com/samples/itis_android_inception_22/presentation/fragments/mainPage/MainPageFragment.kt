package com.samples.itis_android_inception_22.presentation.fragments.mainPage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.FragmentMainPageBinding
import com.samples.itis_android_inception_22.presentation.base.BaseActivity
import com.samples.itis_android_inception_22.presentation.base.BaseFragment
import com.samples.itis_android_inception_22.presentation.fragments.secondPage.SecondPageFragment

class MainPageFragment : BaseFragment(R.layout.fragment_main_page) {

    private val viewBinding: FragmentMainPageBinding by viewBinding(FragmentMainPageBinding::bind)

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarTitle(R.string.main_fragment_title)
        initClickListeners()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
    }

    private fun initClickListeners() {
        with(viewBinding) {
            addFragmentBtn.setOnClickListener {
                //findNavController().navigate(R.id.action_mainPageFragment_to_editTextPageFragment)
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