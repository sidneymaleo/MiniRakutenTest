package com.rakutentest.android.ui

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.yambasama.ui.theme.RakutenTheme
import com.rakutentest.android.ui.views.HomeApp
import com.rakutentest.android.ui.views.LaunchView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.rakutentest.android.ui.views.model.Route

class MainActivity : ComponentActivity() {
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

    private fun initViewModel() {

    }

    /***
     * This method initialize our view navigation
     */
    @Composable
    fun MainView(navController: NavHostController) {

        val activity = (LocalContext.current as? Activity)

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
                HomeApp(navController = navController)
                //this instruction help the user to exit of the app
                //after he press the back button
                BackHandler {
                    activity?.finish()
                }
            }
        }
    }

}
