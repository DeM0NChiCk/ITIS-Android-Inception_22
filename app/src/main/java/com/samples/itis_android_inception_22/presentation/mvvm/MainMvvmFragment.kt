package com.samples.itis_android_inception_22.presentation.mvvm

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.WeatherAppApplication
import com.samples.itis_android_inception_22.databinding.FragmentMainBinding
import com.samples.itis_android_inception_22.di.appComponent
import com.samples.itis_android_inception_22.di.lazyViewModel
import com.samples.itis_android_inception_22.presentation.MainActivity
import com.samples.itis_android_inception_22.presentation.base.BaseFragment
import com.samples.itis_android_inception_22.presentation.screen.SecondScreenFragment
import retrofit2.HttpException

class MainMvvmFragment : BaseFragment(R.layout.fragment_main) {

    private val viewBinding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    private val viewModel: MainFragmentViewModel by lazyViewModel {
        requireContext().appComponent().mainViewModel().create(assistedValue = "AssistedValue")
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as? WeatherAppApplication)?.appComponent?.inject(fragment = this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeData()
    }

    private fun initViews() {
        with(viewBinding) {
            // Вешаем слушатель на системную клавиатуру
            cityNameEt.setOnEditorActionListener { _, actionId, _ ->
                return@setOnEditorActionListener when (actionId) {
                    EditorInfo.IME_ACTION_SEND -> {
                        val cityName = cityNameEt.text?.toString() ?: return@setOnEditorActionListener false
                        viewModel.requestCityByName(cityName = cityName)
                        true
                    }
                    else -> false
                }
            }
            gotoNextScreenBtn.setOnClickListener {
                (requireActivity() as? MainActivity)?.navigate(
                    destination = SecondScreenFragment.getInstance(),
                    isNeedToDetach = true
                )
            }
        }
    }

    private fun observeData() {
        with(viewBinding) {
            viewModel.progressBarState.observe(viewLifecycleOwner) { isVisible ->
                progressLayout.isVisible = isVisible
            }
            viewModel.temperatureDataState.observe(viewLifecycleOwner) { weatherDataModel ->
                weatherDataModel?.let { data ->
                    tempTv.text = getString(R.string.temperature_pattern, data.temperature.toString())
                    pressTv.text = getString(R.string.pressure_pattern, data.pressure.toString())
                    humidTv.text = getString(R.string.humidity_pattern, data.humidity.toInt())
                }
            }
            viewModel.errorState.observe(viewLifecycleOwner) { ex ->
                ex?.let {
                    val errorMessage = (ex as? HttpException)?.message ?: ex.toString()
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.exception_occurred_pattern, errorMessage),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    companion object {
        const val MAIN_FRAGMENT_TAG = "MAIN_FRAGMENT_TAG"

        fun getInstance() = MainMvvmFragment()
    }
}