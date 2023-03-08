package com.example.wordlewannabe.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WordleApp(
    modifier: Modifier = Modifier,
    viewModel: WordleViewModel = viewModel()
) {
    val wordleUIState = viewModel.uiState.collectAsState()
}