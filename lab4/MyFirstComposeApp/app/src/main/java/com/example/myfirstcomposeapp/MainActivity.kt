package com.example.myfirstcomposeapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstcomposeapp.ui.theme.MyFirstComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           App("Світ", "Щастя в СВОЇХ грошах!")
        }
    }
}

@Composable
fun Greeting(name: String) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            text = "Привіт, $name!",
            modifier = Modifier
                .wrapContentSize(),
            style = TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        )
    }
}


@Composable
fun Quote(quoteText: String) {
    var quoteColor by remember { mutableStateOf(Color.Black) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = quoteText,
            style = TextStyle(
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
                color = quoteColor
            ),
            color = quoteColor
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            quoteColor = if (quoteColor == Color.Black) {
                Color.Green
            } else {
                Color.Black
            }
        }, colors = ButtonDefaults.buttonColors(
            containerColor = Color.Magenta
        )) {
            Text("Змінити колір цитати")
        }
    }
}

@Composable
fun App(name: String, quoteText: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Greeting(name)

        Spacer(modifier = Modifier.height(24.dp))

        Quote(quoteText)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewApp() {
    MyFirstComposeAppTheme {
        App(name = "Світ", quoteText = "Щастя в СВОЇХ грошах!")
    }
}


