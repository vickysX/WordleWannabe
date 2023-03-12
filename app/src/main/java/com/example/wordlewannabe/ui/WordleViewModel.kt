package com.example.wordlewannabe.ui

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.wordlewannabe.data.WordsData
import com.example.wordlewannabe.ui.theme.WordleYellow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class LetterStatus(val color : Color) {
    IS_LETTER_IN_WORD_RIGHT_POSITION(Color.Green),
    IS_LETTER_IN_WORD_WRONG_POSITION(WordleYellow),
    LETTER_NOT_IN_WORD(Color.Gray)
}


class WordleViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(WordleUIState())
    val uiState : StateFlow<WordleUIState> = _uiState.asStateFlow()

    private lateinit var currentWord : String
    private val usedWords : MutableSet<String> = mutableSetOf()

    private val userGuess :MutableList<String> = MutableList(5) {""}
    private val guessComparison : MutableList<Pair<String, LetterStatus>?> = mutableListOf(
        null, null, null, null, null
    )


    private val TAG = "ViewModel"

    init {
        resetGame()
    }

    fun resetGame() {
        _uiState.value = WordleUIState()
        chooseWord()
    }

    fun updateUserGuess(input : Pair<String, Int>) {
        when (input.second) {
            0 -> _uiState.update {
                it.copy(
                    first = input.first
                )
            }
            1 -> _uiState.update {
                it.copy(
                    second = input.first
                )
            }
            2 -> _uiState.update {
                it.copy(
                    third = input.first
                )
            }
            3 -> _uiState.update {
                it.copy(
                    fourth = input.first
                )
            }
            else -> _uiState.update {
                it.copy(
                    fifth = input.first
                )
            }
        }
        userGuess[input.second] = input.first
        Log.d(TAG, "userGuess = $userGuess")
    }

    fun checkUserGuess(wordIndex : Int) {
        for (i in 0 until userGuess.size) {
            if (userGuess[i] == currentWord[i].toString()) {
                guessComparison[i] = Pair(userGuess[i], LetterStatus.IS_LETTER_IN_WORD_RIGHT_POSITION)
            } else {
                if (currentWord.contains(userGuess[i])) {
                    guessComparison[i] = Pair(userGuess[i], LetterStatus.IS_LETTER_IN_WORD_WRONG_POSITION)
                } else {
                    guessComparison[i] = Pair(userGuess[i], LetterStatus.LETTER_NOT_IN_WORD)
                }
            }
        }
        updateUIState(wordIndex)
    }

    private fun updateUIState(
        wordIndex: Int
    ) {
        val wordFinished = _uiState.value.isWordFinished
        wordFinished[wordIndex] = true
        val guessCheck = _uiState.value.guessCheck
        guessCheck[wordIndex] = guessComparison
        val nextWordEnabled = _uiState.value.isRowEnabled
        var isGameOver = false
        if (wordIndex < 5) {
            nextWordEnabled[wordIndex + 1] = true
        } else {
            isGameOver = true
        }
        _uiState.update {
            it.copy(
                isWordFinished = wordFinished,
                isRowEnabled = nextWordEnabled,
                guessCheck = _uiState.value.guessCheck,
                isWordGuessed = isWordGuessed(guessComparison),
                isGameOver = isGameOver,
                first = "",
                second = "",
                third = "",
                fifth = "",
                fourth = ""
            )
        }
    }

    private fun isWordGuessed(
        guessComparison : MutableList<Pair<String, LetterStatus>?>
    ) : Boolean {
        val lettersInRightPlace = guessComparison.filter {
            it!!.second == LetterStatus.IS_LETTER_IN_WORD_RIGHT_POSITION
        }
        return guessComparison == lettersInRightPlace
    }

    private fun chooseWord() {
        do {
            currentWord = WordsData.words.random()
        } while (usedWords.contains(currentWord))
        _uiState.update {currentState ->
            currentState.copy(
                currentWord = currentWord
            )
        }
        usedWords.add(currentWord)
        Log.d(TAG, "currentWord = $currentWord")
    }
}