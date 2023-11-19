package com.example.deportestr.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.deportestr.R
import com.example.deportestr.ui.models.Sport
import com.example.deportestr.ui.models.User
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    goLogin: () -> Unit,
    goProfile: () -> Unit,
    goFormula: () -> Unit,
    goFootball: () -> Unit,
    goTenis: () -> Unit,
    goMotoGp: () -> Unit,
    goBasket: () -> Unit,
    goWrc: () -> Unit,
    email: String,
    viewModel: SportsViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    LaunchedEffect(viewModel.userLoaded) {
        if (!viewModel.userLoaded) {
            viewModel.email = email
            viewModel.loadUser()
        }
    }

    val searchUser = viewModel.user

    ModalNavigationDrawer(drawerContent = {
        ModalDrawerSheet {
            DrawerContent(searchUser, goLogin = goLogin, goProfile = goProfile) {
                coroutineScope.launch { drawerState.close() }
            }
        }
    }, drawerState = drawerState) {
        Scaffold(topBar = {
            TopBarContent(onClickDrawer = { coroutineScope.launch { drawerState.open() } })
        }) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                HomeBody(goFootball, goFormula, goTenis, goMotoGp, goBasket, goWrc)
            }
        }
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeBody(
    goFootball: () -> Unit,
    goFormula: () -> Unit,
    goTenis: () -> Unit,
    goMotoGp: () -> Unit,
    goBasket: () -> Unit,
    goWrc: () -> Unit
) {
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    val sports = getSports()
    val pagerState = rememberPagerState {
        sports.size
    }
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }

    Column {
        ScrollableTabRow(selectedTabIndex = selectedTabIndex) {
            sports.forEachIndexed { index, sport ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = sport.name) }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            Box(modifier = Modifier.fillMaxSize()) {

                when (index) {
                    0 -> Card(
                        modifier = Modifier
                            .clickable { goFootball() }
                            .fillMaxWidth()
                            .background(Color(0xFF303030)),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.futbol),
                                contentDescription = null,
                                Modifier.fillMaxWidth(),
                            )
                            Row(modifier = Modifier.align(Alignment.Start)) {
                                Text(
                                    text = sports[index].name,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(2.dp)
                                )
                                Icon(
                                    imageVector = Icons.Filled.NavigateNext,
                                    contentDescription = null
                                )
                            }
                        }
                    }

                    1 -> Card(
                        modifier = Modifier
                            .clickable { goFormula() }
                            .fillMaxWidth()
                            .background(Color(0xFF303030)),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.f_uno),
                                contentDescription = null,
                                Modifier.fillMaxWidth(),
                            )
                            Row(modifier = Modifier.align(Alignment.Start)) {
                                Text(
                                    text = sports[index].name,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(2.dp)
                                )
                                Icon(
                                    imageVector = Icons.Filled.NavigateNext,
                                    contentDescription = null
                                )
                            }
                        }
                    }

                    2 -> Card(
                        modifier = Modifier
                            .clickable { goTenis() }
                            .fillMaxWidth()
                            .background(Color(0xFF303030)),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.tenis),
                                contentDescription = null,
                                Modifier.fillMaxWidth(),
                            )
                            Row(modifier = Modifier.align(Alignment.Start)) {
                                Text(
                                    text = sports[index].name,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(2.dp)
                                )
                                Icon(
                                    imageVector = Icons.Filled.NavigateNext,
                                    contentDescription = null
                                )
                            }
                        }
                    }

                    3 -> Card(
                        modifier = Modifier
                            .clickable { goMotoGp() }
                            .fillMaxWidth()
                            .background(Color(0xFF303030)),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.motogp),
                                contentDescription = null,
                                Modifier.fillMaxWidth(),
                            )
                            Row(modifier = Modifier.align(Alignment.Start)) {
                                Text(
                                    text = sports[index].name,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(2.dp)
                                )
                                Icon(
                                    imageVector = Icons.Filled.NavigateNext,
                                    contentDescription = null
                                )
                            }
                        }
                    }

                    4 -> Card(
                        modifier = Modifier
                            .clickable { goBasket() }
                            .fillMaxWidth()
                            .background(Color(0xFF303030)),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.baloncesto),
                                contentDescription = null,
                                Modifier.fillMaxWidth(),
                            )
                            Row(modifier = Modifier.align(Alignment.Start)) {
                                Text(
                                    text = sports[index].name,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(2.dp)
                                )
                                Icon(
                                    imageVector = Icons.Filled.NavigateNext,
                                    contentDescription = null
                                )
                            }
                        }
                    }

                    5 -> Card(
                        modifier = Modifier
                            .clickable { goWrc() }
                            .fillMaxWidth()
                            .background(Color(0xFF303030)),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Column {
                            Image(
                                painter = painterResource(id = R.drawable.wrc),
                                contentDescription = null,
                                Modifier.fillMaxWidth()
                            )
                            Row(modifier = Modifier.align(Alignment.Start)) {
                                Text(
                                    text = sports[index].name,
                                    fontSize = 20.sp,
                                    modifier = Modifier.padding(2.dp)
                                )
                                Icon(
                                    imageVector = Icons.Filled.NavigateNext,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
fun getSports(): List<Sport> {
    return listOf(
        Sport(1, "Futbol"),
        Sport(2, "Formula 1"),
        Sport(3, "Tenis"),
        Sport(4, "MotoGP"),
        Sport(5, "Baloncesto"),
        Sport(6, "WRC")
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarContent(onClickDrawer: () -> Unit) {
    TopAppBar(

        title = {
            Image(
                painter = painterResource(id = R.drawable.logo_bueno),
                contentDescription = null,
                modifier = Modifier
                    .size(175.dp)
            )
        },
        actions = {
            IconButton(onClick = { onClickDrawer() }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                )
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(Color.Red)
    )
}

@Composable
fun DrawerContent(
    user: User?,
    goLogin: () -> Unit,
    goProfile: () -> Unit,
    onCloseDrawer: () -> Job
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = null,
                modifier = Modifier
                    .padding(6.dp)
                    .clickable { onCloseDrawer() }
                    .size(100.dp)
            )
            Column {
                if (user != null) {
                    Text(text = user.name)
                    Text(text = user.email)
                }
            }
        }
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(), color = Color(0xFF757575)
        )
        Row(modifier = Modifier
            .clickable { goProfile() }
            .fillMaxWidth()
        ) {
            Text(text = "Ir a mi perfil", fontSize = 25.sp)
            Icon(
                imageVector = Icons.Filled.NavigateNext,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(35.dp)
            )
        }
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(), color = Color(0xFF757575)
        )
        Row(modifier = Modifier
            .clickable { goLogin() }
            .fillMaxWidth()

        ) {
            Text(text = "Cerrar session", color = Color(0xFFC70606), fontSize = 25.sp)
            Icon(
                imageVector = Icons.Filled.ExitToApp,
                contentDescription = null,
                tint = Color(0xFFC70606),
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(35.dp)
            )
        }
    }
}