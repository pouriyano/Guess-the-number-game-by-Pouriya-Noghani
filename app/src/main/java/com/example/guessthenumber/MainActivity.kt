package com.example.guessthenumber

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.guessthenumber.ui.theme.GuessTheNumberTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val randomnum: Int = Random.nextInt(100)
        setContent {
            GuessTheNumberTheme {
                val bol = remember {
                    mutableStateOf(true)
                }


                val adad = remember {
                    mutableIntStateOf(0)
                }
                val number = remember {
                    mutableStateOf("")
                }
                var textfil = remember {
                    mutableStateOf("")
                }
                val lists = remember {
                    mutableStateListOf<String>()
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                )


                {
                    Spacer(modifier = Modifier.height(50.dp))

                    TextField(
                        value = number.value,

                        onValueChange = {
                            number.value = it
                        },
                        modifier = Modifier.fillMaxWidth()
                    )



                    Button(
                        enabled = bol.value,

                        onClick = {
                            lists.add(number.value)


                            adad.value = adad.value + 1






                            if (number.value.toInt() > randomnum) {
                                if (adad.value == 5) {
                                    bol.value = false
                                    textfil.value = " You lose"

                                } else {
                                    textfil.value = number.value + " is to high"
                                }


                            } else if (number.value.toInt() < randomnum) {

                                if (adad.value == 5) {
                                    bol.value = false
                                    textfil.value = " You lose"
                                } else {
                                    textfil.value = number.value + " is to low"
                                }


                            } else if (number.value.toInt() == randomnum) {

                                textfil.value = " You win"

                            }


                        }, modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Try IT")

                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.06f)
                            .clip(shape = RoundedCornerShape(24.dp))
                            .background(color = Color(0xffE0E840)),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Text(text = textfil.value)

                    }
                    Text(text = "Last tries")
                    lists.forEach {
                        if (number.value.isNotBlank()) {
                            if (number.value.toInt() > randomnum)
                                Text(


                                    text = "number : " + it + "              " + number.value + ">Game number"
                                )
                            else if (number.value.toInt() < randomnum)
                                Text(


                                    text = "number : " + it + "              " + number.value + "<Game number"
                                )

                        }
                    }

                }


            }
        }

    }
}


