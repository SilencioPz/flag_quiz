//package com.example.flagquiz
//
//import androidx.compose.ui.test.junit4.createAndroidComposeRule
//import androidx.compose.ui.test.onNodeWithTag
//import androidx.compose.ui.test.onNodeWithText
//import androidx.compose.ui.test.performClick
//import androidx.compose.ui.test.performTextInput
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import androidx.test.ext.junit.runners.AndroidJUnit4
//
//@RunWith(AndroidJUnit4::class)
//class PlayerNameScreenTest {
//    @get:Rule
//    val composeTestRule = createAndroidComposeRule<MainActivity>()
//
//    @Test
//    fun testPlayerNameInput() {
//        // Digita o nome e verifica se foi salvo
//        composeTestRule.onNodeWithTag("name_input").performTextInput("Zé")
//        composeTestRule.onNodeWithText("Confirmar").performClick()
//        composeTestRule.onNodeWithText("Zé").assertExists()
//    }
//}