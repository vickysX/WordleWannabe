package com.example.wordlewannabe.ui

data class WordleUIState(
    val isLetterWrong : Boolean? = null,
    val isLetterNotInRightPosition : Boolean? = null,
    val isLetterRight : Boolean? = null
)
