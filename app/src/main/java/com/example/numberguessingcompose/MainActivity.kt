package com.example.numberguessingcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.numberguessingcompose.ui.theme.NumberGuessingComposeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberGuessing()

        }
    }
}

var random: Int = Random.nextInt(1, 1000)
var click = 0
var start = true

@Composable
fun NumberGuessing() {
    val text = remember { mutableStateOf("") }
    val enter = remember { mutableStateOf("") }
    val answer = remember { mutableStateOf(TextFieldValue()) }

    fun reset() {
        random = Random.nextInt(1, 1000)
        text.value = "Try to guess the number i'm thinking of from 1-1000!"
        start = true
       click = 0
    }

    fun algorithm() {
        if (start) {
            if (answer.value.text.isEmpty()) {
                text.value = "enter number"
            } else {
                if (answer.value.text.toInt() < random) {
                    text.value = "Your number is too low"
                    click++

                } else if (answer.value.text.toInt() > random) {
                    text.value = "Your number is too high"
                    click++

                } else {
                    text.value = "Congratulation, your number is correct. You use $click time(s)"
                    start = false
                }
            }
        } else {
            reset()
        }
    }


    Column()
    {   Text( text.value,
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    )
        if(start) {
            TextField(
                value = answer.value,
                onValueChange = {answer.value = it },
                singleLine = true,
                placeholder = { Text("Enter Your guess")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

            )
        }


        Button( onClick = { algorithm() } ) {
            if(start) {
                enter.value = "Enter"
                Text(enter.value)
            } else{
                enter.value = "Play again"
                Text(enter.value)
            }

        }


    }
}
