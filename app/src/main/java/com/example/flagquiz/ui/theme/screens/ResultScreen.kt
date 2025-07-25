package com.example.flagquiz.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.flagquiz.R
import android.media.MediaPlayer
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext


@Composable
fun ResultScreen(
    score: Int,
    playerName: String,
    onRestart: () -> Unit,
    onCloseApp: () -> Unit
) {
    val context = LocalContext.current
    var endingSoundPlayer: MediaPlayer? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        try {
            endingSoundPlayer = MediaPlayer.create(context, R.raw.ending_sound)
            endingSoundPlayer?.start()
        } catch (e: Exception) {
            // Log do erro se necessário
        }
    }

    // Limpa o MediaPlayer quando a tela é destruída
    DisposableEffect(Unit) {
        onDispose {
            endingSoundPlayer?.release()
            endingSoundPlayer = null
        }
    }

    val messageStringRes = when (score) {
        in 0..4 -> R.string.result_message_poor
        in 5..9 -> R.string.result_message_good
        else -> R.string.result_message_excellent
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.result_score, score),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(messageStringRes, playerName),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Button(
            onClick = {
                endingSoundPlayer?.stop()
                onRestart()
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(stringResource(R.string.play_again))
        }
    }
}