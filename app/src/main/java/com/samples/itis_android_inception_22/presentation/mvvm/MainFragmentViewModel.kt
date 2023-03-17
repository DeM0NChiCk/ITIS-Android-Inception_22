package com.samples.itis_android_inception_22.presentation.mvvm

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.samples.itis_android_inception_22.di.DataDependency
import com.samples.itis_android_inception_22.di.ViewModelArgsKeys
import com.samples.itis_android_inception_22.domain.usecase.GetWeatherByCityNameUseCase
import com.samples.itis_android_inception_22.presentation.model.WeatherDataModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragmentViewModel(
    private val getWeatherUseCase: GetWeatherByCityNameUseCase
) : ViewModel() {

    private val _progressBarState: MutableLiveData<Boolean> = MutableLiveData(false)
    val progressBarState: LiveData<Boolean> = _progressBarState

    private val _temperatureDataState: MutableLiveData<WeatherDataModel?> = MutableLiveData(null)
    val temperatureDataState: LiveData<WeatherDataModel?> = _temperatureDataState

    private val _errorState: MutableLiveData<Throwable> = MutableLiveData(null)
    val errorState: LiveData<Throwable> = _errorState

    fun requestCityByName(cityName: String) {
        viewModelScope.launch {
            _progressBarState.value = true
            delay(2000L)
            runCatching {
                getWeatherUseCase(cityName)
            }.onSuccess { weatherDataModel ->
                _progressBarState.value = false
                _temperatureDataState.postValue(weatherDataModel)
            }.onFailure { ex ->
                _progressBarState.value = false
                _errorState.value = ex
            }
        }
    }

    companion object {

        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val getWeatherUseCase =
                    extras[ViewModelArgsKeys.getWeatherUseCaseKey] ?: throw IllegalArgumentException()
                return (MainFragmentViewModel(getWeatherUseCase) as? T) ?: throw java.lang.IllegalStateException()
            }
        }
    }
}