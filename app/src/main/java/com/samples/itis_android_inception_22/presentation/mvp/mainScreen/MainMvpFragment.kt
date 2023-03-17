package com.samples.itis_android_inception_22.presentation.mvp.mainScreen

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
import com.samples.itis_android_inception_22.presentation.base.BaseFragment
import com.samples.itis_android_inception_22.presentation.model.WeatherDataModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import retrofit2.HttpException

class MainMvpFragment : MvpAppCompatFragment(R.layout.fragment_main), MainView {

    private val viewBinding: FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    // private var presenter: MainMvpPresenter? = null

    private val presenter by moxyPresenter {
        MainMvpPresenter(DataDependency.getWeatherUseCase)
    }

/*  @InjectPresenter
    lateinit var presenter2: MainMvpPresenter

    @Inject
    lateinit var presenterProvider

    @ProvidePresenter
    fun providePresenter(): MainMvpPresenter = presenter.get()*/

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
                        presenter?.requestWeatherData(cityName)
                        true
                    }
                    else -> false
                }
            }
        }
    }

    override fun changeProgressBarState(isVisible: Boolean) {
        viewBinding.progressLayout.isVisible = isVisible
    }

    override fun setWeatherData(weatherData: WeatherDataModel) {
        with(viewBinding) {
            tempTv.text = getString(R.string.temperature_pattern, weatherData.temperature.toString())
            pressTv.text = getString(R.string.pressure_pattern, weatherData.pressure.toString())
            humidTv.text = getString(R.string.humidity_pattern, weatherData.humidity.toInt())
        }
    }

    override fun showErrorToast(ex: Throwable) {
        val errorMessage = (ex as? HttpException)?.message ?: ex.toString()
        Toast.makeText(
            requireContext(), getString(R.string.exception_occurred_pattern, errorMessage), Toast.LENGTH_SHORT
        ).show()
    }

    companion object {
        const val MAIN_FRAGMENT_TAG = "MAIN_FRAGMENT_TAG"

        fun getInstance() = MainMvpFragment()
    }
}