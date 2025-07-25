package com.example.flagquiz

import com.example.flagquiz.data.CountryRepository
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.flagquiz.ui.theme.FlagQuizTheme
import com.example.flagquiz.ui.theme.screens.QuizScreen
import com.example.flagquiz.ui.theme.screens.ResultScreen
import com.example.flagquiz.ui.theme.screens.WelcomeScreen
import com.example.flagquiz.ui.theme.viewmodels.QuizViewModel
import kotlinx.coroutines.delay

//Classe Principal do Projeto FlagQuiz
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlagQuizTheme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    val viewModel = remember { QuizViewModel(CountryRepository) }
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    var showAboutDialog by remember { mutableStateOf(false) }
    var isClosing by remember { mutableStateOf(false) }


    if (isClosing) {
        LaunchedEffect(Unit) {
            delay(1000)
            (context as Activity).finish()
        }
    }

    when {
        uiState.playerName.isBlank() -> {
            WelcomeScreen(
                onStartQuiz = { name -> viewModel.setPlayerName(name) },
                onAboutClick = { showAboutDialog = true },
                onCloseApp = { isClosing = true }
            )
        }

        uiState.isGameOver -> {
            ResultScreen(
                score = uiState.score,
                playerName = uiState.playerName,
                onRestart = { viewModel.startNewGame() },
                onCloseApp = { isClosing = true }
            )
        }

        else -> {
            QuizScreen(
                viewModel = viewModel,
                onGameOver = { viewModel.endGame() },
                onAboutClick = { showAboutDialog = true },
                onCloseApp = { isClosing = true }
            )
        }
    }
    if (showAboutDialog) {
        AboutDialog(
            onDismiss = { showAboutDialog = false },
            onVisitWebsite = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://silenciopz.neocities.org/projects")
                )
                context.startActivity(intent)
            }
        )
    }
}

@Composable
fun QuizScreenContent(viewModel: QuizViewModel) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()
    val currentCountry = uiState.currentCountry
    var showAboutDialog by remember { mutableStateOf(false) }
    var isClosing by remember { mutableStateOf(false) }

    if (isClosing) {
        LaunchedEffect(Unit) {
            delay(1000)
            (context as Activity).finish()
        }
    }

    if (currentCountry == null) {
        Text("Carregando...")
        return
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { isClosing = true },
                modifier = Modifier.size(56.dp),
                containerColor = Color.Red,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Close, contentDescription = "Close")
            }
        },
        bottomBar = {
            BottomAppBar {
                Button(
                    onClick = { showAboutDialog = true },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(Icons.Default.Info, contentDescription = null)
                    Spacer(Modifier.size(8.dp))
                    Text("About")
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = currentCountry.flagResId),
                contentDescription = currentCountry.getFlagDescription(context),
                modifier = Modifier
                    .size(200.dp)
                    .padding(8.dp)
                    .size(200.dp)
                    .padding(8.dp)
            )

            Spacer(Modifier.height(16.dp))

            // Exibe as opções de resposta
            uiState.currentOptions.forEach { country ->
                Button(
                    onClick = { viewModel.submitAnswer(country.name) },
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                ) {
                    Text(country.name)
                }
            }

            // Feedback de acerto/erro
            if (uiState.selectedAnswer != null) {
                Text(
                    text = if (uiState.isAnswerCorrect == true) "Right!" else "Wrong!",
                    color = if (uiState.isAnswerCorrect == true) Color.Green else Color.Red
                )

                Spacer(Modifier.height(8.dp))

                Button(
                    onClick = { viewModel.moveToNextQuestion() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Next Question")
                }
            }
        }

        if (showAboutDialog) {
            AboutDialog(
                onDismiss = { showAboutDialog = false },
                onVisitWebsite = {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://silenciopz.neocities.org/projects")
                    )
                    context.startActivity(intent)
                })
        }
    }
}

@Composable
fun AboutDialog(
    onDismiss: () -> Unit,
    onVisitWebsite: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.silenciopz_logo),
                    contentDescription = "SilencioPz Logo",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(bottom = 8.dp)
                )

                Text("About the Dev")
            }
        },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("App developed by [SilencioPz]")
                Spacer(Modifier.height(8.dp))
                Text("Junior Programmer using Android/Kotlin and Java")
                Spacer(Modifier.height(8.dp))
                Text("Visit: https://silenciopz.neocities.org/projects")
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onVisitWebsite) {
                Text("Visit Website")
            }
        }
    )
}