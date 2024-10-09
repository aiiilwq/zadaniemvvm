package com.example.mvvmtimer

import android.util.Log
import androidx.compose.runtime.MutableLongState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class TimerState(var timeLeft: Long = 0)

class TimerViewModel : ViewModel() {
    private var _timerState = TimerState()
    private var timerJob: Job? = null
    private val _timeLeft = mutableStateOf(_timerState.timeLeft)
    val timeLeft: MutableState<Long> get() = _timeLeft

    var inputTime: MutableLongState = mutableLongStateOf(0)

    fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            var remainingTime = inputTime.value!!
            while (remainingTime > 0) {
                _timerState.timeLeft = remainingTime
                _timeLeft.value = _timerState.timeLeft
                delay(1000)
                remainingTime -= 1
                Log.e("TimerError", _timeLeft.value.toString())
            }
            _timerState.timeLeft = 0
            _timeLeft.value = _timerState.timeLeft
        }
    }

    fun stopTimer() {
        timerJob?.cancel()
    }
}
