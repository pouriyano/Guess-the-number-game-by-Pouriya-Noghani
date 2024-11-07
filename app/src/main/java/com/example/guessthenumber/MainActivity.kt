package com.example.guessthenumber
//import android.graphics.fonts.Font
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.guessthenumber.ui.theme.GuessTheNumberTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var textbool = false
        var red = 1;
        var green = 195;
        var blue = 255;
        var alpha = 255;
        var randomnum: Int = Random.nextInt(101)
        var gamestatus: String = "Try It"
        setContent {
            GuessTheNumberTheme {

                val boolofstate = remember {
                    mutableStateOf(true)
                }
                val limit = remember {
                    mutableIntStateOf(5)
                }
                val number = remember {
                    mutableStateOf("")
                }
                val textofstate = remember {
                    mutableStateOf("Status")
                }
                val lists = remember {
                    mutableStateListOf<String>()
                }
                var textfield: String
                val boolofbutton = remember {
                    mutableStateOf(true)
                }
                val listremover = remember {
                    mutableStateOf(false)
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                )
                {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "( Guess the number between 1 and 100 )",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = TextUnit(17f, TextUnitType.Sp),
                        color = Color(10, 10, 10, 87)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    if (limit.value == 1 && gamestatus != "You WIN") {
                        textfield = "It's your last chance"
                    } else if (limit.value == 0 && gamestatus != "You WIN") {
                        textfield = "It was " + randomnum
                    } else {
                        if (gamestatus == "You WIN" && boolofstate.value == false) {
                            textfield = "Congratulations!"
                        } else {
                            textfield = "You have " + limit.value + " chances"
                        }
                    }
                    Text(
                        text = textfield,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(14.dp))
//Text Field
                    TextField(
                        value = number.value,
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color(
                                241, 241, 241, 255
                            )
                        ),
                        onValueChange = {
                            number.value = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(8.dp)),
                        textStyle = TextStyle(textAlign = TextAlign.Center)
                    )
                    if (number.value.toIntOrNull() != null && number.value.toInt() > 0) {
                        boolofbutton.value = true
                    } else {
                        boolofbutton.value = false
                    }
                    Spacer(modifier = Modifier.height(12.dp))
//my button
                    if (boolofstate.value == true) {
                        gamestatus = "Try It"
                    }
                    TextButton(
                        enabled = boolofstate.value && boolofbutton.value,
                        onClick = {
                            listremover.value = false
                            limit.value = limit.value - 1
                            lists.add(number.value)
                            if (number.value.toInt() > randomnum) {
                                if (limit.value == 0) {
                                    gamestatus = "You Lose"
                                    red = 254;
                                    green = 87;
                                    blue = 34;
                                    alpha = 255;
                                    boolofstate.value = false
                                    textofstate.value = "Reset"
                                } else {
                                    textofstate.value = number.value + " is too high"
                                }
                            } else if (number.value.toInt() < randomnum) {
                                if (limit.value == 0) {
                                    gamestatus = "You Lose"
                                    red = 254;
                                    green = 87;
                                    blue = 34;
                                    alpha = 255;
                                    boolofstate.value = false
                                    textofstate.value = "Reset"
                                } else {
                                    textofstate.value = number.value + " is too low"
                                }
                            } else if (number.value.toInt() == randomnum) {
                                gamestatus = "You WIN"
                                red = 76;
                                green = 176;
                                blue = 80;
                                alpha = 255;
                                boolofstate.value = false
                                textofstate.value = "Reset"
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(
                                Color(red, green, blue, alpha)
                            )
                    ) {
                        Text(
                            text = gamestatus,
                            textAlign = TextAlign.Center,
                            fontSize = TextUnit(20f, TextUnitType.Sp),
                            fontWeight = FontWeight.Bold,
                            color = Color(
                                245,
                                240,
                                240,
                                255
                            )
                        )
                    }
                    if (boolofstate.value == false) {
                        textbool = true

                    } else if (boolofstate.value == true) {
                        textbool = false
                    }
//My state
                    Spacer(modifier = Modifier.height(12.dp))

                    TextButton(
                        enabled = textbool,
                        onClick = {
                            limit.value = 5

                            randomnum = Random.nextInt(101)
                            println(randomnum)
                            boolofstate.value = true
                            listremover.value = true
                            textofstate.value = "Status"
                            red = 1;
                            green = 195;
                            blue = 255;
                            alpha = 255;
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(
                                Color(252, 230, 34, 201)
                            ),

                        ) {
                        Text(text = textofstate.value, color = Color(5, 0, 0, 255))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Last tries",
                        fontSize = TextUnit(15f, TextUnitType.Sp), fontWeight = FontWeight.Bold

                    )
 // my list
                    lists.forEach {


                        if (it.toInt() > randomnum && listremover.value == false) {
                            Row(modifier = Modifier.fillMaxWidth()) {

                                TextButton(
                                    enabled = false,
                                    onClick = {

                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(shape = RoundedCornerShape(8.dp))
                                        .background(
                                            Color(239, 108, 0, 255)
                                        ),
                                ) {

                                    Text(
                                        text = "number : " + it, modifier = Modifier
                                            .padding(4.dp)
                                            .weight(2f), color = Color(
                                            247,
                                            247,
                                            247,
                                            255
                                        )
                                    )
                                    Text(
                                        text = it + ">Game number", modifier = Modifier
                                            .weight(1f)
                                            .padding(4.dp), color = Color(
                                            247,
                                            247,
                                            247,
                                            255
                                        )
                                    )
                                }
                            }
                        } else if (it.toInt() < randomnum && listremover.value == false) {
                            Row(modifier = Modifier.fillMaxWidth()) {

                                TextButton(
                                    enabled = false,
                                    onClick = {

                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(shape = RoundedCornerShape(8.dp))
                                        .background(
                                            Color(22, 101, 193, 255)
                                        ),

                                    ) {
                                    Text(
                                        text = "number : " + it, modifier = Modifier
                                            .padding(4.dp)
                                            .weight(2f), color = Color(
                                            247,
                                            247,
                                            247,
                                            255
                                        )
                                    )
                                    Text(
                                        text = it + "<Game number", modifier = Modifier
                                            .weight(1f)
                                            .padding(4.dp), color = Color(
                                            247,
                                            247,
                                            247,
                                            255
                                        )
                                    )
                                }
                            }
                        } else if (listremover.value == true) {
                            lists.clear()
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
    }

}



