package com.example.customresumegen.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(top = 40.dp)
            .padding(horizontal = 20.dp)
            .shadow(12.dp, RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Red)

    ) {
        Text(modifier = Modifier.padding(16.dp) ,
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(fontSize = 16.sp)

                ) {
                    append("Name: ")
                }
                withStyle(
                    style = SpanStyle(fontSize = 16.sp)
                ){
                    append("Your Name")
                }

            })

        Text(modifier = Modifier.padding(16.dp) ,
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(fontSize = 16.sp)

                ) {
                    append("Name: ")
                }
                withStyle(
                    style = SpanStyle(fontSize = 16.sp)
                ){
                    append("Your Name")
                }

            })

        Text(modifier = Modifier.padding(16.dp) ,
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(fontSize = 16.sp)

                ) {
                    append("Name: ")
                }
                withStyle(
                    style = SpanStyle(fontSize = 16.sp)
                ){
                    append("Your Name")
                }

            })
    }
}

@Preview(device = "spec:width=411dp,height=891dp", showSystemUi = true, showBackground = true)
@Composable
private fun PreviewCustomizeResume() {
    CustomizeResume()
}

