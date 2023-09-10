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
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.rakutentest.android.R
import com.rakutentest.android.data.model.dataRemote.response.Buybox
import com.rakutentest.android.data.model.dataRemote.response.Product
import com.rakutentest.android.presentation.util.isNetworkAvailable
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

    var isNetworkFailed = rememberSaveable {
        mutableStateOf(true)
    }

    val snackbarHostState = remember { SnackbarHostState() }

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
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
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

                    /*if (!screenState.isNetworkConnected) {
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
                    }*/
                }
            }
        }
    }






    //we observe our network state
    if (screenState.isNetworkError) {
        productViewModel.onEvent(ProductEvent.IsNetworkError(context.getString(R.string.is_connect_error)))
    } else if (!screenState.isNetworkConnected) {
        productViewModel.onEvent(ProductEvent.IsNetworkConnected(context.getString(R.string.is_connect_error)))
    } else if (screenState.isInternalError) {
        productViewModel.onEvent(ProductEvent.IsInternalError("Internal Error, Error 500"))
    }


    //isrequesred help us to make our http call juste one time
    LaunchedEffect(key1 = isRequested) {
        //we call our api
        productViewModel.onEvent(ProductEvent.GetRemoteProducts(app = context, keyWord = "samsung"))
        isRequested.value = false
    }

    /**
     * we test if we are offline to load our sqlite database datas
     */
    if (!isNetworkAvailable(context)) {
        //we load our locale datas
        LaunchedEffect(key1 = isNetworkFailed) {


            //we get our product list
            productViewModel.getProductList().observe(context as LifecycleOwner) {productListRoom->

                // Before we clean our screen state product list
                screenState.productList.removeAll(screenState.productList)

                productListRoom.forEach { productRoom ->
                    productRoom.id?.let {
                        // We get our buy box in the database
                        productViewModel.getBuyBox(it).observe(context as LifecycleOwner) { buyBox->
                            // After we load our cache data in our list
                            screenState.productList.add(
                                Product(
                                    id = productRoom.id!!,
                                    newBestPrice = productRoom.newBestPrice,
                                    usedBestPrice =  productRoom.usedBestPrice,
                                    headline = productRoom.headline,
                                    reviewsAverageNote = productRoom.reviewsAverageNote,
                                    nbReviews = productRoom.nbReviews,
                                    categoryRef = productRoom.categoryRef,
                                    imagesUrls = productRoom.imagesUrls,
                                    buybox = Buybox(
                                        salePrice = buyBox.salePrice,
                                        advertType = buyBox.advertType,
                                        advertQuality = buyBox.advertQuality,
                                        saleCrossedPrice = buyBox.saleCrossedPrice,
                                        salePercentDiscount = buyBox.salePercentDiscount,
                                        isRefurbished = buyBox.isRefurbished
                                    )
                                )
                            )
                        }
                    }

                }
            }

            //we close our block
            isNetworkFailed.value = false
        }



        LaunchedEffect(key1 = !screenState.isNetworkConnected) {
            productViewModel.uiEventFlow.collectLatest { event ->
                when (event) {
                    is UIEvent.ShowMessage -> {
                        snackbarHostState.showSnackbar(
                            message = event.message,
                            duration = SnackbarDuration.Long
                        )
                    }

                    else -> {}
                }
            }
        }
    }



}