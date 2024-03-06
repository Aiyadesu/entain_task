package com.richard.entain.racing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richard.domain.racing.usecase.RacingUseCase
import com.richard.domain.racing.model.RacingDomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RacingViewModel @Inject constructor(
    private val racingUseCase: RacingUseCase
) : ViewModel() {
    private val _racingData = MutableLiveData<RacingDomainModel>()
    val dataResult: LiveData<RacingDomainModel> get() = _racingData

    init {
        getRacingData()
    }

    private fun getRacingData() {
        viewModelScope.launch {
            _racingData.value = racingUseCase.getRaces()
        }
    }
}