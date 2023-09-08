package com.rakutentest.android.ui.views.bottomNavigationItems.HomeItem.product

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.rakutentest.android.data.model.dataRemote.response.Product
import com.rakutentest.android.presentation.viewModel.Product.ProductViewModel

//In this UI we build our Row Card
@Composable
fun ProductItem(
    navController: NavHostController,
    product: Product,
    productViewModel: ProductViewModel
) {

    Row {
        Text( text = "Testing !! Testing !!")
    }

}