package com.example.wordlewannabe.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    private lateinit var currentWord : MutableList<String>
    private val usedWords : MutableSet<String> = mutableSetOf()
    var userGuess by mutableStateOf(mutableListOf("", "", "", "", ""))
        private set

    val TAG = "ViewModel"

    init {
        resetGame()
    }

    fun resetGame() {
        _uiState.value = WordleUIState()
        chooseWord()
    }

    fun updateUserGuess(input : Pair<String, Int>) {
        userGuess[input.second] = input.first
    }

    fun checkUserGuess(wordIndex : Int) {
        val guessComparison : MutableList<Pair<String, LetterStatus>?> = mutableListOf(
            null, null, null, null, null
        )
        var i : Int
        for (letter in userGuess) {
            i = userGuess.indexOf(letter)
            if (currentWord.contains(letter) && currentWord[i] != letter) {
                guessComparison[i] = Pair(letter, LetterStatus.IS_LETTER_IN_WORD_WRONG_POSITION)
            } else if (currentWord.contains(letter)) {
                guessComparison[i] = Pair(letter, LetterStatus.IS_LETTER_IN_WORD_RIGHT_POSITION)
            } else {
                guessComparison[i] = Pair(letter, LetterStatus.LETTER_NOT_IN_WORD)
            }
        }
        updateUIState(guessComparison, wordIndex)
    }

    private fun updateUIState(
        guessComparison : MutableList<Pair<String, LetterStatus>?>,
        wordIndex: Int
    ) {
        val wordFinished = _uiState.value.isWordFinished
        wordFinished[wordIndex] = true
        val nextWordEnabled = _uiState.value.isRowEnabled
        var isGameOver = false
        if (wordIndex < 5) {
            nextWordEnabled[wordIndex] = true
        } else {
            isGameOver = true
        }
        _uiState.update {
            it.copy(
                isWordFinished = wordFinished,
                isRowEnabled = nextWordEnabled,
                guessCheck = guessComparison,
                isWordGuessed = isWordGuessed(guessComparison),
                isGameOver = isGameOver
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
        var word: String
        do {
            word = WordsData.words.random()
        } while (usedWords.contains(word))
        currentWord = word.split("") as MutableList<String>
        _uiState.update {currentState ->
            currentState.copy(
                currentWord = currentWord
            )
        }
        usedWords.add(word)
        Log.d(TAG, "currentWord = $word")
    }
}