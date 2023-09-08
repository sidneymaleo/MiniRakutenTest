package com.rakutentest.android.ui.views.bottomNavigationItems.HomeItem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.rakutentest.android.R
import com.rakutentest.android.presentation.viewModel.Product.ProductViewModel
import com.rakutentest.android.ui.views.bottomNavigationItems.HomeItem.product.ProductItem
import com.rakutentest.android.ui.views.progressbar.SpinnerCenterVerticalHorizontal


@Composable
fun HomeItem(
    navController: NavHostController,
    productViewModel: ProductViewModel
) {
    //we get our screen state in our viewModel
    val screenState = productViewModel.screenStateProducts.value


    /**
     * We build the LazyRow
     * to illustrate a UI as
     * in the real application
     */
    LazyRow(state =  rememberLazyListState()) {
        items(screenState.productList) {product ->
            ProductItem(
                navController = navController,
                product = product,
                productViewModel = productViewModel
            )
        }

        //we display our spinner if we request our network
        if (screenState.isLoad) {
            items(count = 1) {
                SpinnerCenterVerticalHorizontal()
            }
        }

    }
}