package com.samples.itis_android_inception_22.presentation.fragments.mainPage2

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.FragmentInitialBinding

class InitialFragment : Fragment() {

    private var welcomeTextView: TextView? = null
    private var titleTextView: TextView? = null

    private var viewBinding: FragmentInitialBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = FragmentInitialBinding.inflate(inflater, container, false)
        initViews()
        /*return inflater.inflate(
            R.layout.fragment_initial,
            container,
            false
        )*/
        return viewBinding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        welcomeTextView = null
        titleTextView = null
        viewBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        welcomeTextView?.text = getString(R.string.initial_page_toolbar_template, "Tom")
        initClickListeners()
        initTextWatchers()
    }

    private fun initClickListeners() {
        viewBinding?.apply {
            summaryViewHolder.itemBtnIv.setOnClickListener {
                Toast.makeText(requireContext(), "First Button Message", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initViews() {
        welcomeTextView = requireActivity().findViewById(R.id.welcome_text_tv)
        titleTextView = requireActivity().findViewById(R.id.page_title_tv)
    }

    private fun initTextWatchers() {
        viewBinding?.apply {
            phoneEnterEt.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    if ((phoneEnterEt.text?.length ?: 0) < 5) {
                        phoneEnterEt.setText("+7 (9")
                    }
                }
            }
            phoneEnterEt.addTextChangedListener(
                onTextChanged = { input: CharSequence?, count: Int, start: Int, before: Int ->
                    input?.let { str ->
                        if (start > before) {
                            when {
                                str.length < 5 -> {
                                    phoneEnterEt.setText("+7 (9")
                                    phoneEnterEt.setSelection(phoneEnterEt.text?.length ?: 5)
                                }
                                else -> {
                                    // extra logic
                                }
                            }

                        }
                    }
                })
        }
    }

    companion object {
        const val INITIAL_FRAGMENT_TAG = "INITIAL_FRAGMENT_TAG"
        private const val NUMBER_TAG = "NUMBER_TAG"
        private const val TITLE_TAG = "TITLE_TAG"

        fun getInstance(number: Int, title: String) = InitialFragment().apply {
            arguments = bundleOf(NUMBER_TAG to number, TITLE_TAG to title)
        }
    }
}