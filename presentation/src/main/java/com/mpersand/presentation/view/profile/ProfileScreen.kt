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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import com.mpersand.domain.model.response.UserResponseModel
import com.mpersand.presentation.R
import com.mpersand.presentation.util.UiState
import com.mpersand.presentation.view.component.GKRToolbar
import com.mpersand.presentation.view.profile.component.BanHistoryCard
import com.mpersand.presentation.view.profile.component.RentEquipmentItem
import com.mpersand.presentation.view.profile.component.dialog.LogoutDialog
import com.mpersand.presentation.viewmodel.ProfileViewModel
import com.mpersand.presentation.viewmodel.ViolationViewModel
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    violationViewModel: ViolationViewModel = hiltViewModel(),
    navigateToMain: () -> Unit,
    navigateToSignIn: () -> Unit
) {
    profileViewModel.getUser()
    val userResult by profileViewModel.getUser.observeAsState()

    profileViewModel.getEquipmentRentalListByUser()
    val equipmentRentalList by profileViewModel.getListByUser.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFAFAFA))
    ) {
        GKRToolbar(
            title = "GKR",
            navigateToMain = navigateToMain
        )

        when (val result = equipmentRentalList) {
            UiState.Loading -> {}
            is UiState.Success -> {
                ProfileUserCard(
                    userResult = userResult,
                    rentCount = result.data?.size,
                    profileViewModel = profileViewModel,
                    navigateToSignIn = navigateToSignIn
                )
                RentEquipmentView(equipmentRentalList = result.data)
            }
            UiState.BadRequest -> {}
            UiState.Unauthorized -> {}
            UiState.NotFound -> {
                ProfileUserCard(
                    userResult = userResult,
                    rentCount = 0,
                    profileViewModel = profileViewModel,
                    navigateToSignIn = navigateToSignIn
                )
                RentEquipmentView(equipmentRentalList = emptyList())
            }
            else -> {}
        }
        UserBanHistoryView(violationViewModel = violationViewModel)
    }
}

@Composable
fun ColumnScope.ProfileUserCard(
    userResult: UiState<UserResponseModel?>?,
    rentCount: Int?,
    profileViewModel: ProfileViewModel,
    navigateToSignIn: () -> Unit
) {
    var logoutDialogState by remember { mutableStateOf(false) }
    val logoutState by profileViewModel.logout.observeAsState()
    val snapshot = snapshotFlow { logoutState }

    val scope = rememberCoroutineScope()
    if (logoutDialogState) {
        LogoutDialog(
            onYesButtonClick = {
                scope.launch {
                    profileViewModel.logout()

                    snapshot.collect { state ->
                        when (state) {
                            UiState.Loading -> TODO()
                            is UiState.Success -> navigateToSignIn()
                            UiState.BadRequest -> TODO()
                            UiState.Unauthorized -> TODO()
                            UiState.NotFound -> TODO()
                            else -> {}
                        }
                    }
                }
            },
            onDismissRequest = { logoutDialogState = false }
        )
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
        when (userResult) {
            UiState.Loading -> TODO()
            is UiState.Success -> {
                Image(
                    modifier = Modifier
                        .padding(25.dp)
                        .size(60.dp)
                        .clip(CircleShape),
                    painter = rememberAsyncImagePainter(model = userResult.data?.profileUrl) ?: painterResource(id = R.drawable.ic_user_image),
                    contentDescription = "profile"
                )

                Column {
                    Text(
                        text = userResult.data?.name ?: "null",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.fraunces_black)),
                            color = Color(0xFFC2C2C2),
                            fontSize = 15.sp,
                        )
                    )

                    Text(
                        text = "${userResult.data?.grade}학년 ${userResult.data?.classNum}반 ${userResult.data?.number}번",
                        style = TextStyle(
                            fontFamily = FontFamily(Font(R.font.fraunces_black)),
                            color = Color(0xFFC2C2C2),
                            fontSize = 10.sp
                        )
                    )

                    Text(
                        text = "대여한 물품 ${rentCount}개",
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
            UiState.BadRequest -> TODO()
            UiState.Unauthorized -> TODO()
            UiState.NotFound -> TODO()
            else -> {}
        }
    }
}

@Composable
fun ColumnScope.RentEquipmentView(equipmentRentalList: List<EquipmentResponseModel>?) {
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
            items(equipmentRentalList!!) {
                RentEquipmentItem(data = it)
            }
        }
    }
}

@Composable
fun ColumnScope.UserBanHistoryView(violationViewModel: ViolationViewModel) {
    violationViewModel.getViolationHistory()
    val getHistoryState by violationViewModel.getViolationHistory.observeAsState()

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
            when (val state = getHistoryState) {
                UiState.Loading -> TODO()
                is UiState.Success -> {
                    items(state.data!!) { data ->
                        BanHistoryCard(data = data)
                    }
                }
                UiState.BadRequest -> TODO()
                UiState.Unauthorized -> TODO()
                UiState.NotFound -> TODO()
                else -> {}
            }
        }
    }
}