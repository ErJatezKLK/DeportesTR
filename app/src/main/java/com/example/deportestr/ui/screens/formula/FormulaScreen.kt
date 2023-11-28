package com.example.deportestr.ui.screens.formula

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.deportestr.R
import com.example.deportestr.ui.models.SportEvent
import com.example.deportestr.ui.models.Team
import com.example.deportestr.ui.models.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormulaScreen(
    goLogin: () -> Unit,
    goHome: () -> Unit,
    email: String,
    viewModel: FormulaViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val user = viewModel.user
    val teams = viewModel.teams
    val events = viewModel.events

    LaunchedEffect(Unit) {
        viewModel.loadInfo(email)
        while (user == null || teams == null || events == null) {
            delay(200)
        }
    }

    if (user != null) {
        ModalNavigationDrawer(drawerContent = {
            ModalDrawerSheet {
                DrawerContentFormula(user, goLogin = goLogin, goHome = goHome) {
                    coroutineScope.launch { drawerState.close() }
                }
            }
        }, drawerState = drawerState) {
            Scaffold(topBar = {
                TopBarFormula(onClickDrawer = { coroutineScope.launch { drawerState.open() } })
            }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .background(Color(0xFF303030))
                ) {
                    FormulaContent(teams, events)
                }
            }
        }
    }
}

@Composable
fun FormulaContent(teams: List<Team>?, events: List<SportEvent>?) {
    if (events != null) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
            items(events) { sportEvent ->
                ItemEvent(sportEvent = sportEvent, teams = teams)
            }
        })
    }
}

@Composable
fun ItemEvent(sportEvent: SportEvent?, teams: List<Team>?) {
    if (sportEvent != null || teams != null) {
        if (sportEvent != null) {
            Card(
                modifier = Modifier
                    .clickable { }
                    .fillMaxWidth()
                    .background(Color(0xFFAD0000))
                    .padding(8.dp),
                shape = MaterialTheme.shapes.small
            ) {
                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    Text(
                        text = "",
                        fontSize = 23.sp,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = sportEvent.location,
                        fontSize = 23.sp,
                        modifier = Modifier.padding(2.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
                Column {
                    if (teams != null) {
                        Text(
                            text = teams[0].name,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(2.dp)
                        )
                        Text(
                            text = teams[1].name,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(2.dp)
                        )
                        Text(
                            text = teams[2].name,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(2.dp)
                        )
                        Text(
                            text = teams[3].name,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(2.dp)
                        )
                        Text(
                            text = teams[4].name,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(2.dp)
                        )
                        Text(
                            text = teams[5].name,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(2.dp)
                        )
                        Text(
                            text = teams[6].name,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(2.dp)
                        )
                        Text(
                            text = teams[7].name,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(2.dp)
                        )
                        Text(
                            text = teams[8].name,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(2.dp)
                        )
                        Text(
                            text = teams[9].name,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(2.dp)
                        )
                    }
                }
            }
        }
    }
}
/*
fun sportEvents(): List<SportEvent> {
    val date = Timestamp(2023 - 11 - 5)
    val aston =
        Team(1, "Fernando PADRONSO", creationDate = date, "Vota al Brexit", Sport(2, "Formula 1"))
    val redBull =
        Team(2, "Verstappen y Marko", creationDate = date, "Austria", Sport(2, "Formula 1"))
    val mercedes =
        Team(3, "Mercedes", creationDate = date, "Tambien vota al brexit", Sport(2, "Formula 1"))
    return listOf(
        SportEvent(1, Timestamp(18-11-2023), "Macs Visparten gana","Mexico" ),
        SportEvent(2, Timestamp(18-11-2023), "MAGIIIC", "Brasil"),
        SportEvent(3, Timestamp(18-11-2023), "Macs Trampuchero", "Las vegas"),
        SportEvent(4, Timestamp(18-11-2023), "Goatifi", "Yas marina")
    )
}


fun teamsList(): List<Team> {
    val date = Timestamp(2023 - 11 - 5)

    return listOf(
        Team(1, "La Mision???", creationDate = date, "Vota al Brexit", Sport(2, "Formula 1")),
        Team(2, "Bed Rull", creationDate = date, "Austria", Sport(2, "Formula 1")),
        Team(3, "Votan por el brexit", creationDate = date, "Tambien vota al brexit", Sport(2, "Formula 1")),
        Team(4, "Votan por el brexit y van bien", creationDate = date, "Lo mismo que mercedes", Sport(2, "Formula 1")),
        Team(
            5,
            "Los putos gabachos",
            creationDate = date,
            "No deberia existir",
            Sport(2, "Formula 1")
        ),
        Team(6, "Strotegy", creationDate = date, "Pasta Boys", Sport(2, "Formula 1")),
        Team(7, "Toro Alfa", creationDate = date, "Austria supongo", Sport(2, "Formula 1")),
        Team(8, "Votan por el brexit y son lentos", creationDate = date, "No se", Sport(2, "Formula 1")),
        Team(
            9,
            "WTF IS A KILOMETER *gun shots and a chopper*",
            creationDate = date,
            "",
            Sport(2, "Formula 1")
        ),
        Team(10, "Alfa Bromeo", creationDate = date, "Pasta boys 2", Sport(2, "Formula 1"))
    )
}
*/

@Composable
fun DrawerContentFormula(
    user: User,
    goLogin: () -> Unit,
    goHome: () -> Unit,
    onCloseDrawer: () -> Unit
) {
    if (user != null) {
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
                    Text(text = user.name)
                    Text(text = user.email)
                }
            }
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth(), color = Color(0xFF757575)
            )
            Row(modifier = Modifier
                .clickable { goHome() }
                .fillMaxWidth()
            ) {
                Text(text = "Deportes", fontSize = 25.sp)
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarFormula(onClickDrawer: () -> Unit) {
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
        colors = TopAppBarDefaults.largeTopAppBarColors(Color.Red),
    )
}
