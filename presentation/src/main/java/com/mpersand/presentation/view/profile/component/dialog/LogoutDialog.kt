package com.mpersand.presentation.view.profile.component.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mpersand.presentation.R

@Composable
fun LogoutDialog(
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = Modifier.size(width = 250.dp, height = 150.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            LogoutDialogContent(onDismissRequest = onDismissRequest)
        }
    }
}

@Composable
fun LogoutDialogContent(
    onDismissRequest: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.6f))

        Text(
            text = "로그아웃 하시겠습니까?",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.fraunces_black)),
                fontSize = 12.sp
            )
        )

        Spacer(modifier = Modifier.weight(0.4f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp)
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFF26222)),
                onClick = {  }
            ) {
                Text(
                    text = "네",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.inter_light)),
                        fontSize = 9.sp,
                        color = Color.White
                    )
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFA559)),
                onClick = { onDismissRequest() }
            ) {
                Text(
                    text = "아뇨",
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.inter_light)),
                        fontSize = 9.sp,
                        color = Color.White
                    )
                )
            }
        }
    }
}