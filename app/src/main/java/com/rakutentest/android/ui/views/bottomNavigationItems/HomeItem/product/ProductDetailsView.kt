package com.rakutentest.android.ui.views.bottomNavigationItems.HomeItem.product

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.rakutentest.android.R
import com.rakutentest.android.presentation.viewModel.Product.ProductViewModel
import com.rakutentest.android.ui.UIEvent.Event.ProductEvent
import com.rakutentest.android.ui.views.progressbar.SpinnerCenterVerticalHorizontal
import kotlinx.coroutines.delay

@Composable
fun ProductDetailsView(
    navController: NavHostController,
    productViewModel: ProductViewModel
) {

    //we get our screen state in our viewModel
    val screenState = productViewModel.screenStateProductDetails.value
    //we get our application context
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Scaffold(
                topBar = {
                    //we build our home top bar
                    TopAppBar(
                        backgroundColor = MaterialTheme.colorScheme.surface,
                        navigationIcon = {
                            IconButton(onClick = {
                                navController.navigateUp()
                            }) {
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

                    //we display our spinner if we request our network
                    if (screenState.isLoad) {
                        SpinnerCenterVerticalHorizontal()
                    }

                    }*/
                    //here we call our corousel
                    CarouselProductImage(
                        itemsCount = screenState.productDetails?.images!!.size,
                        itemContent = { index ->

                            Image(
                                painter = getProductImage(
                                    screenState.productDetails?.images!![index].imagesUrls.entry.filter {
                                        /**
                                         * here I'm just testing the original
                                         * because I don't have enough time to
                                         * manage all the screen sizes to use this image
                                         * rendering api optimizer for display depending on the screen
                                         */
                                        it.size === "ORIGINAL"
                                    }[0].url
                                ),
                                contentDescription = "Profile picture description",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .padding(top = 20.dp, bottom = 20.dp, start = 5.dp, end = 5.dp)
                                    .height(100.dp)
                                    .width(100.dp)
                            )
                        }
                    )
                }
            }
        },
        bottomBar = {

        },
        floatingActionButton = {},
        content = { paddingValue -> }
    )

    LaunchedEffect(key1 = screenState.isRequested) {
        //we call our api
        productViewModel.onEvent(
            ProductEvent.GetRemoteProductDetails(
                app = context,
                id = 6035914280
            )
        )
    }

}

//Simple circle with size and color properties…
@Composable
fun IndicatorDot(
    modifier: Modifier = Modifier,
    size: Dp,
    color: Color
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )
}

// Basically a list of dots with padding between them…
@Composable
fun DotsIndicator(
    modifier: Modifier = Modifier,
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color = Color.Yellow /* Color.Yellow */,
    unSelectedColor: Color = Color.Gray  /* Color.Gray */
) {
    LazyRow(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        items(totalDots) { index ->
            IndicatorDot(
                color = if (index == selectedIndex) selectedColor else unSelectedColor,
                size = 8.dp
            )

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}


//here we build our corousel layout
@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun CarouselProductImage(
    modifier: Modifier = Modifier,
    autoSlideDuration: Long = 10,
    itemsCount: Int,
    itemContent: @Composable (index: Int) -> Unit,
) {

    val pagerState = rememberPagerState(pageCount = { itemsCount })

    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()



    LaunchedEffect(pagerState.currentPage) {
        delay(autoSlideDuration)
        pagerState.animateScrollToPage((pagerState.currentPage + 1) % itemsCount)
    }

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        HorizontalPager(state = pagerState) { page ->
            itemContent(page)
        }

        Surface(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.BottomCenter),
            shape = CircleShape,
            color = Color.Black.copy(alpha = 0.5f)
        ) {
            DotsIndicator(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                totalDots = itemsCount,
                selectedIndex = if (isDragged) pagerState.currentPage else pagerState.targetPage
            )
        }
    }

}