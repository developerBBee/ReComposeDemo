package com.example.recomposedemo.composable

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

data class UnStableData(var done: Boolean = false)
data class StableData(val done: Boolean = false)

@Composable
fun Demo1(modifier: Modifier = Modifier) {
    val unStableData by remember { mutableStateOf(UnStableData()) }
    var stableData by remember { mutableStateOf(StableData()) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(
            onClick = {
                unStableData.done = true
                stableData = StableData(done = true)
            }
        ) {
            Text(style = MaterialTheme.typography.titleLarge, text = "Run")
        }

        UnStableText(unStableData = unStableData)
        StableText(stableData = stableData)

        SideEffect {
            Log.d("RecomposeLog", "unStableData: ${unStableData.done}")
            Log.d("RecomposeLog", "stableData: ${stableData.done}")
        }
    }
}

@Composable
fun UnStableText(unStableData: UnStableData) {
    Text(
        text = "UnStableData",
        style = MaterialTheme.typography.titleLarge,
        color = if (unStableData.done) Color.Red else Color.Black
    )
}

@Composable
fun StableText(stableData: StableData) {
    Text(
        text = "StableData",
        style = MaterialTheme.typography.titleLarge,
        color = if (stableData.done) Color.Red else Color.Black
    )
}
