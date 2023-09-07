package com.rakutentest.android.ui.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AllInclusive
import androidx.compose.material.icons.outlined.EuroSymbol
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ManageAccounts
import androidx.compose.material.icons.outlined.ShoppingBasket
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rakutentest.android.R
import com.rakutentest.android.ui.views.bottomNavigationItems.AccountItem
import com.rakutentest.android.ui.views.bottomNavigationItems.BasketItem
import com.rakutentest.android.ui.views.bottomNavigationItems.CashBackItem
import com.rakutentest.android.ui.views.bottomNavigationItems.HomeItem
import com.rakutentest.android.ui.views.bottomNavigationItems.SellItem
import com.rakutentest.android.ui.views.model.BottomNavigationItem
import com.rakutentest.android.ui.views.model.Route


@Composable
fun HomeApp(navController: NavHostController) {

    //In this list we initialize our bottom navigation items
    val bottomNavigationItems = listOf(
        BottomNavigationItem(
            Icons.Outlined.Home,
            stringResource(R.string.home),
            Route.homeTabView
        ),
        BottomNavigationItem(
            Icons.Outlined.AllInclusive,
            stringResource(R.string.cash_back),
            Route.cashBackTabView
        ),
        BottomNavigationItem(
            Icons.Outlined.ShoppingCart,
            stringResource(R.string.basket),
            Route.basketTabView
        ),
        BottomNavigationItem(
            Icons.Outlined.EuroSymbol,
            stringResource(R.string.sell),
            Route.sellTabView
        ),
        BottomNavigationItem(
            Icons.Outlined.ManageAccounts,
            stringResource(R.string.account),
            Route.accountTabView
        ),
    )

    //this variable save the state of the bottom navigation current item
    var selectedItem = remember { mutableIntStateOf(0) }

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
                                Icon(
                                    Icons.Outlined.FavoriteBorder,
                                    contentDescription = "Localized description"
                                )
                            }
                        },
                        //we make elevation to zero because we want the plate view
                        elevation = 0.dp
                    )

                }) { innerPadding ->

                Column(modifier = Modifier.padding(innerPadding)) {

                    // here we initialize our content home app views
                    when (selectedItem.intValue) {
                        0 -> {
                            HomeItem()
                        }

                        1 -> {
                            CashBackItem()
                        }

                        2 -> {
                            BasketItem()
                        }

                        3 -> {
                            SellItem()
                        }

                        else -> {
                            AccountItem()
                        }
                    }
                }
            }

        },
        bottomBar = {
            NavigationBar {
                bottomNavigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                item.id,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                remember { item.title })
                        },
                        selected = selectedItem.intValue == index,
                        onClick = {
                            selectedItem.intValue = index
                            Log.d("TestingMaleoMaleo", " displaying ${selectedItem.intValue}")
                            /*switch.value = index != 1*/
                        }
                    )
                }
            }
        }, floatingActionButton = {},
        content = { paddingValue -> })
}

