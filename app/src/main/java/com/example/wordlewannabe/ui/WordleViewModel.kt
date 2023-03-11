package com.example.wordlewannabe.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.wordlewannabe.data.WordsData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class LetterStatus {
    IS_LETTER_IN_WORD_RIGHT_POSITION,
    IS_LETTER_IN_WORD_WRONG_POSITION,
    LETTER_NOT_IN_WORD
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
        val guessComparison : MutableList<Pair<String, LetterStatus>?> = mutableListOf()
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
        val wordFinished = _uiState.value.isWordFinished
        wordFinished[wordIndex] = true
        _uiState.update {
            it.copy(
                isWordFinished = wordFinished,
                guessCheck = guessComparison,
            )
        }
    }

    private fun chooseWord() {
        var word = ""
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