package com.rakutentest.android.ui.views.bottomNavigationItems.HomeItem.product

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.TabRow
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.rakutentest.android.R
import com.rakutentest.android.data.model.dataRemote.response.enums.ProductImageSizeEnum
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

    var state by remember { mutableIntStateOf(0) }
    //It's a title of our tabs
    val titles = listOf("Description", "Commentaire du vendeur", "Avis")

    var tabTextValue = rememberSaveable { mutableStateOf("") }

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
                                    contentDescription = null
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
                                    contentDescription = null
                                )
                            }

                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(
                                    Icons.Outlined.Search,
                                    contentDescription = null
                                )
                            }

                        },
                        //we make elevation to zero because we want the plate view
                        elevation = 0.dp
                    )

                }) { innerPadding ->

                //we test if product details is not null after
                // to display our ui
                if (screenState.productDetails !== null) {
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {

                        //we display our spinner if we request our network
                        if (screenState.isLoad) {
                            SpinnerCenterVerticalHorizontal()
                        }

                        //we test if our productDetails is not null
                        if (screenState.productDetails !== null) {
                            //we we test the product details images size
                            if (screenState.productDetails?.images!!.isNotEmpty()) {
                                //here we call our corousel
                                CarouselProductImage(
                                    itemsCount = screenState.productDetails?.images!!.size,
                                    itemContent = { index ->

                                        Column(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {

                                            Image(
                                                //here we call getProductImage
                                                painter = getProductImage(
                                                    image = screenState.productDetails?.images!![index].imagesUrls.entry.filter {
                                                        /**
                                                         * here I'm just testing the original
                                                         * because I don't have enough time to
                                                         * manage all the screen sizes to use this image
                                                         * rendering api optimizer for display depending on the screen
                                                         */
                                                        it.size == ProductImageSizeEnum.ORIGINAL.name
                                                    }[0].url
                                                ),
                                                contentDescription = null,
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier.height(200.dp)
                                            )

                                        }
                                    }
                                )

                            }
                        }

                        //we add our add the divider
                        Row {
                            Divider(
                                color = Color.Gray,
                                modifier = Modifier
                                    .padding(top = 15.dp, bottom = 15.dp)
                                    .fillMaxWidth()
                                    .height(0.20.dp),
                            )
                        }

                        // here build our headline block
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "${screenState.productDetails!!.headline}",
                                modifier = Modifier.padding(top = 2.dp, start = 20.dp, end = 20.dp),
                                fontSize = 15.sp
                            )
                        }


                        Row(
                            modifier = Modifier
                                .padding(start = 20.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Column {
                                //We display our ProductStar
                                ProductStarHandle(
                                    score = screenState.productDetails!!.globalRating.score,
                                    nbReviews = screenState.productDetails!!.globalRating.nbReviews
                                )

                                //Here we display our product Price
                                ProductPrice(
                                    advertType = screenState.productDetails!!.quality,
                                    newBestPrice = screenState.productDetails!!.newBestPrice,
                                    usedBestPrice = screenState.productDetails!!.usedBestPrice
                                )
                            }

                            Spacer(modifier = Modifier.size(20.dp))
                            Row(
                                modifier = Modifier.padding(5.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                OutlinedButton(
                                    onClick = { },
                                    shape = CircleShape,
                                    border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.5f))
                                ) {
                                    Icon(
                                        Icons.Outlined.FavoriteBorder,
                                        contentDescription = null,
                                        modifier = Modifier
                                    )
                                }

                                Spacer(modifier = Modifier.size(5.dp))

                                OutlinedButton(
                                    onClick = { },
                                    shape = CircleShape,
                                    border = BorderStroke(1.dp, Color.Gray.copy(alpha = 0.5f))
                                ) {
                                    Icon(
                                        Icons.Outlined.NotificationsNone,
                                        contentDescription = null,
                                        modifier = Modifier
                                    )
                                }
                            }
                        }

                        Row {
                            Divider(
                                color = Color.Gray,
                                modifier = Modifier
                                    .padding(top = 15.dp, bottom = 0.dp)
                                    .fillMaxWidth()
                                    .height(0.20.dp),
                            )
                        }

                        Column {
                            TabRow(
                                selectedTabIndex = state,
                                contentColor = Color.Black,
                                backgroundColor = MaterialTheme.colorScheme.background
                            ) {
                                titles.forEachIndexed { index, title ->
                                    Tab(
                                        selected = state == index,
                                        onClick = { state = index },
                                        text = {
                                            Text(
                                                text = title,
                                                maxLines = 1,
                                                fontSize = 12.sp
                                            )
                                        }
                                    )
                                }
                            }

                            // Here we display our text after we click the text
                            when (state) {
                                0 -> {
                                    tabTextValue.value = screenState.productDetails!!.description
                                }
                                1 -> {
                                    tabTextValue.value = screenState.productDetails!!.sellerComment
                                }
                            }

                            if (listOf(0,1).contains(state)) {
                                // fromHtml help us to translate our html code
                                val spannedText = HtmlCompat.fromHtml(tabTextValue.value, 0)
                                Row {
                                    Text(
                                        text =  spannedText.toString(),
                                        style = MaterialTheme.typography.bodyLarge,
                                        modifier = Modifier.padding(top = 10.dp, start = 5.dp, end = 5.dp)
                                    )
                                }
                            }


                        }

                    }
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
    selectedColor: Color = colorResource(R.color.GrayLight),
    //We decrease the opacity of our gray to make it lighter
    unSelectedColor: Color = Color.Gray.copy(alpha = 0.5f)
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
    /**
     * We make it to max value because we want to fix our slideDuration
     * While making sure to make it automatic again if we change our minds
     */
    autoSlideDuration: Long = Long.MAX_VALUE,
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
            color = Color.Transparent
        ) {
            DotsIndicator(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                totalDots = itemsCount,
                selectedIndex = if (isDragged) pagerState.currentPage else pagerState.targetPage
            )
        }
    }

}