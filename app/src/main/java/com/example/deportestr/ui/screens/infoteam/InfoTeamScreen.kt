package com.example.deportestr.ui.screens.infoteam

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.deportestr.R
import com.example.deportestr.ui.models.Athlete
import com.example.deportestr.ui.models.Team
import kotlinx.coroutines.delay

/**
 * funcion que carga el drawer y la interfaz
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoTeamScreen(
    goBack: () -> Unit,
    teamId: Int,
    viewModel: InfoTeamViewModel = hiltViewModel()
) {
    /**
     * Pantalla con la informacion del equipo
     */
    val team = viewModel.team
    val athletes = viewModel.athletes

    LaunchedEffect(Unit) {
        viewModel.loadInfo(teamId)
        while (team == null || athletes == null) {
            delay(200)
        }
    }
    if (athletes != null) {
        ModalNavigationDrawer(drawerContent = {}) {
            Scaffold(topBar = {
                TopBarInfo(goBack)
            }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .background(Color(0xFF303030))
                ) {
                    TeamInfoContent(team, athletes)
                }
            }
        }
    }
}

/**
 * Contenido de la pantalla carga la info del equipo y sus jugadores
 */
@Composable
fun TeamInfoContent(team: Team?, athletes: List<Athlete>?) {
    if (athletes != null) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFAD0000))
                .padding(8.dp),
            shape = MaterialTheme.shapes.small
        ) {
            Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "Equipo: " + team!!.name,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Equipo: " + team.country,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(2.dp),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        LazyVerticalGrid(columns = GridCells.Fixed(1), content = {
            items(athletes) { athlete ->
                ItemInfoTeam(athlete)
            }
        })
    }
}

/**
 * Se crea un item por cada jugadore que tenga el equipo
 */
@Composable
fun ItemInfoTeam(athlete: Athlete) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFAD0000))
            .padding(8.dp),
        shape = MaterialTheme.shapes.small
    ) {
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                text = "Nombre: " + athlete.name + " " + athlete.surname,
                fontSize = 23.sp,
                modifier = Modifier.padding(2.dp),
                fontWeight = FontWeight.Bold
            )
        }
        Column {
            Text(
                text = "Posicion: " + athlete.position,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(2.dp)
            )
            Text(
                text = "Edad: " + athlete.age,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(2.dp)
            )
            Text(
                text = "Nacionalidad: " + athlete.nationality,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(2.dp)
            )
        }
        Column {
            Text(
                text = "Apodo: " + athlete.nickname,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(2.dp)
            )
            Text(
                text = "Titulos: " + athlete.titles,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(2.dp)
            )
        }
    }
}

/**
 * Barra superior con su contenido
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarInfo(goBack: () -> Unit) {
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
            IconButton(onClick = { goBack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                )
            }
        },
        colors = TopAppBarDefaults.largeTopAppBarColors(Color.Red),
    )
}
