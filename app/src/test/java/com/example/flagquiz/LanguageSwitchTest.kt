//package com.example.flagquiz
//
//import androidx.compose.ui.test.junit4.createAndroidComposeRule
//import androidx.compose.ui.test.onNodeWithText
//import androidx.compose.ui.test.performClick
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import androidx.test.ext.junit.runners.AndroidJUnit4
//
//@RunWith(AndroidJUnit4::class)
//class LanguageSwitchTest {
//    @get:Rule
//    val composeTestRule = createAndroidComposeRule<MainActivity>()
//
//    @Test
//    fun testLanguageSwitchToEnglish() {
//        composeTestRule.onNodeWithText("Idioma").performClick()
//        composeTestRule.onNodeWithText("English").performClick()
//        composeTestRule.onNodeWithText("Flag Quiz").assertExists()
//    }
//}