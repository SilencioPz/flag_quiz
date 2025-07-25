package com.example.flagquiz.ui.theme.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LanguageButton(
    languageCode: String,
    label: String,
    currentLanguage: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (languageCode == currentLanguage)
                Color(0xFF1E88E5) // Azul mais forte quando selecionado
            else
                Color(0xFF424242), // Cinza escuro quando não selecionado
            contentColor = Color.White
        ),
        modifier = Modifier
            .width(64.dp) // Largura fixa para manter consistência
            .padding(4.dp),
        shape = MaterialTheme.shapes.extraLarge // Deixa mais arredondado
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            fontSize = 16.sp // Tamanho maior para ficar bem visível
        )
    }
}