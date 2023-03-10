package com.example.wordlewannabe.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun GameScreen(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        WordRow(
            userGuess = "",
            onLetterGuessed = {},
            onKeyboardDone = {},
            enabled = true
        )
        WordRow(
            userGuess = "",
            onLetterGuessed = {},
            onKeyboardDone = {},
            enabled = false
        )
        WordRow(
            userGuess = "",
            onLetterGuessed = {},
            onKeyboardDone = {},
            enabled = false
        )
        WordRow(
            userGuess = "",
            onLetterGuessed = {},
            onKeyboardDone = {},
            enabled = false
        )
        WordRow(
            userGuess = "",
            onLetterGuessed = {},
            onKeyboardDone = {},
            enabled = false
        )
        WordRow(
            userGuess = "",
            onLetterGuessed = {},
            onKeyboardDone = {},
            enabled = false
        )
    }
}


@Composable
fun WordRow(
    modifier: Modifier = Modifier,
    userGuess : String,
    enabled : Boolean,
    onLetterGuessed : (String) -> Unit,
    onKeyboardDone : () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val focusManager = LocalFocusManager.current
        TextField(
            value = userGuess,
            onValueChange = onLetterGuessed,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Right)
                }
            ),
            modifier = Modifier.weight(1f),
            enabled = enabled
        )
        TextField(
            value = userGuess,
            onValueChange = onLetterGuessed,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Right)
                }
            ),
            modifier = Modifier.weight(1f),
            enabled = enabled
        )
        TextField(
            value = userGuess,
            onValueChange = onLetterGuessed,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Right)
                }
            ),
            modifier = Modifier.weight(1f),
            enabled = enabled
        )
        TextField(
            value = userGuess,
            onValueChange = onLetterGuessed,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Right)
                }
            ),
            modifier = Modifier.weight(1f),
            enabled = enabled
        )
        TextField(
            value = userGuess,
            onValueChange = onLetterGuessed,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {onKeyboardDone()}
            ),
            modifier = Modifier.weight(1f),
            enabled = enabled
        )
    }
}