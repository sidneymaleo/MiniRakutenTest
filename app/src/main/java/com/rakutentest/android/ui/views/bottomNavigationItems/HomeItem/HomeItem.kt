package com.rakutentest.android.ui.views.bottomNavigationItems.HomeItem

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.rakutentest.android.R
import com.rakutentest.android.presentation.viewModel.Product.ProductViewModel
import com.rakutentest.android.ui.UIEvent.Event.ProductEvent
import com.rakutentest.android.ui.UIEvent.UIEvent
import com.rakutentest.android.ui.views.bottomNavigationItems.HomeItem.product.ProductItem
import com.rakutentest.android.ui.views.progressbar.SpinnerCenterVerticalHorizontal
import com.rakutentest.android.ui.views.viewsError.NetworkError
import kotlinx.coroutines.flow.collectLatest


@Composable
fun HomeItem(
    navController: NavHostController,
    productViewModel: ProductViewModel
) {
    //we get our screen state in our viewModel
    val screenState = productViewModel.screenStateProducts.value
    //this variable help use
    // to observe the state of our keyup product search value
    var searchProduct by rememberSaveable { mutableStateOf("Samsung") }
    //we get our application context
    val context = LocalContext.current
    var isRequested = rememberSaveable {
        mutableStateOf(true)
    }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colorScheme.background,
        snackbarHost = { SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.padding(bottom = 80.dp)
        ) }
    ) { paddingValue ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValue)
        ) {
            OutlinedTextField(
                value = searchProduct,
                singleLine = true,
                textStyle = TextStyle(fontSize = 12.sp),
                onValueChange = {
                    searchProduct = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                placeholder = {
                    Text(text = "Rechercher sur Rakuten")
                },
                leadingIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Outlined.Menu,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, bottom = 0.dp, start = 10.dp, end = 10.dp)
                    .height(55.dp),
                shape = RoundedCornerShape(4.dp)
            )

            //we display our spinner if we request our network
            if (screenState.isLoad) {
                SpinnerCenterVerticalHorizontal()
            }

            Row {

                /**
                 * We build the LazyRow
                 * to illustrate a UI as
                 * in the real application
                 */
                LazyColumn(
                    state =  rememberLazyListState(),
                    contentPadding = PaddingValues(
                        top = 20.dp,
                        bottom =  100.dp
                    )
                ) {
                    items(screenState.productList) {product ->
                        ProductItem(
                            navController = navController,
                            product = product
                        )
                    }

                    if (!screenState.isNetworkConnected) {
                        items(count = 1) {
                            NetworkError(
                                title = stringResource(R.string.network_error),
                                iconValue = 0
                            )
                        }
                    } else if (screenState.isNetworkError) {
                        items(count = 1) {
                            NetworkError(
                                title = stringResource(R.string.is_connect_error),
                                iconValue = 1
                            )
                        }
                    } else if (screenState.isInternalError) {
                        items(count = 1) {
                            NetworkError(
                                title = "Internal Error, Error 500",
                                iconValue = 1
                            )
                        }
                    }
                }
            }
        }
    }






    //we observe our network state
    /*if (screenState.isNetworkError) {
        productViewModel.onEvent(ProductEvent.IsNetworkError(context.getString(R.string.is_connect_error)))
    } else */if (!screenState.isNetworkConnected) {
        productViewModel.onEvent(ProductEvent.IsNetworkConnected(context.getString(R.string.is_connect_error)))
    } /*else if (screenState.isInternalError) {
        productViewModel.onEvent(ProductEvent.IsInternalError("Internal Error, Error 500"))
    }*/


    //isrequesred help us to make our http call juste one time
    LaunchedEffect(key1 = isRequested) {
        //we call our api
        productViewModel.onEvent(ProductEvent.GetRemoteProducts(app = context, keyWord = "samsung"))
        isRequested.value = false
    }

    LaunchedEffect(key1 = !screenState.isNetworkConnected) {
        productViewModel.uiEventFlow.collectLatest { event ->
            when (event) {
                is UIEvent.ShowMessage -> {
                    snackbarHostState.showSnackbar(
                        message = event.message,
                        duration = SnackbarDuration.Long,
                        actionLabel = "Réessayer"
                    )
                }

                else -> {}
            }
        }
    }


}