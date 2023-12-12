package com.example.deportestr.ui.screens.football

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
import java.text.SimpleDateFormat

/**
 * funcion que carga el drawer y la interfaz
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FootballScreen(
    goLogin: () -> Unit,
    goHome: (String) -> Unit,
    email: String,
    viewModel: FootballViewModel = hiltViewModel(),
    goInfoTeam: (Int) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val user = viewModel.user
    val teams = viewModel.teams
    val events = viewModel.events
    //Este launched effect al cargar la pantalla cambia unit y hace la busqueda del usuario para el drawer
    //Y carga la informacion de los equipos y de los eventos/partidos
    LaunchedEffect(Unit) {
        viewModel.loadInfo(email)
        while (user == null || teams == null || events == null) {
            delay(200)
        }
    }
    //Un drawer el cual deslizas en la pantalla y sale un desplegable de la izquierda con la informacion del usuario
    if (user != null) {
        ModalNavigationDrawer(drawerContent = {
            ModalDrawerSheet {
                DrawerContentFootball(user, goLogin = goLogin, goHome = goHome) {
                    coroutineScope.launch { drawerState.close() }
                }
            }
        }, drawerState = drawerState) {
            Scaffold(topBar = {
                TopBarFootball(onClickDrawer = { coroutineScope.launch { drawerState.open() } })
            }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .background(Color(0xFF303030))
                ) {
                    FootballContent(teams, events, goInfoTeam)
                }
            }
        }
    }
}
//El contenido de la pantalla con los resultados se crean 1 item por cada evento deportivo
@Composable
fun FootballContent(teams: List<Team>?, events: List<SportEvent>?, goInfoTeam: (Int) -> Unit) {
    if (events != null) {
        LazyVerticalGrid(columns = GridCells.Fixed(1), content = {
            items(events) { sportEvent ->
                ItemFootball(sportEvent = sportEvent, teams = teams, events, goInfoTeam)
            }
        })
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun ItemFootball(
    sportEvent: SportEvent,
    teams: List<Team>?,
    events: List<SportEvent>,
    goInfoTeam: (Int) -> Unit
) {
    val timestamp = sportEvent.date
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val formattedDate = dateFormat.format(timestamp)
    //Al no tener api externa hay que añadirle al evento los equipos que participen practicamente a mano
    //Todos los eventos y los deportes vienen de la base de datos
    when (sportEvent.id) {
        25 -> Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFAD0000))
                .padding(8.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "$formattedDate",
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
                            .clickable { goInfoTeam(teams[0].id!!) }
                    )
                    Text(
                        text = teams[10].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                            .clickable { goInfoTeam(teams[10].id!!) }
                    )
                }
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = sportEvent.result,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        26 -> Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFAD0000))
                .padding(8.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "$formattedDate",
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Column {
                if (teams != null) {
                    Text(
                        text = teams[16].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                            .clickable { goInfoTeam(teams[16].id!!) }
                    )
                    Text(
                        text = teams[5].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                            .clickable { goInfoTeam(teams[5].id!!) }
                    )
                }
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = sportEvent.result,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        27 -> Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFAD0000))
                .padding(8.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "$formattedDate",
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Column {
                if (teams != null) {
                    Text(
                        text = teams[12].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                            .clickable { goInfoTeam(teams[12].id!!) }
                    )
                    Text(
                        text = teams[17].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                            .clickable { goInfoTeam(teams[17].id!!) }
                    )
                }
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = sportEvent.result,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        28 -> Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFAD0000))
                .padding(8.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "$formattedDate",
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Column {
                if (teams != null) {
                    Text(
                        text = teams[9].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                            .clickable { goInfoTeam(teams[9].id!!) }
                    )
                    Text(
                        text = teams[19].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                            .clickable { goInfoTeam(teams[19].id!!) }
                    )
                }
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = sportEvent.result,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        29 -> Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFAD0000))
                .padding(8.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "$formattedDate",
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Column {
                if (teams != null) {
                    Text(
                        text = teams[6].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                            .clickable { goInfoTeam(teams[6].id!!) }
                    )
                    Text(
                        text = teams[17].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                            .clickable { goInfoTeam(teams[17].id!!) }
                    )
                }
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = sportEvent.result,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        30 -> Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFAD0000))
                .padding(8.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "$formattedDate",
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Column {
                if (teams != null) {
                    Text(
                        text = teams[4].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                            .clickable { goInfoTeam(teams[4].id!!) }
                    )
                    Text(
                        text = teams[15].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                            .clickable { goInfoTeam(teams[15].id!!) }
                    )
                }
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = sportEvent.result,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        31 -> Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFAD0000))
                .padding(8.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "$formattedDate",
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Column {
                if (teams != null) {
                    Text(
                        text = teams[3].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                            .clickable { goInfoTeam(teams[3].id!!) }
                    )
                    Text(
                        text = teams[9].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                            .clickable { goInfoTeam(teams[9].id!!) }
                    )
                }
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = sportEvent.result,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        32 -> Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFAD0000))
                .padding(8.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "$formattedDate",
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
            Column {
                if (teams != null) {
                    Text(
                        text = teams[9].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                            .clickable { goInfoTeam(teams[9].id!!) }
                    )
                    Text(
                        text = teams[18].name,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(2.dp)
                            .clickable { goInfoTeam(teams[18].id!!) }
                    )
                }
            }
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = sportEvent.result,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
//El contenido del drawer que contiene los datos del usuario y navega hacia atras en este caso a la pantalla de home
@Composable
fun DrawerContentFootball(
    user: User?,
    goLogin: () -> Unit,
    goHome: (String) -> Unit,
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
                    Text(text = user.name!!)
                    Text(text = user.email!!)
                }
            }
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth(), color = Color(0xFF757575)
            )
            Row(modifier = Modifier
                .clickable { goHome(user.email!!) }
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
//Contenido de la barra superior
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarFootball(onClickDrawer: () -> Unit) {
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