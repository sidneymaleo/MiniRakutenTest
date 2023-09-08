package com.rakutentest.android.ui.views.bottomNavigationItems.HomeItem.product

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.google.android.material.shape.CornerSize
import com.rakutentest.android.BuildConfig
import com.rakutentest.android.data.model.dataRemote.response.Product
import com.rakutentest.android.presentation.viewModel.Product.ProductViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp


@Composable
fun getProductImage(image: String): Painter {
    return rememberAsyncImagePainter(image)
}

/**
 * this method helps us
 * to retrieve the decimal
 * part of our notation
 */
fun getDecimalPart(value: Double) {

}

//In this UI we build our Row Card
@Composable
fun ProductItem(
    navController: NavHostController,
    product: Product,
    productViewModel: ProductViewModel
) {


    Box(Modifier.fillMaxSize()) {

        Box(
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            IconButton(onClick = { /* doSomething() */ }, modifier = Modifier
                .padding(bottom = 10.dp)
                .wrapContentWidth()
                .wrapContentHeight()) {
                Icon(
                    Icons.Outlined.FavoriteBorder,
                    contentDescription = "Localized description",
                    modifier = Modifier
                        .padding(bottom = 20.dp)
                        .wrapContentWidth()
                        .wrapContentHeight()
                )
            }
        }

        Box( modifier = Modifier.align(Alignment.BottomCenter)) {
            Column {

                Row {
                    //we test if we have a image to catch exception
                    if (product.imagesUrls.isNotEmpty()) {
                        Image(
                            painter = getProductImage(product.imagesUrls[0]),
                            contentDescription = "Profile picture description",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(top = 20.dp, bottom = 20.dp, start = 10.dp, end = 10.dp)
                                .height(100.dp)
                                .width(100.dp)
                        )
                    }


                    Column {
                        Row {
                            Text(
                                text = product.headline,
                                style = MaterialTheme.typography.titleSmall,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 35.dp)
                            )


                        }

                        Text(
                            text = "Samsung - Téléphone Samsung Galaxy...",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 35.dp),
                            fontSize = 10.sp

                        )

                        Row {
                            IconButton(onClick = { /* doSomething() */ }, modifier = Modifier
                                .padding(bottom = 10.dp)
                                .wrapContentWidth()
                                .wrapContentHeight()) {
                                Icon(
                                    Icons.Outlined.Star,
                                    contentDescription = "Localized description",
                                    modifier = Modifier
                                        .padding(bottom = 20.dp)
                                        .wrapContentWidth()
                                        .wrapContentHeight()
                                )
                            }
                        }

                    }
                }

                Row {
                    Divider(
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(top = 15.dp, bottom = 15.dp)
                            .fillMaxWidth()
                            .height(0.20.dp),
                    )
                }
            }
        }

    }

}