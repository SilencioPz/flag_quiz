package com.example.flagquiz.ui.theme.screens

import android.media.MediaPlayer
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.flagquiz.R
import com.example.flagquiz.ui.theme.components.LanguageButton
import com.example.flagquiz.ui.theme.viewmodels.QuizViewModel
import com.example.flagquiz.utils.setAppLanguage
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag

@Composable
fun QuizScreen(
    viewModel: QuizViewModel,
    onGameOver: () -> Unit,
    onAboutClick: () -> Unit,
    onCloseApp: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val currentCountry = uiState.currentCountry
    val context = LocalContext.current
    val currentLanguage by viewModel.currentLanguage.collectAsState()
    var clickSoundPlayer: MediaPlayer? by remember { mutableStateOf(null) }

    fun playClickSound() {
        try {
            clickSoundPlayer?.release() // Libera o player anterior se existir
            clickSoundPlayer = MediaPlayer.create(context, R.raw.clicked_button)
            clickSoundPlayer?.start()
        } catch (e: Exception) {
            // Log do erro se necessário
        }
    }

    // Limpa o MediaPlayer quando a tela é destruída
    DisposableEffect(Unit) {
        onDispose {
            clickSoundPlayer?.release()
            clickSoundPlayer = null
        }
    }

    if (currentCountry == null) {
        Text("Carregando...")
        return
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onCloseApp,
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
                    onClick = onAboutClick,
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
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                LanguageButton(
                    languageCode = "en",
                    label = "EN",
                    currentLanguage = currentLanguage
                ) {
                    context.setAppLanguage("en")
                    viewModel.setLanguage("en")
                }
                Spacer(modifier = Modifier.width(8.dp))

                LanguageButton(
                    languageCode = "pt",
                    label = "PT",
                    currentLanguage = currentLanguage
                ) {
                    context.setAppLanguage("pt")
                    viewModel.setLanguage("pt")
                }
                Spacer(modifier = Modifier.width(8.dp))

                LanguageButton(
                    languageCode = "es",
                    label = "ES",
                    currentLanguage = currentLanguage
                ) {
                    context.setAppLanguage("es")
                    viewModel.setLanguage("es")
                }
            }

            Text(
                stringResource(R.string.question_count, uiState.currentQuestionIndex + 1, 10),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(uiState.currentCountry!!.flagResId),
                    contentDescription = stringResource(
                        R.string.flag_description,
                        uiState.currentCountry!!.name
                    ),
                    modifier = Modifier
                        .size(120.dp),
                    contentScale = ContentScale.Fit
                )
            }

            // Adicione a pergunta aqui primeiro
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.flag_question),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                uiState.currentOptions.forEach { country ->
                    val isSelected = country.name == uiState.selectedAnswer
                    val buttonColors = ButtonDefaults.buttonColors(
                        containerColor = when {
                            isSelected && uiState.isAnswerCorrect == true -> Color.Green
                            isSelected && uiState.isAnswerCorrect == false -> Color.Red
                            else -> MaterialTheme.colorScheme.primary
                        }
                    )

                    Button(
                        onClick = {
                            if (uiState.selectedAnswer == null) {
                                playClickSound()
                                viewModel.submitAnswer(country.name)
                            }
                        },
                        colors = buttonColors,
                        enabled = uiState.selectedAnswer == null,
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(48.dp)
                            .testTag("answerOption_${country.name}")
                    ) {
                        Text(
                            text = country.name,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                if (uiState.selectedAnswer != null) {
                    Button(
                        onClick = {
                            playClickSound()
                            if (uiState.currentQuestionIndex + 1 >= 12) {
                                viewModel.endGame()
                                onGameOver()
                            } else {
                                // Próxima pergunta
                                viewModel.moveToNextQuestion()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(48.dp)
                            .padding(top = 8.dp)
                    ) {
                        Text(
                            text = if (uiState.currentQuestionIndex + 1 >= 12) {
                                stringResource(R.string.finish_button)
                            } else {
                                stringResource(R.string.next_button)
                            },
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
