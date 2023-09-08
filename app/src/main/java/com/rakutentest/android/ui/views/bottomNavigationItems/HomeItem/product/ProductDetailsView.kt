package com.rakutentest.android.ui.views.bottomNavigationItems.HomeItem.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rakutentest.android.R
import com.rakutentest.android.ui.views.bottomNavigationItems.AccountItem
import com.rakutentest.android.ui.views.bottomNavigationItems.BasketItem
import com.rakutentest.android.ui.views.bottomNavigationItems.CashBackItem
import com.rakutentest.android.ui.views.bottomNavigationItems.HomeItem.HomeItem
import com.rakutentest.android.ui.views.bottomNavigationItems.SellItem

@Composable
fun ProductDetailsView(){

    Scaffold(
        topBar = {
            Scaffold(
                topBar = {
                    //we build our home top bar
                    TopAppBar(
                        backgroundColor = MaterialTheme.colorScheme.surface,
                        navigationIcon = {
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(
                                    Icons.Outlined.ArrowBack,
                                    contentDescription = "Localized description"
                                )
                            }
                        },
                        title = {

                        },
                        actions = {
                            // RowScope here, so these icons will be placed horizontally
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(
                                    Icons.Outlined.Share,
                                    contentDescription = "Localized description"
                                )
                            }

                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(
                                    Icons.Outlined.Search,
                                    contentDescription = "Localized description"
                                )
                            }

                        },
                        //we make elevation to zero because we want the plate view
                        elevation = 0.dp
                    )

                }) { innerPadding ->

                Column(
                    modifier = Modifier.padding(innerPadding)
                ) {

                }
            }
        },
        bottomBar = {

        },
        floatingActionButton = {},
        content = { paddingValue -> }
    )

}