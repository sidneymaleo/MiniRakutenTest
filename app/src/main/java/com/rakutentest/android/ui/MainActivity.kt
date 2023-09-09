package com.rakutentest.android.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.yambasama.ui.theme.RakutenTheme
import com.rakutentest.android.presentation.viewModel.BuyBox.BuyBoxViewModel
import com.rakutentest.android.presentation.viewModel.BuyBox.BuyBoxViewModelFactory
import com.rakutentest.android.presentation.viewModel.Product.ProductViewModel
import com.rakutentest.android.presentation.viewModel.Product.ProductViewModelFactory
import com.rakutentest.android.ui.views.HomeApp
import com.rakutentest.android.ui.views.LaunchView
import com.rakutentest.android.ui.views.bottomNavigationItems.HomeItem.product.ProductDetailsView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.rakutentest.android.ui.views.model.Route
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // Here we inject our view model factory
    @Inject
    lateinit var productFactory: ProductViewModelFactory
    @Inject
    lateinit var buyBoxFactory: BuyBoxViewModelFactory

    private lateinit var productViewModel: ProductViewModel
    private lateinit var buyBoxViewModel: BuyBoxViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RakutenTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    //we initialize our MainView
                    MainView(navController)
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(200)
                        navController.navigate(Route.homeView)
                    }
                }
            }
        }
    }

    //in this method we provide our viewModel
    private fun initViewModel() {
        productViewModel = ViewModelProvider(owner = this, productFactory)[ProductViewModel::class.java]
        buyBoxViewModel = ViewModelProvider(owner = this, buyBoxFactory)[BuyBoxViewModel::class.java]
    }

    /***
     * This method initialize our view navigation
     */
    @Composable
    fun MainView(navController: NavHostController) {

        val activity = (LocalContext.current as? Activity)

        //We call our init view model method
        this.initViewModel()

        NavHost(navController = navController, startDestination = Route.launchView) {
            //This is our launch view navigation initialize
            composable(route = Route.launchView) {
                LaunchView()
                BackHandler {
                    activity?.finish()
                }
            }

            //This is our home view navigation initialize
            composable(
                route = Route.homeView
            ) {
                HomeApp(
                    navController = navController,
                    productViewModel = productViewModel
                )
                //this instruction help the user to exit of the app
                //after he press the back button
                BackHandler {
                    activity?.finish()
                }
            }

            //This is our home view navigation initialize
            composable(
                route = Route.productDetailsView
            ) {
                ProductDetailsView(
                    navController = navController,
                    productViewModel = productViewModel
                )
            }
        }
    }

}

