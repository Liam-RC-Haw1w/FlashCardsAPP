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

    enum class Screen { // The enum class is used to display the different section screens
        WELCOME, FLASHCARD, SCORE, REVIEW
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            var currentScreen by remember { mutableStateOf(value = Screen.WELCOME) }
            var score by remember { mutableStateOf(value = 0) } // Remembers or tracks user score
            var questionIndex by remember { mutableStateOf(value = 0) } // Used to display the current question

            val questions = listOf( // The questions that will display
                "Goldfish have a five second memory" to false,
                "Bats are the only mammals that fly" to true,
                "All snakes are poisonous" to false,
                "Octopuses have three hearts" to true,
                "Camels store water in their humps" to false
            )

            var answered by remember { mutableStateOf(value = false) } //
            var feedback by remember { mutableStateOf(value = "") } // To pop up feedback after answering questions

            Box( // Help put ui in middle of screen
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all =16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    when (currentScreen) { // Used to display the things needed for the user
                                            // depending where they are in the quiz
                    // Welcome screen displays the welcome message and start quiz button
                        com.example.flashcardsapp.MainActivity.Screen.WELCOME -> {
                            Text(text ="Welcome to the Flashcard Quiz App!",
                                style = MaterialTheme.typography.headlineSmall)
                            Spacer(modifier = Modifier.height(height =8.dp))
                            Text(text ="Test your knowledge with true or false questions.")
                            Spacer(modifier = Modifier.height(height =16.dp))
                            Button(onClick = {
                                score = 0
                                questionIndex = 0
                                currentScreen = com.example.flashcardsapp.MainActivity.Screen.FLASHCARD
                            }) {
                                Text(text ="Start the quiz") // Start quiz button
                            }
                        }
                            // Displays the questions and displays message correct or wrong depending
                            // what they answer
                        com.example.flashcardsapp.MainActivity.Screen.FLASHCARD -> {
                            val (questionText, correctAnswer) = questions[questionIndex]
                            Text(text ="Question ${questionIndex + 1} of ${questions.size}",
                                style = MaterialTheme.typography.titleMedium)
                            Spacer(modifier = Modifier.height(height =8.dp))
                            Text(questionText, style = MaterialTheme.typography.bodyLarge)
                            Spacer(modifier = Modifier.height(height =16.dp))

                            Row(horizontalArrangement = Arrangement.spacedBy(space =8.dp)) {
                                Button(onClick = { // Will detect right or wrong answers
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
                                    Text(text ="True") // True button created
                                }

                                Button(onClick = {
                                    if (!answered) {
                                        if (!correctAnswer) {
                                            score++
                                            feedback = "Correct!"
                                        } else {
                                            feedback = "Wrong!"
                                        }
                                        answered = true
                                    }
                                }) {
                                    Text(text ="False") // False button created
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
                                    }
                                }) {
                                    Text(text ="Next") // Next button created
                                }
                            }
                        }

                        com.example.flashcardsapp.MainActivity.Screen.SCORE -> {
                            // Shows the score they recieved after completing the quiz
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
                                Text(text ="Restart the quiz") // Button the restart the quizz
                            }
                        }

                        com.example.flashcardsapp.MainActivity.Screen.REVIEW -> {
                            // Review right and wrong answers screen
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
                                Text(text ="Back to the Start") // Used to take back to the welcome
                                // screen to restart the quiz
                            }
                        }
                    }
                }
            }
        }
    }
}
