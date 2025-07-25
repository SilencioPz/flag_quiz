//package com.example.flagquiz
//
//import androidx.compose.ui.test.junit4.createAndroidComposeRule
//import androidx.compose.ui.test.onNodeWithTag
//import androidx.compose.ui.test.onNodeWithText
//import androidx.compose.ui.test.performClick
//import androidx.compose.ui.test.performTextInput
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
//@RunWith(AndroidJUnit4::class)
//class FlagQuizComposeTest {
//
//    @get:Rule
//    val composeTestRule = createAndroidComposeRule<MainActivity>()
//
//    @Test
//    fun testWelcomeScreen() {
//        // Verifica texto inicial
//        composeTestRule.onNodeWithText("Quiz das Bandeiras").assertExists()
//
//        // Insere nome e inicia
//        composeTestRule.onNodeWithTag("nameField").performTextInput("SilencioPz")
//        composeTestRule.onNodeWithText("Iniciar").performClick()
//
//        // Verifica se exibe a primeira pergunta
//        composeTestRule.onNodeWithText("Qual é esta bandeira?").assertExists()
//    }
//
//    @Test
//    fun testAnswerFlow() {
//        // Prepara o teste
//        composeTestRule.onNodeWithTag("nameField").performTextInput("Teste")
//        composeTestRule.onNodeWithText("Iniciar").performClick()
//
//        // Responde a primeira opção
//        composeTestRule.onNodeWithText("Opção 1").performClick()
//
//        // Verifica feedback
//        composeTestRule.onNodeWithText("Próxima").assertExists()
//    }
//}