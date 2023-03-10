package com.example.wordlewannabe.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.wordlewannabe.data.WordsData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow



class WordleViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(WordleUIState())
    val uiState : StateFlow<WordleUIState> = _uiState.asStateFlow()

    private lateinit var currentWord : String
    val usedWords : MutableSet<String> = mutableSetOf()

    val TAG = "ViewModel"

    init {
        resetGame()
    }

    fun resetGame() {
        chooseWord()
        _uiState.value = WordleUIState()
    }

    private fun chooseWord() {
        do {
            currentWord = WordsData.words.random()
        } while (usedWords.contains(currentWord))
        usedWords.add(currentWord)
        Log.d(TAG, "currentWord = $currentWord")
    }
}