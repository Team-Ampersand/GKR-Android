package com.mpersand.presentation.view.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mpersand.presentation.R
import com.mpersand.presentation.view.component.GKRToolbar
import com.mpersand.presentation.view.profile.component.BanHistoryCard
import com.mpersand.presentation.view.profile.component.RentEquipmentItem
import com.mpersand.presentation.view.profile.component.dialog.LogoutDialog

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFAFAFA))
    ) {
        GKRToolbar(title = "GKR")
        ProfileUserCard()
        RentEquipmentView()
        UserBanHistoryView()
    }
}

@Composable
fun ColumnScope.ProfileUserCard() {
    var logoutDialogState by remember { mutableStateOf(false) }

    if (logoutDialogState) {
        LogoutDialog {
            logoutDialogState = false
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .weight(0.2f)
            .clip(RoundedCornerShape(5.dp))
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .padding(25.dp)
                .size(60.dp),
            painter = painterResource(id = R.drawable.ic_user_image),
            contentDescription = "profile"
        )

        Column {
            Text(
                text = "임가람",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.fraunces_black)),
                    color = Color(0xFFC2C2C2),
                    fontSize = 15.sp,
                )
            )

            Text(
                text = "3학년 4반 12번",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.fraunces_black)),
                    color = Color(0xFFC2C2C2),
                    fontSize = 10.sp
                )
            )

            Text(
                text = "대여한 물품 1개",
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.fraunces_black)),
                    color = Color(0xFFC2C2C2),
                    fontSize = 10.sp
                )
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        
        IconButton(
            modifier = Modifier.padding(end = 25.dp),
            onClick = { logoutDialogState = logoutDialogState.not() }
        ) {
            Icon(
                modifier = Modifier.size(25.dp),
                imageVector = Icons.Default.MoreVert,
                contentDescription = "profile more vert",
                tint = Color(0xFF999999)
            )
        }
    }
}

@Composable
fun ColumnScope.RentEquipmentView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .weight(0.3f)
    ) {
        Text(
            text = "대여 물품",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.fraunces_black)),
                fontSize = 15.sp,
                color = Color(0xFFC0C0C0)
            )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(Color.White)
                .height(200.dp),
            contentPadding = PaddingValues(5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            val list = listOf(1,2,3,4)
            items(list) {
                RentEquipmentItem()
            }
        }
    }
}

@Composable
fun ColumnScope.UserBanHistoryView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .weight(0.4f)
    ) {
        Text(
            text = "제재 내역",
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.fraunces_black)),
                fontSize = 15.sp,
                color = Color(0xFFC0C0C0)
            )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(Color.White),
            contentPadding = PaddingValues(5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            val list = listOf(1,2,3,4)
            items(list) {
                BanHistoryCard()
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    ProfileScreen()
}