package com.mpersand.presentation.view.main.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.mpersand.presentation.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GKRFilterItem(
    title: String,
    select: Boolean,
    onClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(7.dp),
        border = BorderStroke(width = if (select) 0.dp else 1.dp, color = Color(0xFFC3C3C3)),
        backgroundColor = if (select) Color(0xFFF26222) else Color(0xFFFAFAFA),
        onClick = onClick
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            text = title,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.inter_light)),
                color = if (select) Color.White else Color(0xFFF26222)
            )
        )
    }
}