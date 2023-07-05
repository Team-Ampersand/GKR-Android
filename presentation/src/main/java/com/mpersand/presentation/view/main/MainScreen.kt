package com.mpersand.presentation.view.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.DrawerValue
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.mpersand.domain.model.equipment.response.EquipmentResponseModel
import com.mpersand.presentation.R
import com.mpersand.presentation.util.UiState
import com.mpersand.presentation.view.main.component.GKRFilterItem
import com.mpersand.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
    navigateToDetail: (productNumber: String) -> Unit,
    navigateToProfile: () -> Unit,
    navigateToSearch: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getAllEquipments()
    }
    
    val uiState by viewModel.getAllEquipmentsUiState.observeAsState()

    when (val state = uiState) {
        UiState.BadRequest -> TODO()
        UiState.Loading -> TODO()
        UiState.NotFound -> TODO()
        is UiState.Success -> {
            Surface(
                modifier = modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                ModalDrawerScreen(
                    equipments = state.data!!,
                    navigateToDetail = navigateToDetail,
                    navigateToProfile = navigateToProfile
                )
                viewModel.getAllEquipments()
            }
        }
        UiState.Unauthorized -> TODO()
        UiState.Unknown -> TODO()
        else -> {}
    }
}

@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    openModalDrawer: () -> Unit
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
            modifier = modifier.clickable {
                openModalDrawer()
            },
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
}

@Composable
fun ListItems(
    modifier: Modifier = Modifier,
    equipment: EquipmentResponseModel,
) {
    Column {
        Text(
            modifier = modifier.padding(top = 10.dp),
            text = equipment.name,
            fontSize = 10.sp,
            fontWeight = FontWeight.Thin,
            fontFamily = FontFamily(Font(R.font.fraunces_black))
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
            text = when (equipment.name) {
                "맥북" -> { "#맥북  #노트북" }
                "갤럭시 북" -> { "#갤럭시 북  #노트북" }
                "터치모니터" -> { "#터치모니터  #모니터" }
                else -> { "#?" }
            },
            fontSize = 7.sp,
            fontWeight = FontWeight.Thin,
            fontFamily = FontFamily(Font(R.font.fraunces_black)),
            color = Color(0xFFC0C0C0),
        )
        Spacer(modifier = modifier.height(10.dp))
        Divider(
            modifier = modifier.fillMaxWidth(),
            color = Color.Gray,
            thickness = 1.dp
        )
    }
}

@Composable
fun ModalDrawerScreen(
    modifier: Modifier = Modifier,
    equipments: List<EquipmentResponseModel>,
    navigateToDetail: (productNumber: String) -> Unit,
    navigateToProfile: () -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItem by remember { mutableStateOf(0) }

    ModalDrawer(
        drawerState = drawerState,
        drawerContent = {
            Spacer(modifier = modifier.height(10.dp))
            Column(
                modifier = modifier.padding(horizontal = 15.dp)
            ) {
                Image(
                    modifier = modifier.size(30.dp, 30.dp),
                    painter = painterResource(id = R.drawable.ic_logo), // rememberAsyncImagePainter(imageUri.value)
                    contentDescription = "image",
                    contentScale = ContentScale.Crop
                )
                Text(
                    modifier = modifier.padding(top = 10.dp),
                    text = "박성현",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Thin,
                    fontFamily = FontFamily(Font(R.font.fraunces_black))
                )
                Text(
                    text = "3학년 2반 8번",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Thin,
                    fontFamily = FontFamily(Font(R.font.fraunces_black))
                )
                Spacer(modifier = modifier.height(10.dp))
                Divider(
                    modifier = modifier.fillMaxWidth(),
                    color = Color.Gray,
                    thickness = 1.dp
                )
                Spacer(modifier = modifier.height(10.dp))
                val content = listOf("Main Page", "Profile Page", "Rental Page")
                val resourceId =
                    listOf(R.drawable.ic_folder, R.drawable.ic_profile, R.drawable.ic_handshake)

                repeat(3) {
                    DrawerItem(
                        selected = it == selectedItem,
                        content = content[it],
                        image = {
                            Image(
                                modifier = modifier.size(20.dp, 20.dp),
                                painter = painterResource(id = resourceId[it]),
                                contentDescription = "image",
                                contentScale = ContentScale.Crop,
                                colorFilter = ColorFilter.tint(
                                    if (selectedItem == it) Color(0xFFFF6000) else Color(0xFF999999)
                                )
                            )
                        }
                    ) {
                        selectedItem = it

                        if (it == 1) navigateToProfile()

                        scope.launch {
                            drawerState.close()
                        }
                    }
                    Spacer(modifier = modifier.height(15.dp))
                }
            }
        },
        content = {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(horizontal = 25.dp)
            ) {
                AppBar(openModalDrawer = {
                    scope.launch {
                        drawerState.open()
                    }
                })

                var filterSelectState by remember { mutableStateOf(0) }
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    val list = listOf("전체", "맥북", "갤럭시 북", "터치모니터")

                    itemsIndexed(list) { index, item ->
                        GKRFilterItem(
                            title = item,
                            select = index == filterSelectState
                        ) {
                            filterSelectState = index
                        }
                    }
                }

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(equipments) {
                        Row(
                            modifier = modifier
                                .fillMaxWidth()
                                .clickable {
                                    navigateToDetail(it.productNumber)
                                }
                        ) {
                            Image(
                                modifier = modifier.size(120.dp, 90.dp),
                                painter = rememberAsyncImagePainter(it.image),
                                contentDescription = "image",
                                contentScale = ContentScale.Crop
                            )
                            Spacer(modifier = modifier.width(10.dp))
                            ListItems(equipment = it)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun DrawerItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    content: String,
    image: @Composable () -> Unit,
    onClickItem: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClickItem() }
            .background(
                color = if (selected) Color(0xFFFFE6C7) else Color.Transparent,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(12.dp)
    ) {
        image()
        Spacer(modifier = modifier.width(10.dp))
        Text(
            modifier = modifier.padding(top = 2.dp),
            text = content,
            fontSize = 10.sp,
            fontWeight = FontWeight.Thin,
            fontFamily = FontFamily(Font(R.font.fraunces_black)),
            color = if (selected) Color(0xFFFF6000) else Color(0xFF999999)
        )
    }
}
