package com.david.tot.ui.sync

import android.annotation.SuppressLint
import android.os.Bundle
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
import com.david.tot.ui.article.*
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

import androidx.compose.material.icons.filled.Sync
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SyncActivity : ComponentActivity() {

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
                    val syncViewModel = viewModel<SyncViewModel>()
                    //syncViewModel.onCreate()
                    //val drugsDeliveryConsumerViewHeaderViewModel = viewModel<DrugsDeliveryConsumerViewHeaderViewModel>()
                    //val contextArticleActivity = this@ArticleActivity
                    if(syncViewModel.toastTheresNotConsumiblesToSync)
                        Toast.makeText(contextForToast, "No se encontraron elementos", Toast.LENGTH_SHORT).show()
                    if(syncViewModel.toastInsertedSuccessfully)
                        Toast.makeText(contextForToast, "Registro Exitoso", Toast.LENGTH_SHORT).show()
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
                            TopAppBarSyncAcivity(syncViewModel) {
                                coroutineScope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        },
                        bottomBar = { BottomNavigationBar(navController) },
                        content = { padding ->
                            Box(modifier = Modifier.padding(padding)) {
                                NavigationBotomMenu(syncViewModel,navController = navController)
                            }
                        },
                        drawerContent = {
                            DrawerContent(context=this@SyncActivity) { itemLabel ->
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
fun TopAppBarSyncAcivity(syncViewModel: SyncViewModel, onNavIconClick: () -> Unit) {
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
                //enviar lo que hay pendiente al servidor, crear la header y enviarla [se usa el tipo de dato vehicle]
                syncViewModel.syncConsumible()
                //cuando el header responda ...
                //...Insertar los consumibles
                //cuando los consumibles responda ok
                //eliminar el registro de la tabla
                ///TODO cuando elimine el registro verificar si hay mas elementos pendientes  y repetir la operacion
            }) {
                Icon(
                    imageVector = Icons.Filled.Sync,
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
fun NavigationBotomMenu(syncViewModel:SyncViewModel, navController: NavHostController) {

    NavHost(navController, startDestination = BotomNavigationItem.Home.route) {
        composable(BotomNavigationItem.Home.route) {
            HeaderAndBodyScreen(syncViewModel)
        }
        composable(BotomNavigationItem.Music.route) {
            MusicScreen()
        }
        composable(BotomNavigationItem.Movies.route) {
            MoviesScreen()
        }
        composable(BotomNavigationItem.Books.route) {
            BooksScreen()
        }
        composable(BotomNavigationItem.Profile.route) {
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
        BotomNavigationItem.Home,
        BotomNavigationItem.Music,
        BotomNavigationItem.Movies,
        BotomNavigationItem.Books,
        BotomNavigationItem.Profile
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