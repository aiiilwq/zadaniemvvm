package com.example.mvvmtimer.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.mvvmtimer.StopwatchViewModel
import com.example.mvvmtimer.TimerViewModel

@Composable
fun MainScreen(
    timerViewModel: TimerViewModel,
    stopwatchViewModel: StopwatchViewModel
) {
    val stopwatchState by stopwatchViewModel.stopwatchState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Таймер
        Text(text = "Таймер", style = MaterialTheme.typography.headlineLarge)
        TextField(
            value = timerViewModel.inputTime.value.toString(),
            onValueChange = { value ->
                timerViewModel.inputTime.value = value.toLongOrNull() ?: 0
            },
            label = { Text("Введите время в секундах") }
        )
        Text(text = "Осталось: ${timerViewModel.timeLeft.value} сек", style = MaterialTheme.typography.bodyLarge)
        Row {
            Button(onClick = { timerViewModel.startTimer() }) { Text("Старт") }
            Button(onClick = { timerViewModel.stopTimer() }) { Text("Стоп") }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Секундомер
        Text(text = "Секундомер", style = MaterialTheme.typography.headlineLarge)
        Text(text = "Прошло: ${stopwatchState.elapsedTime / 1000} сек", style = MaterialTheme.typography.bodyLarge)
        Row {
            Button(onClick = { stopwatchViewModel.startStopwatch() }) { Text("Старт") }
            Button(onClick = { stopwatchViewModel.stopStopwatch() }) { Text("Стоп") }
        }
    }
}
