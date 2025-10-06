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
        WELCOME, FLASHCARD, SCORE, REVIEW
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var currentScreen by remember { mutableStateOf(value = Screen.WELCOME) }
            var score by remember { mutableStateOf(value = 0) }
            var questionIndex by remember { mutableStateOf(value = 0) }

            val questions = listOf(
                "Question" to false,
                "Question" to true,
                "Question" to false,
                "Question" to true
            )

            var answered by remember { mutableStateOf(value = false) }
            var feedback by remember { mutableStateOf(value = "") }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = 16.dp),
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
            Row(horizontalArrangement = Arrangement.spacedBy(space =8.dp)) {
                Button(onClick = {
                    if (!answered) {
                        if (correctAnswer) {
                            score++
                            feedback = "Correct!"
                        } else {
                            feedback = "Wrong!"
                        }
                        answered = true
                    }
                }) {
                    Text(text ="True")
                }

                Button(onClick = {
                    if (!answered) {
                        if (!correctAnswer) {
                            score++
                            feedback = "Correct!"
                        } else {
                            feedback = "Wrong!"
                        com.example.flashcardsapp.MainActivity.Screen.SCORE -> {
                            Text(text ="The quiz is now completed",
                                style = MaterialTheme.typography.headlineSmall)
                            Spacer(modifier = Modifier.height(height =8.dp))
                            Text(text ="Your Score: $score out of ${questions.size}",
                                style = MaterialTheme.typography.bodyLarge)
                            Spacer(modifier = Modifier.height(height =8.dp))
                            val finalFeedback = when {
                                score == questions.size -> "Well done"
                                score >= questions.size / 2 -> "Good try"
                                else -> "Keep practising"
                            }
                            Text(text =finalFeedback)

                            Spacer(modifier = Modifier.height(height =16.dp))
                            Button(onClick = {
                                currentScreen = com.example.flashcardsapp.MainActivity.Screen.REVIEW
                            }) {
                                Text(text ="Review your answers")
                            }

                            Spacer(modifier = Modifier.height(height =8.dp))
                            Button(onClick = {
                                currentScreen = com.example.flashcardsapp.MainActivity.Screen.WELCOME
                            }) {
                                Text(text ="Restart the quiz")
                            }
                        }
                        answered = true
                    }
                }) {
                    Text(text ="False")
                }
            }

            Spacer(modifier = Modifier.height(height =8.dp))
            Text(text = feedback)

            if (answered) {
                Spacer(modifier = Modifier.height(height =8.dp))
                Button(onClick = {
                    answered = false
                    feedback = ""
                    if (questionIndex < questions.lastIndex) {
                        questionIndex++
                    } else {
                        currentScreen = com.example.flashcardsapp.MainActivity.Screen.SCORE
                        com.example.flashcardsapp.MainActivity.Screen.REVIEW -> {
                            Text(text ="Review your answers",
                                style = MaterialTheme.typography.titleLarge)
                            Spacer(modifier = Modifier.height(height =8.dp))
                            questions.forEachIndexed { index, pair ->
                                Text(text ="${index + 1}. ${pair.first} - Answer: ${if (pair.second)
                                    "True" else "False"}")
                                Spacer(modifier = Modifier.height(height =4.dp))
                            }

                            Spacer(modifier = Modifier.height(height =16.dp))
                            Button(onClick = {
                                currentScreen = com.example.flashcardsapp.MainActivity.Screen.WELCOME
                            }) {
                                Text(text ="Back to the Start")
                            }
                        }
                    }
                }
            }
        }
    }
}
