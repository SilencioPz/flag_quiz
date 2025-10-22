package com.example.flagquiz.ui.theme.screens

import android.media.MediaPlayer
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.flagquiz.utils.setAppLanguage
import com.example.flagquiz.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.example.flagquiz.data.CountryRepository
import com.example.flagquiz.ui.theme.components.LanguageButton
import com.example.flagquiz.ui.theme.viewmodels.QuizViewModel

@Composable
fun WelcomeScreen(
    onStartQuiz: (String) -> Unit,
    onAboutClick: () -> Unit,
    onCloseApp: () -> Unit
) {
    var playerName by remember { mutableStateOf("") }
    val context = LocalContext.current
    var mediaPlayer: MediaPlayer? by remember { mutableStateOf(null) }

    val viewModel: QuizViewModel = remember { QuizViewModel(CountryRepository) }
    val currentLanguage by viewModel.currentLanguage.collectAsState()

    LaunchedEffect(Unit) {
        mediaPlayer = MediaPlayer.create(context, R.raw.adieu_au_piano)?.apply {
            isLooping = true  // Para repetir a música
            setVolume(0.5f, 0.5f) // 50% do volume
            start()
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onCloseApp,
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_close_clear_cancel),
                        contentDescription = "Close app",
                        tint = Color.Red
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.select_language) + ": ",
                        color = Color.White,
                        modifier = Modifier.padding(end = 8.dp)
                    )

                    LanguageButton(
                        languageCode = "en",
                        label = "EN",
                        currentLanguage = currentLanguage
                    ) {
                        try {
                            context.setAppLanguage("en")
                            viewModel.setLanguage("en")
                        } catch (e: Exception) {
                            // Handle language change error
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))

                    LanguageButton(
                        languageCode = "pt",
                        label = "PT",
                        currentLanguage = currentLanguage
                    ) {
                        try {
                            context.setAppLanguage("pt")
                            viewModel.setLanguage("pt")
                        } catch (e: Exception) {
                            // Handle language change error
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))

                    LanguageButton(
                        languageCode = "es",
                        label = "ES",
                        currentLanguage = currentLanguage
                    ) {
                        try {
                            context.setAppLanguage("es")
                            viewModel.setLanguage("es")
                        } catch (e: Exception) {
                            // Handle language change error
                        }
                    }
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.silenciopz_logo2),
                contentDescription = "Silenciopz Logo",
                modifier = Modifier
                    .size(200.dp)
                    .padding(16.dp),
                contentScale = ContentScale.Fit
            )
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineLarge.copy(color = Color.White),
                modifier = Modifier.padding(16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = playerName,
                onValueChange = { newText: String -> playerName = newText },
                label = { Text(stringResource(R.string.name_hint)) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color.White,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(16.dp),
                textStyle = LocalTextStyle.current.copy(color = Color.White)
            )

            Button(
                onClick = {
                    if (playerName.isNotBlank()) {
                        onStartQuiz(playerName)
                    }
                },
                enabled = playerName.isNotBlank(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .padding(16.dp)
                    .testTag("startButton")
            ) {
                Text(stringResource(R.string.start_button))
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Botão About
            TextButton(
                onClick = onAboutClick,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "About",
                    color = Color.White
                )
            }

            Text(
                text = "Music: Adieu au Piano - Beethoven (recording: MusOpen, CC PD)",
                color = Color.Gray,
                style = MaterialTheme.typography.labelSmall,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
        }
    }
}