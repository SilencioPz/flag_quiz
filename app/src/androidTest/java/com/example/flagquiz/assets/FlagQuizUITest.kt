//package com.example.flagquiz.assets
//
//import androidx.compose.runtime.*
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.compose.ui.test.onNodeWithTag
//import androidx.compose.ui.test.onNodeWithText
//import androidx.compose.ui.test.performClick
//import androidx.compose.ui.test.performTextInput
//import androidx.compose.ui.test.assertIsEnabled
//import androidx.compose.ui.test.assertIsNotEnabled
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.example.flagquiz.R
//import com.example.flagquiz.data.Country
//import com.example.flagquiz.data.CountryRepository
//import com.example.flagquiz.assets.TestCountryRepository
//import com.example.flagquiz.ui.theme.FlagQuizTheme
//import com.example.flagquiz.ui.theme.screens.QuizScreen
//import com.example.flagquiz.ui.theme.screens.WelcomeScreen
//import com.example.flagquiz.ui.theme.screens.ResultScreen
//import com.example.flagquiz.ui.theme.viewmodels.QuizViewModel
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
//@RunWith(AndroidJUnit4::class)
//class FlagQuizUITest {
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    private class TestCountryRepository {
//        private val testCountries = listOf(
//            Country(
//                nameEn = "Brazil",
//                namePt = "Brasil",
//                nameEs = "Brasil",
//                flagResId = R.drawable.brasil,
//                region = "South America"
//            ),
//            Country(
//                nameEn = "Argentina",
//                namePt = "Argentina",
//                nameEs = "Argentina",
//                flagResId = R.drawable.argentina,
//                region = "South America"
//            ),
//            Country(
//                nameEn = "Germany",
//                namePt = "Alemanha",
//                nameEs = "Alemania",
//                flagResId = R.drawable.germany,
//                region = "Europe"
//            ),
//            Country(
//                nameEn = "France",
//                namePt = "França",
//                nameEs = "Francia",
//                flagResId = R.drawable.france,
//                region = "Europe"
//            )
//        )
//
//        fun getRandomCountries(amount: Int, language: String): List<Country> {
//            return getCountries(language).take(amount)
//        }
//
//        fun getCountries(language: String): List<Country> {
//            return testCountries.map { country ->
//                country.copy(name = when (language.lowercase()) {
//                    "pt" -> country.namePt
//                    "es" -> country.nameEs
//                    else -> country.nameEn
//                })
//            }
//        }
//    }
//
////    private fun createTestViewModel(): QuizViewModel {
////        return QuizViewModel(testCountryRepository)
////    }
////
////    private class TestQuizViewModel(
////        private val repository: TestCountryRepository
////    ) : QuizViewModel(CountryRepository) {
////
////        init {
////            loadNextQuestion()
////        }
////
////        private fun loadNextQuestion() {
////            val countries = repository.getRandomCountries(4, getCurrentLanguage())
////            if (countries.isNotEmpty()) {
////                setCurrentQuestion(
////                    country = countries.first(),
////                    options = countries
////                )
////            }
////        }
////
////        private fun getCurrentLanguage(): String {
////            return "en" // Padrão para testes
////        }
////
////        private fun setCurrentQuestion(country: Country, options: List<Country>) {
////            // Aqui você implementaria a lógica para definir a questão atual
////            // Como não temos acesso direto ao código do QuizViewModel,
////            // esta é uma versão simplificada
////        }
////    }
//
//    @Test
//    fun welcomeScreen_shouldDisplayAllElements() {
//        // Testa apenas a WelcomeScreen isoladamente
//        composeTestRule.setContent {
//            FlagQuizTheme {
//                WelcomeScreen(
//                    onStartQuiz = { },
//                    onAboutClick = { },
//                    onCloseApp = { }
//                )
//            }
//        }
//
//        // Verifica se todos os elementos estão presentes
//        composeTestRule.onNodeWithText("Welcome to FlagQuiz").assertExists()
//        composeTestRule.onNodeWithText("Enter your name").assertExists()
//        composeTestRule.onNodeWithTag("startButton").assertExists()
//    }
//
//    @Test
//    fun welcomeScreen_shouldStartQuizWhenNameEntered() {
//        var quizStarted = false
//        var capturedName = ""
//
//        composeTestRule.setContent {
//            FlagQuizTheme {
//                WelcomeScreen(
//                    onStartQuiz = { name ->
//                        capturedName = name
//                        quizStarted = true
//                    },
//                    onAboutClick = { },
//                    onCloseApp = { }
//                )
//            }
//        }
//
//        // Insere o nome
//        composeTestRule.onNodeWithText("Enter your name")
//            .performTextInput("Test Player")
//
//        // Clica no botão start
//        composeTestRule.onNodeWithTag("startButton")
//            .performClick()
//
//        // Verifica se o callback foi chamado corretamente
//        assert(quizStarted) { "Quiz deveria ter iniciado" }
//        assert(capturedName == "Test Player") { "Nome deveria ter sido capturado" }
//    }
//
//    @Test
//    fun quizScreen_shouldDisplayFlagAndOptions() {
//        val viewModel = createTestViewModel()
//        viewModel.setPlayerName("Test Player")
//
//        composeTestRule.setContent {
//            FlagQuizTheme {
//                QuizScreen(
//                    viewModel = viewModel,
//                    onGameOver = { },
//                    onAboutClick = { },
//                    onCloseApp = { }
//                )
//            }
//        }
//
//        // Verifica se a pergunta aparece
//        composeTestRule.onNodeWithText("What country does this flag belong to?")
//            .assertExists()
//
//        // Verifica se pelo menos uma opção de resposta existe
//        // (Como o repository retorna countries com nomes em inglês por default)
//        composeTestRule.onNodeWithText("Brazil").assertExists()
//    }
//
//    @Test
//    fun quizScreen_shouldDisableOptionsAfterSelection() {
//        val viewModel = createTestViewModel()
//        viewModel.setPlayerName("Test Player")
//
//        composeTestRule.setContent {
//            FlagQuizTheme {
//                QuizScreen(
//                    viewModel = viewModel,
//                    onGameOver = { },
//                    onAboutClick = { },
//                    onCloseApp = { }
//                )
//            }
//        }
//
//        // Clica na primeira opção disponível
//        composeTestRule.onNodeWithTag("answerOption_Brazil")
//            .performClick()
//
//        // Verifica se os botões ficaram desabilitados
//        composeTestRule.onNodeWithTag("answerOption_Brazil")
//            .assertIsNotEnabled()
//    }
//
//    @Test
//    fun quizScreen_shouldShowNextButtonAfterAnswer() {
//        val viewModel = createTestViewModel()
//        viewModel.setPlayerName("Test Player")
//
//        composeTestRule.setContent {
//            FlagQuizTheme {
//                QuizScreen(
//                    viewModel = viewModel,
//                    onGameOver = { },
//                    onAboutClick = { },
//                    onCloseApp = { }
//                )
//            }
//        }
//
//        // Responde uma questão
//        composeTestRule.onNodeWithTag("answerOption_Brazil")
//            .performClick()
//
//        // Verifica se o botão "Next" aparece
//        composeTestRule.onNodeWithText("Next")
//            .assertExists()
//            .assertIsEnabled()
//    }
//
//    @Test
//    fun resultScreen_shouldDisplayScore() {
//        var restartCalled = false
//
//        composeTestRule.setContent {
//            FlagQuizTheme {
//                ResultScreen(
//                    score = 8,
//                    playerName = "Test Player",
//                    onRestart = { restartCalled = true },
//                    onCloseApp = { }
//                )
//            }
//        }
//
//        // Verifica se o score é exibido
//        composeTestRule.onNodeWithText("Test Player").assertExists()
//        composeTestRule.onNodeWithText("8").assertExists()
//
//        // Verifica se o botão de reiniciar funciona
//        composeTestRule.onNodeWithText("Play Again")
//            .performClick()
//
//        assert(restartCalled) { "Restart deveria ter sido chamado" }
//    }
//
//    @Test
//    fun quizScreen_shouldChangeLanguage() {
//        val viewModel = createTestViewModel()
//        viewModel.setPlayerName("Test Player")
//
//        composeTestRule.setContent {
//            FlagQuizTheme {
//                QuizScreen(
//                    viewModel = viewModel,
//                    onGameOver = { },
//                    onAboutClick = { },
//                    onCloseApp = { }
//                )
//            }
//        }
//
//        // Clica no botão PT para mudar para português
//        composeTestRule.onNodeWithText("PT")
//            .performClick()
//
//        // Aguarda um pouco para a mudança de estado
//        composeTestRule.waitForIdle()
//
//        // Verifica se aparece o nome em português
//        composeTestRule.onNodeWithText("Brasil").assertExists()
//    }
//
//    @Test
//    fun aboutDialog_shouldShowWhenAboutClicked() {
//        var aboutClicked = false
//
//        composeTestRule.setContent {
//            FlagQuizTheme {
//                WelcomeScreen(
//                    onStartQuiz = { },
//                    onAboutClick = { aboutClicked = true },
//                    onCloseApp = { }
//                )
//            }
//        }
//
//        composeTestRule.onNodeWithText("About")
//            .performClick()
//
//        assert(aboutClicked) { "About deveria ter sido clicado" }
//    }
//
//    @Test
//    fun completeGameFlow_integration() {
//        // Teste de integração que simula um fluxo completo
//        val viewModel = createTestViewModel()
//        var currentScreen by mutableStateOf("welcome")
//
//        composeTestRule.setContent {
//            FlagQuizTheme {
//                when (currentScreen) {
//                    "welcome" -> WelcomeScreen(
//                        onStartQuiz = { name ->
//                            viewModel.setPlayerName(name)
//                            currentScreen = "quiz"
//                        },
//                        onAboutClick = { },
//                        onCloseApp = { }
//                    )
//                    "quiz" -> QuizScreen(
//                        viewModel = viewModel,
//                        onGameOver = {
//                            currentScreen = "result"
//                        },
//                        onAboutClick = { },
//                        onCloseApp = { }
//                    )
//                    "result" -> ResultScreen(
//                        score = 5,
//                        playerName = "Integration Test",
//                        onRestart = { currentScreen = "welcome" },
//                        onCloseApp = { }
//                    )
//                }
//            }
//        }
//
//        // 1. Testa tela de boas-vindas
//        composeTestRule.onNodeWithText("Welcome to FlagQuiz").assertExists()
//
//        // 2. Insere nome e inicia quiz
//        composeTestRule.onNodeWithText("Enter your name")
//            .performTextInput("Integration Test")
//        composeTestRule.onNodeWithTag("startButton")
//            .performClick()
//
//        // 3. Verifica se está na tela do quiz
//        composeTestRule.waitForIdle()
//        composeTestRule.onNodeWithText("What country does this flag belong to?")
//            .assertExists()
//
//        // 4. Responde algumas questões rapidamente
//        repeat(3) {
//            // Clica em uma resposta
//            composeTestRule.onNodeWithTag("answerOption_Brazil")
//                .performClick()
//
//            composeTestRule.waitForIdle()
//
//            // Clica em "Next" se disponível
//            try {
//                composeTestRule.onNodeWithText("Next")
//                    .performClick()
//                composeTestRule.waitForIdle()
//            } catch (e: Exception) {
//                // Se não houver botão Next, pode ser a última questão
//            }
//        }
//    }
//
//    // ===============================================
//    // HELPER: Componente de teste que virtualiza o app completo
//    // ===============================================
//    @Composable
//    private fun TestAppContainer(
//        initialScreen: String = "welcome",
//        onScreenChange: (String) -> Unit = {}
//    ) {
//        val viewModel = remember { createTestViewModel() }
//        var currentScreen by remember { mutableStateOf(initialScreen) }
//
//        LaunchedEffect(currentScreen) {
//            onScreenChange(currentScreen)
//        }
//
//        when (currentScreen) {
//            "welcome" -> WelcomeScreen(
//                onStartQuiz = { name ->
//                    viewModel.setPlayerName(name)
//                    currentScreen = "quiz"
//                },
//                onAboutClick = { },
//                onCloseApp = { }
//            )
//            "quiz" -> QuizScreen(
//                viewModel = viewModel,
//                onGameOver = { currentScreen = "result" },
//                onAboutClick = { },
//                onCloseApp = { }
//            )
//            "result" -> {
//                val uiState by viewModel.uiState.collectAsState()
//                ResultScreen(
//                    score = uiState.score,
//                    playerName = uiState.playerName,
//                    onRestart = {
//                        viewModel.startNewGame()
//                        currentScreen = "welcome"
//                    },
//                    onCloseApp = { }
//                )
//            }
//        }
//    }
//
//    @Test
//    fun virtualizedApp_completeFlow() {
//        var currentScreen = ""
//
//        composeTestRule.setContent {
//            FlagQuizTheme {
//                TestAppContainer { screen ->
//                    currentScreen = screen
//                }
//            }
//        }
//
//        // Verifica estado inicial
//        assert(currentScreen == "welcome")
//        composeTestRule.onNodeWithText("Welcome to FlagQuiz").assertExists()
//
//        // Simula entrada de nome e início do quiz
//        composeTestRule.onNodeWithText("Enter your name")
//            .performTextInput("Virtual Test")
//        composeTestRule.onNodeWithTag("startButton")
//            .performClick()
//
//        composeTestRule.waitForIdle()
//        assert(currentScreen == "quiz")
//
//        // Verifica se chegou na tela de quiz
//        composeTestRule.onNodeWithText("What country does this flag belong to?")
//            .assertExists()
//    }
//}