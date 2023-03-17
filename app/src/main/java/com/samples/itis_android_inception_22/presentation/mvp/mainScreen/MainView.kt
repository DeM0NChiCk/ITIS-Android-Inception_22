package com.samples.itis_android_inception_22.presentation.mvp.mainScreen

import com.samples.itis_android_inception_22.presentation.model.WeatherDataModel
import moxy.MvpView
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@AddToEndSingle
interface MainView: MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun changeProgressBarState(isVisible: Boolean)

    @OneExecution
    fun setWeatherData(weatherData: WeatherDataModel)

    fun showErrorToast(ex: Throwable)
}