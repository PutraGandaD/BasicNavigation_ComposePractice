package com.putragandad.basicnavigationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.putragandad.basicnavigationcompose.ui.theme.BasicNavigationComposeTheme
import kotlinx.serialization.Serializable

@Serializable
object FirstScreen

@Serializable
object SecondScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicNavigationComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = FirstScreen) {
        composable<FirstScreen> {
            FirstScreen(
                name = "Android",
                modifier = modifier,
                onNavigateToSecondScreen = {
                    navController.navigate(route = SecondScreen)
                }
            )
        }

        composable<SecondScreen> {
            SecondScreen(
                modifier = modifier,
                onNavigateToFirstScreen = {
                    navController.navigateUp()
                }
            )
        }
    }
}

@Composable
fun FirstScreen(
    name: String,
    modifier: Modifier = Modifier,
    onNavigateToSecondScreen: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = "Hello, its me $name",
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
        Button(
            onClick = {
                onNavigateToSecondScreen()
            }
        ) {
            Text("I was wondering...")
        }
    }
}

@Composable
fun SecondScreen(
    modifier: Modifier = Modifier,
    onNavigateToFirstScreen: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        Text(
            text = "After all these years, would you'd like to meet?",
            modifier = Modifier
                .padding(bottom = 8.dp)
        )
        Button(
            onClick = {
                onNavigateToFirstScreen()
            }
        ) {
            Text("No. (Back)")
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun FirstScreenPreview() {
//    BasicNavigationComposeTheme {
//        FirstScreen("Android")
//    }
//}

@Preview(showBackground = true)
@Composable
fun SecondScreenPreview() {
    BasicNavigationComposeTheme {
        SecondScreen(
            onNavigateToFirstScreen = {

            }
        )
    }
}