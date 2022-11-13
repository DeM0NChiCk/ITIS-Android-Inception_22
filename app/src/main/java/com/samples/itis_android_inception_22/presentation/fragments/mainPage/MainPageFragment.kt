package com.samples.itis_android_inception_22.presentation.fragments.mainPage

import android.app.AlarmManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.samples.itis_android_inception_22.utils.NotificationsManager

class MainPageFragment : BaseFragment(R.layout.fragment_main_page) {

    private val viewBinding: FragmentMainPageBinding by viewBinding(FragmentMainPageBinding::bind)
    private var notificationsManager: NotificationsManager? = null

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
        notificationsManager = NotificationsManager(ctx = requireContext())
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
        val handler = Handler(Looper.getMainLooper())
        with(viewBinding) {
            var id = 101
            var text = "sample"
            var desc = "Description"
            addFragmentBtn.setOnClickListener {
                handler.postDelayed({
                    notificationsManager?.createBasicNotification(id, text, desc)
                    id = 102
                    text = "Edited sample"
                    desc = "edited desc"
                }, 2000L)
            }
            replaceFragmentBtn.setOnClickListener {
                (requireActivity() as? BaseActivity)?.replaceFragment(
                    SecondPageFragment.getInstance(),
                    SecondPageFragment.SECOND_PAGE_FRAGMENT_TAG
                )
            }
        }
    }

    /*private fun alarmManager() {
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.apply {
            set()
        }
    }*/

    companion object {
        const val MAIN_PAGE_FRAGMENT_TAG = "MAIN_PAGE_FRAGMENT_TAG"

        fun getInstance() = MainPageFragment()
    }
}