package com.example.recomposedemo.composable

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp

// List(Collection interface) は Unstable
private val MutableContents = mutableListOf<Int>()
private val Contents: List<Int> = MutableContents

private fun addValueToList(value: Int) {
    MutableContents.add(value)
}

@Composable
fun Demo2(modifier: Modifier = Modifier) {
    var isOdd by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier.padding(32.dp),
            onClick = {
                addValueToList((0..100).random())
                isOdd = !isOdd
            }
        ) {
            Text("Add random value")
        }

        Text(
            modifier = Modifier.padding(32.dp),
            text = if (isOdd) "Odd" else "Even",
            style = MaterialTheme.typography.titleLarge,
        )

        ListContent(list = Contents)
        SideEffect {
            Log.d("RecomposeLog", "Contents: $Contents")
        }
    }
}

@Composable
fun ListContent(list: List<Int>) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(list) { value ->
            Text(
                text = value.toString(),
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}
