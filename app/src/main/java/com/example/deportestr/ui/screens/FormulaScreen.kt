package com.example.deportestr.ui.screens

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.deportestr.R
import com.example.deportestr.navigation.AppScreens
import com.example.deportestr.ui.models.Sport
import com.example.deportestr.ui.models.SportEvent
import com.example.deportestr.ui.models.Team
import com.example.deportestr.ui.models.User
import kotlinx.coroutines.launch
import java.sql.Timestamp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormulaScreen(
    goLogin: NavHostController,
    goHome: NavHostController
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val user = User(1, "Big.Boss", "big.boss@dd.com", "Esta", 0, R.drawable.iris)


    ModalNavigationDrawer(drawerContent = {
        ModalDrawerSheet {
            DrawerContentFormula(goLogin, goHome) {
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
                FormulaContent(user)
            }

        }

    }
}

@Composable
fun FormulaContent(user: User) {
    LazyVerticalGrid(columns = GridCells.Fixed(1), content = {
        items(sportEvents()){ sportEvent ->
            ItemEvent(sportEvent = sportEvent, teams = teamsList())

        }

    })


}



@Composable
fun ItemEvent(sportEvent: SportEvent, teams: List<Team>) {

    Card(
        modifier = Modifier
            .clickable { }
            .fillMaxWidth()
            .background(Color(0xFF303030)),
        shape = MaterialTheme.shapes.small
    ) {
        Column {
            Row(modifier = Modifier.align(Alignment.Start)) {
                Text(
                    text = "",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(2.dp)
                )
                Text(
                    text = "",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(2.dp)
                )
                Text(
                        text = sportEvent.localTeam.name,
                fontSize = 20.sp,
                modifier = Modifier.padding(2.dp)
                )


            }
        }
    }
}

fun sportEvents(): List<SportEvent> {
    val date = Timestamp(2023-11-5)
    val aston = Team(1, "Fernando PADRONSO", creationDate = date,"Vota al Brexit", Sport(2,"Formula 1"))
    val redBull = Team(2, "Verstappen y Marko", creationDate = date,"Austria", Sport(2,"Formula 1"))
    val mercedes = Team(3, "Mercedes", creationDate = date,"Tambien vota al brexit", Sport(2,"Formula 1"))
    return listOf(
        SportEvent(1, aston, redBull,"Gana Macs Visparten", "Mexico"),
        SportEvent(2, aston, redBull,"3er EL VIEJO SABROSO", "Brasil"),
        SportEvent(3, aston, redBull,"Max a la carcel por trampuchero", "Las vegas"),
        SportEvent(4, aston, redBull,"Goatifi", "Yas marina")
    )
}

fun teamsList(): List<Team> {
    val date = Timestamp(2023-11-5)

    return listOf(
        Team(1, "Aston Martin", creationDate = date,"Vota al Brexit", Sport(2,"Formula 1")),
        Team(2, "Bed Rull", creationDate = date,"Austria", Sport(2,"Formula 1")),
        Team(3, "Mercedes", creationDate = date,"Tambien vota al brexit", Sport(2,"Formula 1")),
        Team(4, "Mclaren", creationDate = date,"Lo mismo que mercedes", Sport(2,"Formula 1")),
        Team(5, "Los putos gabachos", creationDate = date,"No deberia existir", Sport(2,"Formula 1")),
        Team(6, "Ferrari", creationDate = date,"Pasta Boys", Sport(2,"Formula 1")),
        Team(7, "Alphatauri", creationDate = date,"Austria supongo", Sport(2,"Formula 1")),
        Team(8, "Williams", creationDate = date,"No se", Sport(2,"Formula 1")),
        Team(9, "Haas", creationDate = date,"WTF IS A KILOMETER *gun shots and a chopper*", Sport(2,"Formula 1")),
        Team(10, "Alfa romeo", creationDate = date,"Pasta boys 2", Sport(2,"Formula 1"))
    )
}


/*
@Composable
fun MessageCard(userMessages: UserMessages, user: User) {
    var isExpanded by remember { mutableStateOf(false) }
    val surfaceColor by animateColorAsState(
        if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        label = ""
    )
    Row(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(id = user.photo),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = userMessages.author,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 2.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp),
        ) {
        Text(
            text = userMessages.body,
            modifier = Modifier.padding(4.dp),
            style = MaterialTheme.typography.bodyMedium,
            maxLines = if (isExpanded) Int.MAX_VALUE else 1
        )
    }
    }
}
}

 */

@Composable
fun DrawerContentFormula(
    goLogin: NavHostController,
    goHome: NavHostController,
    onCloseDrawer: () -> Unit
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
                Text(text = "Big Boss")
                Text(text = "@big_boss.oh")
            }
        }
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(), color = Color(0xFF757575)
        )
        Row(modifier = Modifier
            .clickable { goHome.navigate(route = AppScreens.HomeScreen.route) }
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
            .clickable { goLogin.navigate(route = AppScreens.LoginScreen.route) }
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
