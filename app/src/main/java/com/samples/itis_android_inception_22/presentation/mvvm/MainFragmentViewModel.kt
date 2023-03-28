package com.samples.itis_android_inception_22.presentation.mvvm

import android.util.Log
import androidx.lifecycle.*
import com.samples.itis_android_inception_22.domain.usecase.GetWeatherByCityNameUseCase
import com.samples.itis_android_inception_22.presentation.model.WeatherDataModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragmentViewModel @AssistedInject constructor(
    @Assisted(ASSISTED_VALUE_KEY) private val assistedValue: String,
    private val getWeatherUseCase: GetWeatherByCityNameUseCase
) : ViewModel() {

    private val _progressBarState: MutableLiveData<Boolean> = MutableLiveData(false)
    val progressBarState: LiveData<Boolean> = _progressBarState

    private val _temperatureDataState: MutableLiveData<WeatherDataModel?> = MutableLiveData(null)
    val temperatureDataState: LiveData<WeatherDataModel?> = _temperatureDataState

    private val _errorState: MutableLiveData<Throwable> = MutableLiveData(null)
    val errorState: LiveData<Throwable> = _errorState

    init {
        Log.d("MainViewModelTag", assistedValue)
    }

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

    @AssistedFactory
    interface Factory {
        fun create(@Assisted(ASSISTED_VALUE_KEY) assistedValue: String): MainFragmentViewModel
    }

    companion object {

        private const val ASSISTED_VALUE_KEY = "ASSISTED_STRING_VALUE"
    }
}