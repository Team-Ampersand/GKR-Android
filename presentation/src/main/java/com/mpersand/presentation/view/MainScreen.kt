package com.mpersand.presentation.view

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.mpersand.presentation.R

@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val imageUri = remember {
        mutableStateOf<Uri?>(null)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 25.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(45.dp)
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "GKR",
                fontFamily = FontFamily(Font(R.font.fraunces_black)),
                fontSize = 20.sp,
                fontWeight = FontWeight.Thin,
                color = Color(0xFFFFA559),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.ic_hamburger),
                contentDescription = "hamburger"
            )
            Spacer(modifier = modifier.width(16.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "search"
            )
            Spacer(modifier = modifier.width(16.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_bell),
                contentDescription = "search"
            )
        }
        LazyColumn {
            items(15) {
                Row(modifier = modifier.fillMaxWidth()) {
                    Image(
                        modifier = modifier.size(120.dp, 90.dp),
                        painter = rememberAsyncImagePainter(imageUri.value) ?: painterResource(id = R.drawable.ic_logo),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = modifier.width(10.dp))
                    Column {
                        Text(
                            modifier = modifier.padding(top = 10.dp),
                            text = "기자재 이름 $it",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Thin,
                            fontFamily = FontFamily(Font(R.font.fraunces_black))
                        )
                        Text(
                            modifier = modifier.padding(top = 2.dp),
                            text = "대여 기간 - 06.01 ~ 07.01",
                            fontSize = 7.sp,
                            fontWeight = FontWeight.Thin,
                            fontFamily = FontFamily(Font(R.font.fraunces_black)),
                            color = Color(0xFFC0C0C0)
                        )
                        Text(
                            modifier = modifier.padding(top = 2.dp),
                            text = "대여 장소 - 전문교육부",
                            fontSize = 7.sp,
                            fontWeight = FontWeight.Thin,
                            fontFamily = FontFamily(Font(R.font.fraunces_black)),
                            color = Color(0xFFC0C0C0)
                        )
                        Text(
                            modifier = modifier.padding(top = 2.dp),
                            text = "#IOT",
                            fontSize = 7.sp,
                            fontWeight = FontWeight.Thin,
                            fontFamily = FontFamily(Font(R.font.fraunces_black)),
                            color = Color(0xFFC0C0C0),
                        )
                    }
                    Spacer(modifier = modifier.height(10.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun preview() {
    MainScreen()
}