package com.david.tot.ui.settings



import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.david.tot.ui.theme.TotTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.david.tot.ui.consumible.*


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
/*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

 */

import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.david.tot.ui.DrawerContent
import com.david.tot.ui.authenticable.AuthenticableViewModel
//import com.yeslab.fastprefs.FastPrefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TotTheme(darkTheme = false) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val scaffoldState = rememberScaffoldState()
                    val coroutineScope = rememberCoroutineScope()
                    val contextForToast = LocalContext.current.applicationContext
                    val settingsViewModel = viewModel<SettingsViewModel>()
                    val authenticableViewModel = viewModel<AuthenticableViewModel>()
                    //val contextArticleActivity = this@ArticleActivity

                    //Bottom nav controller
                    val navController = rememberNavController()
                    //val recipeViewModel = viewModel<RecipeViewModel>()
                    //NavigationHost(recipeViewModel = recipeViewModel)
                    //var ordeModalWindowCUrrentState by rememberSaveable { mutableStateOf(false) }
                    //val recipeViewModel = viewModel<RecipeViewModel>()
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        scaffoldState = scaffoldState,
                        topBar = {
                            TopAppBarSettingsAcivity(this@SettingsActivity,settingsViewModel) {
                                coroutineScope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        },
                        bottomBar = { BottomNavigationBar(navController) },
                        content = { padding ->
                            Box(modifier = Modifier.padding(padding)) {
                                SettingsNavigationBotomMenu(this@SettingsActivity,settingsViewModel,authenticableViewModel,navController = navController)
                            }
                        },
                        drawerContent = {
                            DrawerContent(context=this@SettingsActivity) { itemLabel ->
                                Toast
                                    .makeText(contextForToast, itemLabel, Toast.LENGTH_SHORT)
                                    .show()
                                coroutineScope.launch {
                                    // delay for the ripple effect
                                    delay(timeMillis = 250)
                                    scaffoldState.drawerState.close()
                                }
                            }
                        }
                    )
                    /*{
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                        }
                    }

                     */
                }
            }
        }
    }
    /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val articleViewModel = viewModel<ArticleViewModel>()
            val drugsDeliveryConsumerViewHeaderViewModel = viewModel<DrugsDeliveryConsumerViewHeaderViewModel>()
            val contextArticleActivity = this@ArticleActivity
            MainScreen(articleViewModel,drugsDeliveryConsumerViewHeaderViewModel,contextArticleActivity)
        }
    }
    */
}


/*
@Composable
fun MainScreen(articleViewModel:ArticleViewModel,drugsDeliveryConsumerViewHeaderViewModel:DrugsDeliveryConsumerViewHeaderViewModel,contextArticleActivity:ArticleActivity) {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val contextForToast = LocalContext.current.applicationContext
    Scaffold(
        topBar = {
            TopAppBar {
                coroutineScope.launch {
                    scaffoldState.drawerState.open()
                }
            }
        },
        drawerContent = {
            DrawerContent(contextArticleActivity) { itemLabel ->
                Toast
                    .makeText(contextForToast, itemLabel, Toast.LENGTH_SHORT)
                    .show()
                coroutineScope.launch {
                    // delay for the ripple effect
                    delay(timeMillis = 250)
                    scaffoldState.drawerState.close()
                }
            }

        },
        bottomBar = { BottomNavigationBar(navController) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(articleViewModel,drugsDeliveryConsumerViewHeaderViewModel,navController = navController)
            }
        },
        //backgroundColor = colorResource(R.color.colorPrimaryDark) // Set background color to avoid the white flashing when you switch between screens
    )
}
*/



@Composable
fun TopAppBarSettingsAcivity(nContext:SettingsActivity, settingsViewModel: SettingsViewModel, onNavIconClick: () -> Unit) {
    val mContext = LocalContext.current.applicationContext
    TopAppBar(
        title = { Text(text = "GLAPP") },
        navigationIcon = {
            IconButton(
                onClick = {
                    onNavIconClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Open Navigation Drawer"
                )
            }
        },
        actions = {
            IconButton(onClick = { /* doSomething() */

                //settingsViewModel.saveSettingsListToSync()
                /*
                var dataList = mutableListOf(Consumible(0, 1,"",1,"UND","2023-08-08T00:48:12.104Z",0))

                val prefs = FastPrefs(mContext)
                val consumibleGuardado = prefs.get(articleViewModel.key.toString(),dataList)
                if(articleViewModel.saveArticleListToSync(mContext)==1){
                    Toast.makeText(mContext,"Consumible creado exitosamente"+consumibleGuardado!!.size, Toast.LENGTH_SHORT).show()
                    nContext.startActivity(Intent(nContext,ArticleActivity::class.java))
                }else{
                    Toast.makeText(mContext,"No hay suficiente cantidad en inventario de algunos productos seleccionados" +consumibleGuardado!!.size, Toast.LENGTH_SHORT).show()
                }

                 */
                Log.e("TAG","TAGTAG")
            }) {
                Icon(
                    imageVector = Icons.Filled.Save,
                    contentDescription = "Localized description"
                )
            }
        },
        backgroundColor = Color(0xFF22475b),
        contentColor = Color.White
    )
}

/*
@Composable
fun MainScreenPreview(articleViewModel:ArticleViewModel,drugsDeliveryConsumerViewHeaderViewModel: DrugsDeliveryConsumerViewHeaderViewModel) {
    MainScreen(articleViewModel, drugsDeliveryConsumerViewHeaderViewModeln)
}
*/

@Composable
fun SettingsNavigationBotomMenu(contextActivity:SettingsActivity, settingsViewModel:SettingsViewModel, authenticableViewModel: AuthenticableViewModel, navController: NavHostController) {

    NavHost(navController, startDestination = SettingsBotomNavigationItem.Home.route) {
        composable(SettingsBotomNavigationItem.Home.route) {
            ArticleHeaderAndBodyScreen(contextActivity,settingsViewModel,authenticableViewModel)
        }
        composable(SettingsBotomNavigationItem.Music.route) {
            MusicScreen()
        }
        composable(SettingsBotomNavigationItem.Movies.route) {
            MoviesScreen()
        }
        composable(SettingsBotomNavigationItem.Books.route) {
            BooksScreen()
        }
        composable(SettingsBotomNavigationItem.Profile.route) {
            ProfileScreen()
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        //title = { Text(text = stringResource(R.string.app_name), fontSize = 18.sp) },
        title = { Text(text = "GLAPP", fontSize = 18.sp) },
        //backgroundColor = colorResource(id = R.color.colorPrimary),
        backgroundColor = Color.Gray,
        contentColor = Color.White
    )
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}

@Composable
fun BottomNavigationBar(navController: NavController) {

    val Purple200 = Color(0xFF7baf4a)

    val items = listOf(
        SettingsBotomNavigationItem.Home,
        SettingsBotomNavigationItem.Music,
        SettingsBotomNavigationItem.Movies,
        SettingsBotomNavigationItem.Books,
        SettingsBotomNavigationItem.Profile
    )
    BottomNavigation(
        //backgroundColor = Color.Gray,
        backgroundColor= Color(0xFF22475b),
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    // BottomNavigationBar()
}