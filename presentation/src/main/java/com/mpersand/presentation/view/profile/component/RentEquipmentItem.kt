package com.mpersand.presentation.view.profile.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import com.mpersand.presentation.R

@Composable
fun RentEquipmentItem(data: EquipmentResponseModel) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .padding(10.dp)
                    .size(width = 90.dp, height = 60.dp),
                painter = rememberAsyncImagePainter(model = data.image) ?: painterResource(id = R.drawable.ic_temp_equipment_image),
                contentDescription = "equipment image"
            )
            
            Column {
                Text(
                    text = data.name,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.fraunces_black)),
                        fontSize = 10.sp,
                        color = Color(0xFFC2C2C2)
                    )
                )

                Text(
                    text = data.productNumber,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.fraunces_black)),
                        fontSize = 7.sp,
                        color = Color(0xFFC2C2C2)
                    )
                )

                Text(
                    text = data.description,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.fraunces_black)),
                        fontSize = 7.sp,
                        color = Color(0xFFC2C2C2)
                    )
                )
            }
        }
    }
}