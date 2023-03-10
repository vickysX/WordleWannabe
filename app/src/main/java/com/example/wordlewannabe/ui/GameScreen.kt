package com.example.wordlewannabe.ui

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordlewannabe.R

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    first : String,
    second : String,
    third : String,
    fourth : String,
    fifth : String,
    isWordFinished: List<Boolean>,
    isRowEnabled : List<Boolean>,
    guessCheck : MutableList<MutableList<Pair<String, LetterStatus>?>>,
    isGameOver : Boolean,
    isGameWon: Boolean,
    onLetterGuessed: (Pair<String, Int>) -> Unit,
    onWordFinished : (Int) -> Unit,
    replayGame : () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        WordRow(
            first = first,
            second = second,
            third = third,
            fourth = fourth,
            fifth = fifth,
            onLetterGuessed = {onLetterGuessed(it)},
            onKeyboardDone = {onWordFinished(0)},
            enabled = isRowEnabled[0],
            isWordFinished = isWordFinished[0],
            guessCheck = guessCheck[0]
        )
        WordRow(
            first = first,
            second = second,
            third = third,
            fourth = fourth,
            fifth = fifth,
            onLetterGuessed = {onLetterGuessed(it)},
            onKeyboardDone = {onWordFinished(1)},
            enabled = isRowEnabled[1],
            isWordFinished = isWordFinished[1],
            guessCheck = guessCheck[1]
        )
        WordRow(
            first = first,
            second = second,
            third = third,
            fourth = fourth,
            fifth = fifth,
            onLetterGuessed = {onLetterGuessed(it)},
            onKeyboardDone = {onWordFinished(2)},
            enabled = isRowEnabled[2],
            isWordFinished = isWordFinished[2],
            guessCheck = guessCheck[2]
        )
        WordRow(
            first = first,
            second = second,
            third = third,
            fourth = fourth,
            fifth = fifth,
            onLetterGuessed = {onLetterGuessed(it)},
            onKeyboardDone = {onWordFinished(3)},
            enabled = isRowEnabled[3],
            isWordFinished = isWordFinished[3],
            guessCheck = guessCheck[3]
        )
        WordRow(
            first = first,
            second = second,
            third = third,
            fourth = fourth,
            fifth = fifth,
            onLetterGuessed = {onLetterGuessed(it)},
            onKeyboardDone = {onWordFinished(4)},
            enabled = isRowEnabled[4],
            isWordFinished = isWordFinished[4],
            guessCheck = guessCheck[4]
        )
        WordRow(
            first = first,
            second = second,
            third = third,
            fourth = fourth,
            fifth = fifth,
            onLetterGuessed = {onLetterGuessed(it)},
            onKeyboardDone = {onWordFinished(5)},
            enabled = isRowEnabled[5],
            isWordFinished = isWordFinished[5],
            guessCheck = guessCheck[5]
        )
        if (isGameOver || isGameWon) {
            FinalAlertDialog(
                onConfirmButton = replayGame,
                isGameWon = isGameWon
            )
        }
    }
}


@Composable
fun WordRow(
    modifier: Modifier = Modifier,
    first: String,
    second: String,
    third: String,
    fourth: String,
    fifth: String,
    isWordFinished : Boolean,
    guessCheck: MutableList<Pair<String, LetterStatus>?>,
    enabled : Boolean,
    onLetterGuessed : (Pair<String, Int>) -> Unit,
    onKeyboardDone : () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LetterField(
            index = 0,
            letter = first,
            isWordFinished = isWordFinished,
            enabled = enabled,
            onLetterGuessed = onLetterGuessed,
            guessCheck = guessCheck,
            modifier = Modifier.weight(1f)
        ) {

        }
        LetterField(
            index = 1,
            letter = second,
            isWordFinished = isWordFinished,
            enabled = enabled,
            onLetterGuessed = onLetterGuessed,
            guessCheck = guessCheck,
            modifier = Modifier.weight(1f)
        ) {

        }
        LetterField(
            index = 2,
            letter = third,
            isWordFinished = isWordFinished,
            enabled = enabled,
            onLetterGuessed = onLetterGuessed,
            guessCheck = guessCheck,
            modifier = Modifier.weight(1f)
        ) {

        }
        LetterField(
            index = 3,
            letter = fourth,
            isWordFinished = isWordFinished,
            enabled = enabled,
            onLetterGuessed = onLetterGuessed,
            guessCheck = guessCheck,
            modifier = Modifier.weight(1f)
        ) {

        }
        LetterField(
            index = 4,
            letter = fifth,
            isWordFinished = isWordFinished,
            enabled = enabled,
            onLetterGuessed = onLetterGuessed,
            onKeyboardDone = onKeyboardDone,
            guessCheck = guessCheck,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun LetterField(
    modifier: Modifier = Modifier, // remember weight modifier
    index : Int,
    letter : String,
    isWordFinished: Boolean,
    guessCheck : MutableList<Pair<String, LetterStatus>?>,
    enabled: Boolean,
    onLetterGuessed: (Pair<String, Int>) -> Unit,
    onKeyboardDone: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    if (!isWordFinished) {
        TextField(
            value = letter,
            onValueChange = {onLetterGuessed(Pair(it, index))},
            keyboardOptions = if (index == 4) {
                KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            } else {
                KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )
            },
            keyboardActions = if (index == 4) {
                KeyboardActions(
                    onDone = {onKeyboardDone()}
                )
            } else {
                KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Right)
                    }
                )
            },
            modifier = modifier,
            enabled = enabled
        )
    } else {
        // display letters in color
        //val text = guessCheck[index]!!.first.uppercase()
        //val letterColor = guessCheck[index]!!.second.color
        Text(
            text = guessCheck[index]!!.first.uppercase(),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 60.sp,
            modifier = Modifier.background(
                color = guessCheck[index]!!.second.color
            )
        )
    }

}

@Composable
fun FinalAlertDialog(
    modifier: Modifier = Modifier,
    isGameWon : Boolean,
    onConfirmButton : () -> Unit
) {
    val activity = LocalContext.current as Activity
    val title = when {
        isGameWon -> stringResource(id = R.string.congratulations)
        else -> stringResource(id = R.string.game_over)
    }
    val dialogText = when {
        isGameWon -> stringResource(id = R.string.game_won)
        else -> stringResource(id = R.string.dialog_text)
    }
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(
                text = title
            )
        },
        text = {
            Text(
                text = dialogText
            )
        },
        dismissButton = {
            TextButton(onClick = {activity.finish()}) {
                Text(stringResource(id = R.string.exit))
            }
        },
        confirmButton = {
            TextButton(onClick = onConfirmButton) {
                Text(stringResource(id = R.string.play_again))
            }
        }
    )
}

