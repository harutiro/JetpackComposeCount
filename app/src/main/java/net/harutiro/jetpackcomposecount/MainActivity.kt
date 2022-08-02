package net.harutiro.jetpackcomposecount

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import net.harutiro.jetpackcomposecount.ui.theme.JetpackComposeCountTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeCountTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Greeting("Android")
                        Photo()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Column(
//        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var count by remember { mutableStateOf(0) }
        Text(
            text = "Tap count: $count",
            modifier = Modifier.padding(20.dp)
        )
        Button(
            onClick = { count++ }
        ) {
            Text(text = "Count up!")
        }
    }
}

@Composable
fun Photo(){
    Column(
//        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var count by remember { mutableStateOf(0)}
        Text(
            text = "Tap count: $count",
            modifier = Modifier.padding(10.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clickable { count++ }
        )
    }
}

@Composable
fun MyBox(){
    //draggable
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {

        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }

        Box(
            modifier = Modifier
                .offset{ IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .background(Color.Red)
                .size(50.dp)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consumeAllChanges()
                        offsetX += dragAmount.x
                        offsetY += dragAmount.y
                    }
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeCountTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Greeting("Android")
            Photo()
//            MyBox()
        }
    }
}