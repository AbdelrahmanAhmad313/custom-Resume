package com.example.customresumegen.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.customresumegen.api.Resume
import com.example.customresumegen.api.getDetails
import com.example.customresumegen.ui.theme.CustomresumegenTheme
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.kavi.droid.color.picker.ui.KvColorPickerBottomSheet
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
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

@SuppressLint("ContextCastToActivity")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomizeResume(modifier: Modifier) {
    var resumeDetails by remember { mutableStateOf<Resume?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var bgColor by remember { mutableStateOf(Color.White) }
    var fontColor by remember { mutableStateOf(Color.Black) }
    val showBgSheet = remember { mutableStateOf(false) }
    val showFontSheet = remember { mutableStateOf(false) }
    val bgSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val fontSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var fontSize by remember { mutableStateOf(16) }
    LaunchedEffect(Unit) {
        getDetails { result ->
            resumeDetails = result
            isLoading = false

        }
    }
    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
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
                    items(1) { _ ->
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(fontSize = fontSize.sp, color = fontColor)

                                ) {
                                    append("Address: ")
                                }
                                withStyle(
                                    style = SpanStyle(fontSize = fontSize.sp, color = fontColor)
                                ) {
                                    append(resumeDetails?.address)
                                }

                            })

                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(fontSize = fontSize.sp, color = fontColor)

                                ) {
                                    append("Email: ")
                                }
                                withStyle(
                                    style = SpanStyle(fontSize = fontSize.sp, color = fontColor)
                                ) {
                                    append(resumeDetails?.email)
                                }

                            })

                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(fontSize = fontSize.sp, color = fontColor)

                                ) {
                                    append("Name: ")
                                }
                                withStyle(
                                    style = SpanStyle(fontSize = fontSize.sp, color = fontColor)
                                ) {
                                    append(resumeDetails?.name)
                                }

                            })
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(fontSize = fontSize.sp, color = fontColor)

                                ) {
                                    append("Phone: ")
                                }
                                withStyle(
                                    style = SpanStyle(fontSize = fontSize.sp, color = fontColor)
                                ) {
                                    append(resumeDetails?.phone)
                                }

                            })
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "Projects",
                            fontSize = fontSize.sp,
                            color = fontColor
                        )
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "--------------------------",
                            fontSize = fontSize.sp,
                            color = fontColor
                        )
                        resumeDetails!!.projects.forEach { project ->
                            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                                Text(
                                    modifier = Modifier.padding(8.dp),
                                    text = project.title,
                                    fontSize = fontSize.sp,
                                    color = fontColor
                                )
                                Text(
                                    modifier = Modifier.padding(8.dp),
                                    text = project.description,
                                    fontSize = fontSize.sp,
                                    color = fontColor
                                )
                                Text(
                                    modifier = Modifier.padding(8.dp),
                                    text = "${project.startDate} - ${project.endDate}",
                                    color = fontColor,
                                    fontSize = fontSize.sp,
                                )
                                Text(
                                    "-----------------",
                                    fontSize = fontSize.sp,
                                    color = fontColor
                                )
                            }
                        }
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = "Skills",
                            color = fontColor,
                            fontSize = fontSize.sp
                        )
                        Text(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            text = "--------------------------",
                            color = fontColor,
                            fontSize = fontSize.sp
                        )
                        resumeDetails!!.skills.forEachIndexed { index, skill ->
                            Text(
                                modifier = Modifier.padding(8.dp),
                                fontSize = fontSize.sp,
                                text = "${index + 1}- $skill",
                                color = fontColor
                            )
                        }

                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(fontSize = fontSize.sp, color = fontColor)

                                ) {
                                    append("Summery: ")
                                }
                                withStyle(
                                    style = SpanStyle(fontSize = fontSize.sp, color = fontColor)
                                ) {
                                    append(resumeDetails?.summary)
                                }

                            })
                        Text(
                            modifier = Modifier.padding(16.dp),
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(fontSize = fontSize.sp, color = fontColor)

                                ) {
                                    append("Twitter: ")
                                }
                                withStyle(
                                    style = SpanStyle(fontSize = fontSize.sp, color = fontColor)
                                ) {
                                    append(resumeDetails?.twitter)
                                }

                            })
                    }
                }
                if (showBgSheet.value) {
                    KvColorPickerBottomSheet(
                        showSheet = showBgSheet,
                        sheetState = bgSheetState,
                        onColorSelected = { selectedColor ->
                            bgColor = selectedColor
                        }
                    )
                }
                if (showFontSheet.value) {
                    KvColorPickerBottomSheet(
                        showSheet = showFontSheet,
                        sheetState = fontSheetState,
                        onColorSelected = { selectedColor ->
                            fontColor = selectedColor
                        }
                    )
                }
                Column {
                    Text("Font Size: $fontSize")
                    Slider(
                        value = fontSize.toFloat(),
                        onValueChange = { newValue ->
                            fontSize = (newValue.roundToInt() / 2 * 2).coerceIn(2, 26)
                        },
                        valueRange = 2f..26f,
                        colors = SliderDefaults.colors(
                            thumbColor = Color.Transparent
                        ),
                        track = { sliderState ->
                            SliderDefaults.Track(
                                sliderState = sliderState,
                                thumbTrackGapSize = 0.dp,
                                modifier = Modifier.height(4.dp),
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
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // First button - exactly half
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = { showBgSheet.value = true },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Black,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Bg Color")
                        }

                        // Second button - exactly half (remaining space)
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = { showFontSheet.value = true },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Black,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Font Color")
                        }
                    }

                }
            }
        }
    }
}

@Preview(device = "spec:width=411dp,height=891dp", showSystemUi = true, showBackground = true)
@Composable
private fun PreviewCustomizeResume() {
    CustomizeResume(modifier = Modifier)
}




