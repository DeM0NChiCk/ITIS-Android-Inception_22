package com.samples.itis_android_inception_22.presentation.mvp.mainScreen

import com.samples.itis_android_inception_22.domain.usecase.GetWeatherByCityNameUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope

class MainMvpPresenter(
    private val getWeatherByCityNameUseCase: GetWeatherByCityNameUseCase
): MvpPresenter<MainView>() {

    override fun attachView(view: MainView?) {
        super.attachView(view)
    }

    fun requestWeatherData(city: String) {
        presenterScope.launch {
            viewState.changeProgressBarState(isVisible = true)
            delay(2000L)
            runCatching {
                getWeatherByCityNameUseCase(city)
            }.onSuccess { weatherDataModel ->
                viewState.changeProgressBarState(isVisible = false)
                viewState.setWeatherData(weatherDataModel)
            }.onFailure { ex ->
                viewState.changeProgressBarState(isVisible = false)
                viewState.showErrorToast(ex = ex)
            }
        }
    }
}

