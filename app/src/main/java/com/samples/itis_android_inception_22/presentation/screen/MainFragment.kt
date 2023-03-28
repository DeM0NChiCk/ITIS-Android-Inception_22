package com.samples.itis_android_inception_22.presentation.screen

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.samples.itis_android_inception_22.R
import com.samples.itis_android_inception_22.databinding.FragmentMainBinding
import com.samples.itis_android_inception_22.di.DataDependency
import com.samples.itis_android_inception_22.presentation.MainActivity
import com.samples.itis_android_inception_22.presentation.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val viewBinding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        with(viewBinding) {
            // Вешаем слушатель на системную клавиатуру
            cityNameEt.setOnEditorActionListener { _, actionId, _ ->
                return@setOnEditorActionListener when (actionId) {
                    EditorInfo.IME_ACTION_SEND -> {
                        val cityName = cityNameEt.text?.toString() ?: return@setOnEditorActionListener false
                        requestCityByName(cityName = cityName)
                        true
                    }
                    else -> false
                }
            }
            gotoNextScreenBtn.setOnClickListener {
                (requireActivity() as? MainActivity)?.navigate(SecondScreenFragment.getInstance(), isNeedToDetach = true)
            }
        }
    }

    private fun requestCityByName(cityName: String) {
        lifecycleScope.launch {
            viewBinding.progressLayout.isVisible = true
            delay(2000L)
            runCatching {
                DataDependency.getWeatherUseCase(cityName)
            }.onSuccess { weatherDataModel ->
                viewBinding.progressLayout.isVisible = false
                with(viewBinding) {
                    tempTv.text = getString(R.string.temperature_pattern, weatherDataModel.temperature.toString())
                    pressTv.text = getString(R.string.pressure_pattern, weatherDataModel.pressure.toString())
                    humidTv.text = getString(R.string.humidity_pattern, weatherDataModel.humidity.toInt())
                }
            }.onFailure { ex ->
                viewBinding.progressLayout.isVisible = false
                val errorMessage = (ex as? HttpException)?.message ?: ex.toString()
                Toast.makeText(
                    requireContext(), getString(R.string.exception_occurred_pattern, errorMessage), Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        const val MAIN_FRAGMENT_TAG = "MAIN_FRAGMENT_TAG"

        fun getInstance() = MainFragment()
    }
}