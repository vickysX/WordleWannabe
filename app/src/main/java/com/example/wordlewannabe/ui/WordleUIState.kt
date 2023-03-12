package com.example.wordlewannabe.ui

data class WordleUIState(
    val first : String = "",
    val second : String = "",
    val third : String = "",
    val fourth : String = "",
    val fifth : String = "",
    val currentWord : String = "",
    val guessCheck : MutableList<MutableList<Pair<String, LetterStatus>?>> =
        mutableListOf(
            MutableList(5) {null},
            MutableList(5) {null},
            MutableList(5) {null},
            MutableList(5) {null},
            MutableList(5) {null},
            MutableList(5) {null},
        ),
    val isWordFinished : MutableList<Boolean> = MutableList(6) {false},
    val isRowEnabled : MutableList<Boolean> = mutableListOf(
        true, false, false,
        false, false, false
    ),
    val isWordGuessed : Boolean = false,
    val isGameOver : Boolean = false
)
