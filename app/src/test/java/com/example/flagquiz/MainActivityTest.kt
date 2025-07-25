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
//class MainActivityTest {
//    @get:Rule
//    val composeTestRule = createAndroidComposeRule<MainActivity>()
//
//    @Test
//    fun testWelcomeScreenElements() {
//        composeTestRule.onNodeWithText("Quiz das Bandeiras").assertExists()
//
//        composeTestRule.onNodeWithTag("nameField").assertExists()
//
//        composeTestRule.onNodeWithTag("startButton").assertExists()
//    }
//
//    @Test
//    fun testUserInteraction() {
//        // Digita um nome no campo
//        composeTestRule.onNodeWithTag("nameField")
//            .performTextInput("João")
//
//        // Clica no botão iniciar
//        composeTestRule.onNodeWithTag("startButton")
//            .performClick()
//
//        // Aqui você pode adicionar verificações do que deve acontecer
//        // após clicar no botão, como navegar para próxima tela
//    }
//
//    @Test
//    fun testEmptyNameHandling() {
//        // Testa o comportamento quando o nome está vazio
//        composeTestRule.onNodeWithTag("startButton")
//            .performClick()
//
//        // Adicione aqui verificações se deve mostrar algum erro
//        // ou se deve permitir continuar sem nome
//    }
//}