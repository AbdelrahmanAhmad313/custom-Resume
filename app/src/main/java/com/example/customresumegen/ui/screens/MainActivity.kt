package com.example.customresumegen.ui.screens

import android.os.Bundle
import android.util.Log
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import com.kavi.droid.color.picker.ui.KvColorPickerBottomSheet
import kotlin.math.roundToInt

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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomizeResume(modifier: Modifier) {
    var resumeDetails by remember { mutableStateOf<Resume?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var bgColor by remember { mutableStateOf(Color.White) }
    var fontColor by remember{ mutableStateOf(Color.Black) }
    val showBgSheet = remember { mutableStateOf(false) }
    val showFontSheet = remember { mutableStateOf(false) }
    val bgSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val fontSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var sliderPosition by remember{mutableFloatStateOf(512f)}
    var value by remember { mutableFloatStateOf(.5f) }
//    sliderPosition=getValue()
    LaunchedEffect(Unit) {
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
                        .background(bgColor, RoundedCornerShape(12.dp))
                        .padding(8.dp)

                ) {
                    items(1){_->
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(fontSize = 16.sp, color = fontColor)

                            ) {
                                append("Address: ")
                            }
                            withStyle(
                                style = SpanStyle(fontSize = 16.sp, color = fontColor)
                            ) {
                                append(resumeDetails?.address)
                            }

                        })

                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(fontSize = 16.sp, color = fontColor)

                            ) {
                                append("Email: ")
                            }
                            withStyle(
                                style = SpanStyle(fontSize = 16.sp, color = fontColor)
                            ) {
                                append(resumeDetails?.email)
                            }

                        })

                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(fontSize = 16.sp, color = fontColor)

                            ) {
                                append("Name: ")
                            }
                            withStyle(
                                style = SpanStyle(fontSize = 16.sp, color = fontColor)
                            ) {
                                append(resumeDetails?.name)
                            }

                        })
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(fontSize = 16.sp, color = fontColor)

                            ) {
                                append("Phone: ")
                            }
                            withStyle(
                                style = SpanStyle(fontSize = 16.sp, color = fontColor)
                            ) {
                                append(resumeDetails?.phone)
                            }

                        })
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = "Projects",
                        fontSize = 16.sp,
                        color = fontColor
                    )
                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = "--------------------------",
                        color = fontColor
                    )
                            resumeDetails!!.projects.forEach { project->
                                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                                    Text(modifier=Modifier.padding(8.dp), text = project.title, color = fontColor)
                                    Text(modifier=Modifier.padding(8.dp), text = project.description, color = fontColor)
                                    Text(modifier=Modifier.padding(8.dp),text = "${project.startDate} - ${project.endDate}", color = fontColor)
                                    Text("-----------------", color = fontColor)
                                }
                            }
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text ="Skills",
                        color = fontColor)
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "--------------------------",
                            color = fontColor
                        )
                        resumeDetails!!.skills.forEachIndexed { index,skill->
                            Text(modifier=Modifier.padding(8.dp), text = "${index+1}- $skill", color = fontColor)
                        }

                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(fontSize = 16.sp, color = fontColor)

                            ) {
                                append("Summery: ")
                            }
                            withStyle(
                                style = SpanStyle(fontSize = 16.sp, color = fontColor)
                            ) {
                                append(resumeDetails?.summary)
                            }

                        })
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(fontSize = 16.sp, color = fontColor)

                            ) {
                                append("Twitter: ")
                            }
                            withStyle(
                                style = SpanStyle(fontSize = 16.sp, color = fontColor)
                            ) {
                                append(resumeDetails?.twitter)
                            }

                        })
                }}
                if (showBgSheet.value) {
                    KvColorPickerBottomSheet(
                        showSheet = showBgSheet,
                        sheetState = bgSheetState,
                        onColorSelected = { selectedColor ->
                            bgColor=selectedColor
                        }
                    )
                }
                if (showFontSheet.value) {
                    KvColorPickerBottomSheet(
                        showSheet = showFontSheet,
                        sheetState = fontSheetState,
                        onColorSelected = { selectedColor ->
                            fontColor=selectedColor
                        }
                    )
                }
                Slider(
                    value = value,
                    onValueChange = { value = it },
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Transparent
                    ),
                    track = { sliderState ->
                        SliderDefaults.Track(
                            sliderState = sliderState,
                            thumbTrackGapSize = 0.dp,
                            colors = SliderDefaults.colors(
                                activeTrackColor = Color.Blue,
                                inactiveTrackColor = Color.Gray
                            )
                        )
                    },
                    thumb = {
                        Box(
                            Modifier
                                .size(24.dp)
                                .background(Color.Black, CircleShape)
                                .shadow(2.dp)  // Optional shadow
                        )
                    }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Button(onClick = { showBgSheet.value = true },
                        colors = ButtonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.Gray
                    )) { Text("Bg color") }
                    Button(onClick = { showFontSheet.value = true },
                        colors = ButtonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White,
                            disabledContainerColor = Color.Gray,
                            disabledContentColor = Color.Gray
                        )) { Text("Font Color") }
                    Button(onClick = {}) { Text("Font Size") }
                }
            }
        }
    }
}

fun ChangeFontColor(){}
fun ChangeFontSize(){}
@Preview(device = "spec:width=411dp,height=891dp", showSystemUi = true, showBackground = true)
@Composable
private fun PreviewCustomizeResume() {
    CustomizeResume(modifier = Modifier)
}

