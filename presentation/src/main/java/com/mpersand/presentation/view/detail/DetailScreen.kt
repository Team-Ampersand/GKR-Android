package com.mpersand.presentation.view.detail

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
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.mpersand.presentation.R
import com.mpersand.presentation.util.UiState
import com.mpersand.presentation.viewmodel.DetailViewModel
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    productNumber: String?,
    viewModel: DetailViewModel = hiltViewModel()
) {
    var showDialog by remember { mutableStateOf(false) }
    val getEquipmentInfoUiState by viewModel.getEquipmentInfoUiState.observeAsState()
    val postRentalRequestUiState by viewModel.postRentalRequestUiState.observeAsState()
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(Unit) {
        viewModel.getEquipmentInfo(checkNotNull(productNumber))
    }

    Scaffold(scaffoldState = scaffoldState) { paddingValues ->
        if (showDialog) {
            DetailDialog(onDismissRequest = { showDialog = false }) {
                when(postRentalRequestUiState) {
                    is UiState.Success -> {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("대여 요청이 완료되었습니다.")
                        }
                    }
                    else -> { coroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar("예상 못 한 오류가 발생 했습니다.\n 개발자에게 문의 해주세요.")
                    } }
                }
            }
        }

        when (val state = getEquipmentInfoUiState) {
            UiState.Loading -> {}
            is UiState.Success -> {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(paddingValues)
                ) {
                    Image(
                        modifier = modifier.fillMaxWidth(),
                        painter = rememberAsyncImagePainter(state.data!!.image),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = modifier.padding(horizontal = 26.dp)) {
                        Text(
                            modifier = modifier.padding(top = 15.dp),
                            text = state.data!!.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Thin,
                            fontFamily = FontFamily(Font(R.font.fraunces_black)),
                            color = Color(0xFF000000)
                        )
                        Text(
                            modifier = modifier.padding(top = 3.dp),
                            text = when (state.data!!.name) {
                                "맥북" -> {
                                    "#맥북  #노트북"
                                }

                                "갤럭시 북" -> {
                                    "#갤럭시 북  #노트북"
                                }

                                "터치모니터" -> {
                                    "#터치모니터  #모니터"
                                }

                                else -> {
                                    "#?"
                                }
                            },
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Thin,
                            fontFamily = FontFamily(Font(R.font.fraunces_black)),
                            color = Color(0xFF999999)
                        )
                        Text(
                            modifier = modifier.padding(top = 30.dp),
                            text = state.data!!.description,
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
            UiState.BadRequest -> {}
            UiState.Unauthorized -> {}
            UiState.NotFound -> {}
            else -> {}
        }
    }
}
