package com.example.deportestr.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormulaScreen(
    goLogin: NavHostController,
    goHome: NavHostController
){
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(drawerContent = {
        ModalDrawerSheet {
            DrawerContentFormula(goLogin , goHome) {
                coroutineScope.launch { drawerState.close() }
            }
        }
    }, drawerState = drawerState) {
        Scaffold(topBar = {
            TopBarFormula(onClickDrawer = { coroutineScope.launch { drawerState.open() } })
        }
        ) {


        }

    }
}

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
