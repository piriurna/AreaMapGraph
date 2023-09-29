package com.piriurna.libs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.libs.ui.theme.AreaMapGraphTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AreaMapGraphTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.size(250.dp), color = MaterialTheme.colorScheme.background) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Graph(
        modifier = Modifier,
        data = mapOf(
            "a" to 0.3f,
            "b" to 0.2f,
            "c" to 0.15f,
            "d" to 0.125f,
            "e" to 0.125f,
            "f" to 0.1f
        )
    )
}

@Composable
fun Graph(data: Map<String, Float>, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val totalArea = size.width * size.height
        var remainingWidth = size.width
        var remainingHeight = size.height
        var offsetX = 0f
        var offsetY = 0f
        var isHorizontalSplit = true

        val colorList = listOf(
            Color.Red,
            Color.Black,
            Color.Green,
            Color.Cyan,
            Color.Gray,
            Color.Yellow
        )
        data.entries.forEachIndexed { index, entry ->
            val percentage = entry.value
            val chosenColor = colorList[index % colorList.size]
            if (isHorizontalSplit) {
                val width = (totalArea * percentage) / remainingHeight
                drawRect(
                    color = chosenColor,
                    topLeft = Offset(offsetX, offsetY),
                    size = Size(width, remainingHeight)
                )
                offsetX += width
                remainingWidth -= width
            } else {
                val height = (totalArea * percentage) / remainingWidth
                drawRect(
                    color = chosenColor,
                    topLeft = Offset(offsetX, offsetY),
                    size = Size(remainingWidth, height)
                )
                offsetY += height
                remainingHeight -= height
            }
            isHorizontalSplit = !isHorizontalSplit
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AreaMapGraphTheme {
        Greeting("Android")
    }
}