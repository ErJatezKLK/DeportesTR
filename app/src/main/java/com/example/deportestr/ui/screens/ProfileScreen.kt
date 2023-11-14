package com.example.deportestr.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deportestr.R
import com.example.deportestr.navigation.AppScreens
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    goLogin: () -> Unit,
    goHome: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(drawerContent = {
        ModalDrawerSheet {
            DrawerContentProfile(goLogin , goHome) {
                coroutineScope.launch { drawerState.close() }
            }
        }
    }, drawerState = drawerState) {
        Scaffold(topBar = {
            TopBarProfile(onClickDrawer = { coroutineScope.launch { drawerState.open() } })
        },
            ) {
                innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)){
                ProfileContent()
            }

        }

    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarProfile(onClickDrawer: () -> Unit) {
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

@Composable
fun ProfileContent() {
    Divider(
        modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .padding(top = 10.dp), color = Color(0xFF757575)
    )
    BoxWithConstraints(modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding()
        .padding(10.dp)
        .background(Color(0xFF303030))
    ) {
        Surface {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp)) {
                ProfileHeader(
                    this@BoxWithConstraints.maxHeight)
                UserInfo(this@BoxWithConstraints.maxHeight)
            }
        }
    }
}



@Composable
fun ProfileHeader(maxHeight: Dp) {

    Image(
        painter = painterResource(R.drawable.iris),
        contentDescription = "avatar",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(CircleShape)
            .border(2.dp, Color.Gray, CircleShape)
    )
}
@Composable
fun UserInfo(maxHeight: Dp) {
    Column {
        Name(
            modifier = Modifier.height(32.dp)
        )
        Position(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .height(24.dp)
        )
        ProfileProperty(stringResource(R.string.display_name), "Nombre de usuario")

        ProfileProperty(stringResource(R.string.email), "big.boss@dd.com")
        Spacer(Modifier.height((320.dp).coerceAtLeast(0.dp)))
    }
}
@Composable
private fun Name(modifier: Modifier = Modifier) {
    Text(
        text = "Nombre de usuario",
        modifier = modifier,
        style = MaterialTheme.typography.headlineSmall
    )
}

@Composable
private fun Position(modifier: Modifier = Modifier) {
    Text(
        text = "Contenido del perfil",
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
fun ProfileProperty(label: String, value: String, isLink: Boolean = false) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider()
        Text(
            text = label,
            modifier = Modifier.height(24.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        val style = if (isLink) {
            MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary)
        } else {
            MaterialTheme.typography.bodyLarge
        }
        Text(
            text = value,
            modifier = Modifier.height(24.dp),
            style = style
        )
    }
}

@Composable
fun DrawerContentProfile(
    goLogin: () -> Unit,
    goHome: () -> Unit,
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
