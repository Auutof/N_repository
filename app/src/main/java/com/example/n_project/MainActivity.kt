package com.example.n_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.n_project.ui.theme.N_projectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            N_projectTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var showGreeting by remember { mutableStateOf(true) }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (showGreeting) {
                GreetingFeature()
            } else {
                ColorThemeFeature()
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = { showGreeting = !showGreeting }) {
                Text(
                    if (showGreeting) "Switch to Color Theme Feature"
                    else "Switch to Greeting Feature"
                )
            }
        }
    }
}

@Composable
fun GreetingFeature(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var greetingText by remember { mutableStateOf("Hello!") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Enter your name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            greetingText = if (name.isNotBlank()) "Hello, $name!" else "Hello!"
        }) {
            Text("Say Hello")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = greetingText,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
fun ColorThemeFeature(modifier: Modifier = Modifier) {
    var isDarkTheme by remember { mutableStateOf(false) }
    var usePrimary by remember { mutableStateOf(true) }

    val textColor = if (usePrimary) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
    val backgroundColor = if (isDarkTheme) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.background

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hello from Color Theme Feature!",
            color = textColor,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { isDarkTheme = !isDarkTheme }) {
            Text(if (isDarkTheme) "Switch to Light Theme" else "Switch to Dark Theme")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { usePrimary = !usePrimary }) {
            Text("Change Text Color")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    N_projectTheme {
        MainScreen()
    }
}