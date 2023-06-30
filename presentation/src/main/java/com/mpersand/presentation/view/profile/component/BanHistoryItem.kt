package com.mpersand.presentation.view.profile.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mpersand.presentation.R

@Composable
fun BanHistoryCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(width = 1.dp, color = Color(0xFFD3D3D3))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "[제재 사항] 기자재 반납일 지남",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.inter_black)),
                        fontSize = 15.sp
                    )
                )
                
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "안녕하세요 GKR 입니다.",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.inter_light)),
                        fontSize = 12.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            
            Text(
                text = "2023.06.26",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.inter_light)),
                    fontSize = 12.sp,
                    color = Color(0xFF525252)
                )
            )
        }
    }
}