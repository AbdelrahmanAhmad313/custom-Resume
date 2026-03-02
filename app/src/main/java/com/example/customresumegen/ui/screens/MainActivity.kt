package com.example.customresumegen.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.customresumegen.api.Resume
import com.example.customresumegen.ui.theme.CustomresumegenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CustomresumegenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CustomizeResume(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CustomizeResume(modifier: Modifier = Modifier) {
    val resumeDetails by remember { mutableStateOf<Resume?>(null) }

    Column (
        modifier = Modifier
        .fillMaxSize()
        .padding(24.dp)
        .padding(top = 12.dp),
        horizontalAlignment = Alignment.End
    ){
        Text(modifier=Modifier.padding(24.dp),
            text = "GPS"
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
//                .padding(24.dp)
//                .padding(top = 12.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .shadow(12.dp, RoundedCornerShape(12.dp))
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Red)
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(fontSize = 16.sp)

                        ) {
                            append("Address: ")
                        }
                        withStyle(
                            style = SpanStyle(fontSize = 16.sp)
                        ) {
                            append(resumeDetails?.address)
                        }

                    })

                Text(
                    modifier = Modifier.padding(16.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(fontSize = 16.sp)

                        ) {
                            append("Email: ")
                        }
                        withStyle(
                            style = SpanStyle(fontSize = 16.sp)
                        ) {
                            append("His email")
                        }

                    })

                Text(
                    modifier = Modifier.padding(16.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(fontSize = 16.sp)

                        ) {
                            append("Name: ")
                        }
                        withStyle(
                            style = SpanStyle(fontSize = 16.sp)
                        ) {
                            append("His Name")
                        }

                    })
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(fontSize = 16.sp)

                        ) {
                            append("Phone: ")
                        }
                        withStyle(
                            style = SpanStyle(fontSize = 16.sp)
                        ) {
                            append("His Phone")
                        }

                    })
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(fontSize = 16.sp)

                        ) {
                            append("Projects: ")
                        }
                        withStyle(
                            style = SpanStyle(fontSize = 16.sp)
                        ) {
                            append("His Projects")
                        }

                    })
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(fontSize = 16.sp)

                        ) {
                            append("SKills: ")
                        }
                        withStyle(
                            style = SpanStyle(fontSize = 16.sp)
                        ) {
                            append("His Skills")
                        }

                    })
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(fontSize = 16.sp)

                        ) {
                            append("Summery: ")
                        }
                        withStyle(
                            style = SpanStyle(fontSize = 16.sp)
                        ) {
                            append("His Summery")
                        }

                    })
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(fontSize = 16.sp)

                        ) {
                            append("Twitter: ")
                        }
                        withStyle(
                            style = SpanStyle(fontSize = 16.sp)
                        ) {
                            append("His Twitter")
                        }

                    })
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Button(onClick = {}) { Text("Bg color") }
                Button(onClick = {}) { Text("Font Color") }
                Button(onClick = {}) { Text("Font Size") }
            }
        }
    }
}

@Preview(device = "spec:width=411dp,height=891dp", showSystemUi = true, showBackground = true)
@Composable
private fun PreviewCustomizeResume() {
    CustomizeResume()
}

