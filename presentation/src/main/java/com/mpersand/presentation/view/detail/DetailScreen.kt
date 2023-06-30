package com.mpersand.presentation.view.detail

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mpersand.presentation.R

@Composable
fun DetailScreen(modifier: Modifier = Modifier) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        DetailDialog {
            showDialog = false
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Image(
            modifier = modifier.fillMaxWidth(),
//            painter = rememberAsyncImagePainter(imageUri),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "image",
            contentScale = ContentScale.Crop
        )
        Column(modifier = modifier.padding(horizontal = 26.dp)) {
            Text(
                modifier = modifier.padding(top = 15.dp),
                text = "라즈베리파이 4",
                fontSize = 16.sp,
                fontWeight = FontWeight.Thin,
                fontFamily = FontFamily(Font(R.font.fraunces_black)),
                color = Color(0xFF000000)
            )
            Text(
                modifier = modifier.padding(top = 3.dp),
                text = "#IOT",
                fontSize = 8.sp,
                fontWeight = FontWeight.Thin,
                fontFamily = FontFamily(Font(R.font.fraunces_black)),
                color = Color(0xFF999999)
            )
            Text(
                modifier = modifier.padding(top = 30.dp),
                text = "라즈베리파이 4는 IOT 통신을 하는데 필요한 기자재입니다.",
                fontSize = 13.sp,
                fontWeight = FontWeight.Thin,
                fontFamily = FontFamily(Font(R.font.fraunces_black)),
                color = Color(0xFF000000)
            )
            Spacer(modifier = modifier.weight(1f))
            Button(
                modifier = modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFFFA559),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                onClick = { showDialog = true }
            ) {
                Text(text = "대여하기")
            }
            Spacer(modifier = modifier.height(50.dp))
        }
    }
}

@Preview
@Composable
fun preview() {
    DetailScreen()
}