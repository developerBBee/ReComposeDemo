package com.example.recomposedemo.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

enum class Screen { TOP, DEMO1, DEMO2 }

@Composable
fun Top(
    modifier: Modifier = Modifier
) {
    var screen by rememberSaveable { mutableStateOf(Screen.TOP) }

    Box(modifier = modifier) {
        when (screen) {
            Screen.TOP -> {
                TopContent(
                    modifier = Modifier.fillMaxSize(),
                    onClickDemo1 = { screen = Screen.DEMO1 },
                    onClickDemo2 = { screen = Screen.DEMO2 }
                )
            }

            Screen.DEMO1 -> {
                Demo1(modifier = Modifier.fillMaxSize())
            }

            Screen.DEMO2 -> {
                Demo2(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun TopContent(
    modifier: Modifier = Modifier,
    onClickDemo1: () -> Unit,
    onClickDemo2: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onClickDemo1) { Text("DEMO1") }
        Button(onClick = onClickDemo2) { Text("DEMO2") }
    }
}