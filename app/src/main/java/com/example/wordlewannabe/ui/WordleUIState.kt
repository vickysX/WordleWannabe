package com.example.wordlewannabe.ui

data class WordleUIState(
    val currentWord : MutableList<String> = mutableListOf(),
    val guessCheck : MutableList<Pair<String, LetterStatus>?> = mutableListOf(
        null, null, null, null, null
    ),
    val isWordFinished : MutableList<Boolean> = mutableListOf(
        false, false, false,
        false, false, false
    ),
    val isRowEnabled : MutableList<Boolean> = mutableListOf(
        true, false, false,
        false, false, false
    ),
    val isGameOver : Boolean = false
)
