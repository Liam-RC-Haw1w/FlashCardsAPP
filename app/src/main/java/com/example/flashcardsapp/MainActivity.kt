package com.example.flashcardsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.flashcardsapp.ui.theme.FlashCardsAPPTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat.enableEdgeToEdge
import com.example.flashcardsapp.MainActivity.Screen
class MainActivity : ComponentActivity() {
    enum class Screen {
    Welcome, Flashcard, Score, Review
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var currentScreen by remember { mutableStateOf(value = Screen.Welcome)}
            var score by remember { mutableStateOf(value =0) }
            var questions by remember { mutableStateOf(value= 0) }

            val questions = listOf(
                "test" to true
            )

            var answered by remember { mutableStateOf(value = false)}
            var feedback by remember { mutableStateOf(value = "")}
            

                    .padding(all =16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    when (currentScreen) {
                        Screen.WELCOME -> {
                            Text(
                                text="Hello there! Welcome to the Flashcards App!",
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Spacer(modifier = Modifier.height(height =8.dp))
                            Text(text ="Test your knowledge with true or false questions.")
                            Spacer(modifier = Modifier.height(height =16.dp))
                            Button(onClick = {
                                score = 0
                                questionIndex = 0
                                currentScreen = Screen.FLASHCARD
                            }) {
                                Text(text="Start The Quiz")
                            }
                        }
                        Screen.FLASHCARD -> TODO()
                        Screen.SCORE -> TODO()
                        Screen.REVIEW -> TODO()
                    }
                }
                com.example.flashcardsapp.MainActivity.Screen.FLASHCARD -> {
                    val (questionText, correctAnswer) = questions[questionIndex]
                    Text(text ="Question ${questionIndex + 1} of ${questions.size}",
                    style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(height =8.dp))
                    Text(questionText, style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(height =16.dp))
            }
        }
    }
}