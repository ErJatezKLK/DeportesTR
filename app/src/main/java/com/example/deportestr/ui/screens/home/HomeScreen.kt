package com.example.deportestr.ui.screens.home

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.deportestr.R
import com.example.deportestr.ui.models.Sport
import com.example.deportestr.ui.models.User
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * funcion que carga la pantalla de home
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    goLogin: () -> Unit,
    goProfile: (String) -> Unit,
    goFormula: (String) -> Unit,
    goFootball: (String) -> Unit,
    goTenis: (String) -> Unit,
    goMotoGp: (String) -> Unit,
    goBasket: (String) -> Unit,
    goWrc: (String) -> Unit,
    email: String,
    viewModel: SportsViewModel = hiltViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val user = viewModel.user
    val sports = viewModel.sports
    //Este launched effect al cargar la pantalla cambia unit y hace la busqueda del usuario para el drawer
    //Y carga la informacion de los deportes
    LaunchedEffect(Unit) {
        viewModel.loadInfo(email)
        while (user == null || sports == null) {
            delay(100)
        }
    }

    //Un drawer el cual deslizas en la pantalla y sale un desplegable de la izquierda con la informacion del usuario
    if (user != null && sports != null) {
        ModalNavigationDrawer(drawerContent = {
            ModalDrawerSheet {
                DrawerContent(user, goLogin = goLogin, goProfile = goProfile) {
                    coroutineScope.launch { drawerState.close() }
                }
            }
        }, drawerState = drawerState) {
            Scaffold(topBar = {
                TopBarContent(onClickDrawer = { coroutineScope.launch { drawerState.open() } })
            }) { innerPadding -> //innerPadding para que se separe de la barra de arriba en caso de no ponerlo se susperpone
                Box(modifier = Modifier.padding(innerPadding)) {
                    HomeBody(goFootball, goFormula, goTenis, goMotoGp, goBasket, goWrc, sports, user)
                }
            }
        }
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeBody(
    goFootball: (String) -> Unit,
    goFormula: (String) -> Unit,
    goTenis: (String) -> Unit,
    goMotoGp: (String) -> Unit,
    goBasket: (String) -> Unit,
    goWrc: (String) -> Unit,
    sports: List<Sport>,
    user: User
) {
    if (user != null) {
        var selectedTabIndex by remember {
            mutableIntStateOf(0 )
        }
        val pagerState = rememberPagerState {
            sports.size
        }

        //Links metidos a mano por que no tenemos api externa gratuita que nos aporte una feed de noticias
        val context = LocalContext.current
        val soyMotorNews = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://soymotor.com/f1/noticias/la-fia-prohibe-las-pruebas-aerodinamicas-para-desarrollar-los-monoplazas-de-2026")) }
        val footballNews = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mundodeportivo.com/futbol/internacional/20231208/1002149782/hijo-leo-messi-mateo-viral-gol-inter-miami.html")) }
        val tenisNews = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.atptour.com/es/news/best-of-2023-rivalries-djokovic-alcaraz")) }
        val motoGPNews = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://es.motorsport.com/motogp/news/trackhouse-propiedad-estadounidense-rnf-aprilia-motogp-2024/10555185/")) }
        val basketNews = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mundodeportivo.com/baloncesto/nba/20231208/1002149578/brutal-racha-triplista-lebron-james-inicio-paliza-pelicans.html")) }
        val wrcNews = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://lat.motorsport.com/wrc/news/wrc-cambia-sistema-puntos-temporada-2024/10555680/")) }
        //Estos launched effect son para los tabs o pestaña
        //El primero hace que se vea cual esta seleccionado
        //El segundo al pulsar va a la pestaña seleccionada
        LaunchedEffect(selectedTabIndex) {
            pagerState.animateScrollToPage(selectedTabIndex)
        }
        LaunchedEffect(pagerState.currentPage) {
            selectedTabIndex = pagerState.currentPage
        }
        //Este ScrollableTabRow es para que las pestañas o tabs salgan en forma de fila
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
            //El HorizontalPager para que se vean en horizontal las cards o cartas de presentacion
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
                                .clickable { goFootball(user.email!!) }
                                .fillMaxWidth(),
                            shape = MaterialTheme.shapes.small
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.futbol),
                                    contentDescription = null,
                                    Modifier.fillMaxWidth(),
                                )
                                Row(
                                    modifier = Modifier.align(Alignment.Start)
                                ) {
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
                                Card (
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    shape = MaterialTheme.shapes.small
                                ){
                                    Text(
                                        text = "El hijo de Leo Messi, Mateo, se hace viral por su gol con el Inter Miami",
                                        fontSize = 30.sp,
                                        modifier = Modifier.padding(2.dp),
                                        fontWeight = FontWeight.Bold
                                    )
                                    Row {
                                        Button(
                                            onClick = {  context.startActivity(footballNews) },
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically)
                                                .fillMaxWidth()
                                        ) {
                                            Text(text = stringResource(id = R.string.go_to_web))
                                        }
                                    }
                                }
                            }
                        }

                        1 -> Card(
                            modifier = Modifier
                                .clickable { goFormula(user.email!!) }
                                .fillMaxWidth(),
                            shape = MaterialTheme.shapes.small
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.f_uno),
                                    contentDescription = null,
                                    Modifier.fillMaxWidth(),
                                )
                                Row(
                                    modifier = Modifier.align(Alignment.Start)
                                ) {
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
                            Card (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                shape = MaterialTheme.shapes.small
                            ){
                                Text(
                                    text = "La FIA prohíbe las pruebas aerodinámicas para desarrollar los monoplazas de 2026",
                                    fontSize = 30.sp,
                                    modifier = Modifier.padding(2.dp),
                                    fontWeight = FontWeight.Bold
                                )
                                Row {
                                    Button(
                                        onClick = {  context.startActivity(soyMotorNews) },
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                            .fillMaxWidth()
                                    ) {
                                        Text(text = stringResource(id = R.string.go_to_web))
                                    }
                                }
                            }
                        }

                        2 -> Card(
                            modifier = Modifier
                                .clickable { goTenis(user.email!!) }
                                .fillMaxWidth(),
                            shape = MaterialTheme.shapes.small
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.tenis),
                                    contentDescription = null,
                                    Modifier.fillMaxWidth(),
                                )
                                Row(
                                    modifier = Modifier.align(Alignment.Start)
                                ) {
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
                                Card (
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    shape = MaterialTheme.shapes.small
                                ){
                                    Text(
                                        text = "Rivalidades De 2023: Djokovic vs. Alcaraz",
                                        fontSize = 30.sp,
                                        modifier = Modifier.padding(2.dp),
                                        fontWeight = FontWeight.Bold
                                    )
                                    Row {
                                        Button(
                                            onClick = { context.startActivity(tenisNews) },
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically)
                                                .fillMaxWidth()
                                        ) {
                                            Text(text = stringResource(id = R.string.go_to_web))
                                        }
                                    }
                                }
                            }
                        }

                        3 -> Card(
                            modifier = Modifier
                                .clickable { goMotoGp(user.email!!) }
                                .fillMaxWidth(),
                            shape = MaterialTheme.shapes.small
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.motogp),
                                    contentDescription = null,
                                    Modifier.fillMaxWidth(),
                                )
                                Row(
                                    modifier = Modifier.align(Alignment.Start)
                                ) {
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
                                Card (
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    shape = MaterialTheme.shapes.small
                                ){
                                    Text(
                                        text = "Capital estadounidense se hará cargo de las plazas de RNF en MotoGP 2024",
                                        fontSize = 30.sp,
                                        modifier = Modifier.padding(2.dp),
                                        fontWeight = FontWeight.Bold
                                    )
                                    Row {
                                        Button(
                                            onClick = { context.startActivity(motoGPNews) },
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically)
                                                .fillMaxWidth()
                                        ) {
                                            Text(text = stringResource(id = R.string.go_to_web))
                                        }
                                    }
                                }
                            }
                        }

                        4 -> Card(
                            modifier = Modifier
                                .clickable { goBasket(user.email!!) }
                                .fillMaxWidth(),
                            shape = MaterialTheme.shapes.small
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.baloncesto),
                                    contentDescription = null,
                                    Modifier.fillMaxWidth(),
                                )
                                Row(
                                    modifier = Modifier.align(Alignment.Start)
                                ) {
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
                                Card (
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    shape = MaterialTheme.shapes.small
                                ){
                                    Text(
                                        text = "La brutal racha triplista de LeBron James que inició la paliza a los Pelicansw",
                                        fontSize = 30.sp,
                                        modifier = Modifier.padding(2.dp),
                                        fontWeight = FontWeight.Bold
                                    )
                                    Row {
                                        Button(
                                            onClick = { context.startActivity(basketNews) },
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically)
                                                .fillMaxWidth()
                                        ) {
                                            Text(text = stringResource(id = R.string.go_to_web))
                                        }
                                    }
                                }
                            }
                        }

                        5 -> Card(
                            modifier = Modifier
                                .clickable { goWrc(user.email!!) }
                                .fillMaxWidth(),
                            shape = MaterialTheme.shapes.small
                        ) {
                            Column {
                                Image(
                                    painter = painterResource(id = R.drawable.wrc),
                                    contentDescription = null,
                                    Modifier.fillMaxWidth()
                                )
                                Row(
                                    modifier = Modifier.align(Alignment.Start)
                                ) {
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
                                Card (
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    shape = MaterialTheme.shapes.small
                                ){
                                    Text(
                                        text = "El WRC adoptará un nuevo sistema de puntos para mejorar los rallyes",
                                        fontSize = 30.sp,
                                        modifier = Modifier.padding(2.dp),
                                        fontWeight = FontWeight.Bold
                                    )
                                    Row {
                                        Button(
                                            onClick = { context.startActivity(wrcNews) },
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically)
                                                .fillMaxWidth()
                                        ) {
                                            Text(text = stringResource(id = R.string.go_to_web))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

//Contenido de la barra superior
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
//Contenido del drawer con la informacion del usuario y la navegacion a la pantalla de usuario
@Composable
fun DrawerContent(
    user: User?,
    goLogin: () -> Unit,
    goProfile: (String) -> Unit,
    onCloseDrawer: () -> Job
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
                .clickable { goProfile(user.email!!) }
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
}