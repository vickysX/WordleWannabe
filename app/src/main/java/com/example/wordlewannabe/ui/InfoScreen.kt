package com.example.wordlewannabe.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordlewannabe.R
import com.example.wordlewannabe.ui.theme.WordleWannabeTheme
import com.example.wordlewannabe.ui.theme.WordleYellow

@Composable
fun InfoScreen(
    modifier: Modifier = Modifier,
    onNextClicked : () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .wrapContentHeight(Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = buildAnnotatedString {
                append(stringResource(id = R.string.game_rules_first))
                append(" ")
                withStyle(
                    style = SpanStyle(
                        background = Color.Green,
                        color = Color.White,
                        shadow = Shadow()
                    )
                ) {
                    append(stringResource(id = R.string.green))
                }
                append(" ")
                append(stringResource(id = R.string.game_rules_second))
                append(" ")
                withStyle(
                    style = SpanStyle(
                        background = WordleYellow,
                        color = Color.White,
                        shadow = Shadow()
                    )
                ) {
                    append(stringResource(id = R.string.yellow))
                }
                append(" ")
                append(stringResource(id = R.string.game_rules_third))
                append(" ")
                withStyle(
                    style = SpanStyle(
                        background = Color.Gray,
                        color = Color.White,
                        shadow = Shadow()
                    )
                ) {
                    append(stringResource(id = R.string.gray))
                }
                append(" ")
                append(stringResource(id = R.string.game_rules_last))
            },
            fontSize = 24.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(start = 60.dp, end = 60.dp, bottom = 30.dp)
        )
        Button(
            onClick = onNextClicked,
            modifier = Modifier
        ) {
            Text(stringResource(id = R.string.next_btn))
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun InfoScreenPreview() {
    WordleWannabeTheme() {
        InfoScreen() {
            
        }
    }
}