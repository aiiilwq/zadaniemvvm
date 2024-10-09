package com.example.mvvmtimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.mvvmtimer.ui.MainScreen
import com.example.mvvmtimer.TimerViewModel
import com.example.mvvmtimer.StopwatchViewModel

class MainActivity : ComponentActivity() {
    private val timerViewModel: TimerViewModel by viewModels()
    private val stopwatchViewModel: StopwatchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(timerViewModel = timerViewModel, stopwatchViewModel = stopwatchViewModel)
        }
    }
}
