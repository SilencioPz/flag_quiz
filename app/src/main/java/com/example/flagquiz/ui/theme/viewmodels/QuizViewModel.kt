package com.example.flagquiz.ui.theme.viewmodels

import com.example.flagquiz.data.Country
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flagquiz.data.CountryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale

open class QuizViewModel(private val repository: CountryRepository) : ViewModel() {

    private val _currentLanguage = MutableStateFlow(Locale.getDefault().language)
    val currentLanguage: StateFlow<String> = _currentLanguage.asStateFlow()

    private val _uiState = MutableStateFlow(QuizState())
    val uiState = _uiState.asStateFlow()

    init {
        startNewGame()
    }

    fun startNewGame() {
        viewModelScope.launch {
            try {
                val randomCountries = repository.getRandomCountries(12, _currentLanguage.value)

                // Verificação de segurança
                if (randomCountries.isNotEmpty()) {
                    val firstQuestionOptions = generateOptionsForQuestion(randomCountries[0])

                    _uiState.value = QuizState(
                        countries = randomCountries,
                        currentQuestionIndex = 0,
                        playerName = _uiState.value.playerName, // Preserva o nome do jogador
                        currentOptions = firstQuestionOptions,
                        isGameOver = false,
                        score = 0,
                        correctAnswers = emptyList()
                    )
                }
            } catch (e: Exception) {
                _uiState.value = QuizState(
                    countries = emptyList(),
                    currentQuestionIndex = 0,
                    playerName = _uiState.value.playerName,
                    currentOptions = emptyList(),
                    isGameOver = false
                )
            }
        }
    }

    fun setPlayerName(name: String) {
        _uiState.value = _uiState.value.copy(playerName = name)
    }

    fun submitAnswer(selectedAnswer: String) {
        val currentState = _uiState.value

        if (currentState.countries.isEmpty() || currentState.currentQuestionIndex >= currentState.countries.size) {
            return
        }

        val currentCountry = currentState.countries[currentState.currentQuestionIndex]
        val isCorrect = selectedAnswer == currentCountry.name

        val updatedCorrectAnswers = if (isCorrect) {
            currentState.correctAnswers + currentCountry.name
        } else {
            currentState.correctAnswers
        }

        val newScore = if (isCorrect) currentState.score + 1 else currentState.score
        val isPerfectScore = newScore == 10

        _uiState.value = currentState.copy(
            selectedAnswer = selectedAnswer,
            isAnswerCorrect = isCorrect,
            score = newScore,
            correctAnswers = updatedCorrectAnswers,
            isPerfectScore = isPerfectScore
        )
    }

    fun moveToNextQuestion() {
        val currentState = _uiState.value
        val nextIndex = currentState.currentQuestionIndex + 1

        if (nextIndex < currentState.countries.size && nextIndex < 10) {
            val nextOptions = generateOptionsForQuestion(currentState.countries[nextIndex])

            _uiState.value = currentState.copy(
                currentQuestionIndex = nextIndex,
                selectedAnswer = null,
                isAnswerCorrect = null,
                currentOptions = nextOptions
            )
        } else {
            endGame()
        }
    }

    fun setLanguage(languageCode: String) {
        _currentLanguage.value = languageCode
        startNewGame()
    }

    private fun generateOptionsForQuestion(correctCountry: Country): List<Country> {
        return try {
            val allCountries = repository.getCountries(_currentLanguage.value)
            val options = mutableListOf(correctCountry) // Resposta correta SEMPRE incluída

            // Adiciona 4 países aleatórios (excluindo o correto)
            allCountries
                .filter { it.name != correctCountry.name }
                .shuffled()
                .take(3)
                .forEach { options.add(it) }

            options.shuffled() // Embaralha as opções
        } catch (e: Exception) {
            listOf(correctCountry)
        }
    }

    fun endGame() {
        _uiState.value = _uiState.value.copy(
            isGameOver = true
        )
    }

    fun restartGame() {
        val playerName = _uiState.value.playerName
        _uiState.value = QuizState(
            playerName = playerName,
            isGameOver = false
        )
        startNewGame()
    }

    data class QuizState(
        val countries: List<Country> = emptyList(),
        val questionsWithOptions: Map<Country, List<Country>> = emptyMap(),
        val currentQuestionIndex: Int = 0,
        val playerName: String = "",
        val selectedAnswer: String? = null,
        val isAnswerCorrect: Boolean? = null,
        val score: Int = 0,
        val currentOptions: List<Country> = emptyList(),
        val correctAnswers: List<String> = emptyList(),
        val isPerfectScore: Boolean = false,
        val isGameOver: Boolean = false
    ) {
        val currentCountry: Country?
            get() = if (countries.isNotEmpty() && currentQuestionIndex < countries.size) {
                countries[currentQuestionIndex]
            } else null

        val totalQuestions: Int get() = 10
    }
}