//package com.example.flagquiz
//
//import androidx.compose.ui.test.junit4.createAndroidComposeRule
//import androidx.compose.ui.test.onNodeWithText
//import androidx.compose.ui.test.performClick
//import junit.framework.TestCase.assertTrue
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import androidx.test.ext.junit.runners.AndroidJUnit4
//
//@RunWith(AndroidJUnit4::class)
//class ExitAppTest {
//    @get:Rule
//    val activityRule = createAndroidComposeRule<MainActivity>()
//
//    @Test
//    fun testExitButton() {
//        activityRule.onNodeWithText("Sair").performClick()
//        // Verifica se a Activity foi finalizada
//        assertTrue(activityRule.activity.isFinishing)
//    }
//}