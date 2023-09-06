package com.rakutentest.android.ui.views

import androidx.compose.foundation.Image
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController



import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rakutentest.android.R


@Composable
fun HomeApp(navController: NavHostController) {

    Scaffold(
        topBar = {
            Scaffold(
                topBar = {
                    //we build our home top bar
                    TopAppBar(
                        backgroundColor = MaterialTheme.colorScheme.surface,
                        navigationIcon = {
                            Image(
                                modifier = Modifier.padding(start = 5.dp),
                                painter = painterResource(id = R.drawable.rakuten_label),
                                contentDescription = "The Application Launcher"
                            )
                        },
                        title = {

                        },
                        actions = {
                            // RowScope here, so these icons will be placed horizontally
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Localized description")
                            }
                        },
                        //we make elevation to zero because we want the plate view
                        elevation = 0.dp
                    )

                }) { innerPadding -> }

        },
        bottomBar = {}, floatingActionButton = {}, content = {})
}

