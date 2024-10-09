package com.example.mvvmtimer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StopwatchViewModel : ViewModel() {
    private val _stopwatchState = MutableStateFlow(StopwatchState())
    val stopwatchState: StateFlow<StopwatchState> = _stopwatchState

    fun startStopwatch() {
        _stopwatchState.value = _stopwatchState.value.copy(isRunning = true)
        viewModelScope.launch {
            while (_stopwatchState.value.isRunning) {
                delay(1000L)  // Задержка 1 секунда
                _stopwatchState.value = _stopwatchState.value.copy(elapsedTime = _stopwatchState.value.elapsedTime + 1000L)
            }
        }
    }

    fun stopStopwatch() {
        _stopwatchState.value = _stopwatchState.value.copy(isRunning = false)
    }

    fun resetStopwatch() {
        _stopwatchState.value = StopwatchState()
    }
}

data class StopwatchState(
    val elapsedTime: Long = 0L,
    val isRunning: Boolean = false
)
