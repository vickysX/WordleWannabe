package com.example.wordlewannabe.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wordlewannabe.R
import com.example.wordlewannabe.ui.theme.WordleWannabeTheme

enum class Screen {
    Rules, Game
}
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WordleApp(
    modifier: Modifier = Modifier,
    viewModel: WordleViewModel = viewModel()
) {
    val wordleUIState = viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            TopBar()
        },
        modifier = modifier
            .wrapContentSize(Alignment.TopCenter)
    ) {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Screen.Rules.name,
            modifier = Modifier.padding(it)
        ) {
            composable(route = Screen.Rules.name) {
                InfoScreen(
                    onNextClicked = {navController.navigate(Screen.Game.name)}
                )
            }
            composable(route = Screen.Game.name) {
                GameScreen(
                    onLetterGuessed = {guess ->
                        viewModel.updateUserGuess(guess)
                    },
                    onWordFinished = {wordNum ->
                        viewModel.checkUserGuess(wordNum)
                    },
                    word = viewModel.userGuess,
                    isGameOver = wordleUIState.value.isGameOver,
                    isWordFinished = wordleUIState.value.isWordFinished,
                    isRowEnabled = wordleUIState.value.isRowEnabled,
                    guessCheck = wordleUIState.value.guessCheck,
                    replayGame = {viewModel.resetGame()}
                )
            }
        }
    }
}



@Composable
fun TopBar(
    modifier : Modifier = Modifier
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            fontFamily = FontFamily(
                Font(R.font.karnak_condensed)
            ),
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center,
            fontSize = 36.sp,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun WordlePreview() {
    WordleWannabeTheme {
        WordleApp()
    }
}