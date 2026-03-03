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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import com.example.customresumegen.api.ProjectDetails
import com.example.customresumegen.api.Resume
import com.example.customresumegen.api.getDetails
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
    var resumeDetails by remember { mutableStateOf<Resume?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var index by remember { mutableStateOf(0) }
    LaunchedEffect(Unit) {  // Unit = runs once
        getDetails { result ->
            resumeDetails = result
            isLoading = false
        }
    }
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .padding(top = 12.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                modifier = Modifier.padding(24.dp),
                text = "GPS"
            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(550.dp)
                        .shadow(12.dp, RoundedCornerShape(12.dp))
                        .background(Color.White, RoundedCornerShape(12.dp))
                        .padding(8.dp)

                ) {
                    items(1){_->
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
                                append(resumeDetails?.email)
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
                                append(resumeDetails?.name)
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
                                append(resumeDetails?.phone)
                            }

                        })
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = "Projects",
                        fontSize = 16.sp,
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = "--------------------------"
                    )
                            resumeDetails!!.projects.forEach { project->
                                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                                    Text(modifier=Modifier.padding(8.dp), text = project.title)
                                    Text(modifier=Modifier.padding(8.dp), text = project.description)
                                    Text(modifier=Modifier.padding(8.dp),text = "${project.startDate} - ${project.endDate}")
                                    Text("-----------------")
                                }
                            }
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text ="Skills")
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "--------------------------"
                        )
                        resumeDetails!!.skills.forEachIndexed { index,skill->
                            Text(modifier=Modifier.padding(8.dp), text = "${index+1}- $skill")
                        }

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
                                append(resumeDetails?.summary)
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
                                append(resumeDetails?.twitter)
                            }

                        })
                }}
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Button(onClick = { ChangeBgColor(index) }) { Text("Bg color") }
                    Button(onClick = {}) { Text("Font Color") }
                    Button(onClick = {}) { Text("Font Size") }
                }
            }
        }
    }
}
fun ChangeBgColor(index:Int):Color{
    val bgColor= listOf(Color.White,Color.Black,Color.Red,Color.Blue,Color.Yellow,Color.Green)
    var co =index
    co++
    return bgColor[co]
}
fun ChangeFontColor(){}
fun ChangeFontSize(){}
@Preview(device = "spec:width=411dp,height=891dp", showSystemUi = true, showBackground = true)
@Composable
private fun PreviewCustomizeResume() {
    CustomizeResume()
}

