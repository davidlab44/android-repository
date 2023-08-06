package com.david.tot.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Queue
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.david.tot.R
import com.david.tot.ui.theme.TotTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.david.tot.ui.article.*


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TotTheme(darkTheme = false) {
                androidx.compose.material.Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = androidx.compose.material.MaterialTheme.colors.background
                ) {
                    val scaffoldState = rememberScaffoldState()
                    val coroutineScope = rememberCoroutineScope()
                    val contextForToast = LocalContext.current.applicationContext
                    //var ordeModalWindowCUrrentState by rememberSaveable { mutableStateOf(false) }
                    //val recipeViewModel = viewModel<RecipeViewModel>()
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        scaffoldState = scaffoldState,
                        topBar = {
                            MyTopAppBar {
                                coroutineScope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        },
                        /*
                        floatingActionButton = {
                            FloatingActionButton(onClick = {
                                startActivity(Intent(this@MainActivity,AddProductActivity::class.java))
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Queue,
                                    contentDescription = "Ver orden de pedido"
                                )
                            }
                        },
                        floatingActionButtonPosition = FabPosition.End,
                        */
                        drawerContent = {
                            DrawerContent(context=this@MainActivity) { itemLabel ->
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
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val recipeViewModel = viewModel<RecipeViewModel>()
                            NavigationHost(recipeViewModel = recipeViewModel)
                            /*
                            Text(
                                text = getResources().getString(R.string.app_name) + " \n ".repeat(
                                    20
                                )
                            )
                            */
                        }
                    }
                }
            }
        }
    }
    /*
    TotTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val recipeViewModel = viewModel<RecipeViewModel>()
            NavigationHost(recipeViewModel)
        }
    }
    */
}




@Composable
fun MyTopAppBar(onNavIconClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = "Some text here") },
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
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Localized description"
                )
            }
        }

    )
}



@Composable
fun DrawerContent(
    context: Context,
    //gradientColors: List<Color> = listOf(Color(0xFFF70A74), Color(0xFFF59118)),
    gradientColors: List<Color> = listOf(Color(0xFF22475b), Color(0xFF7baf4a)),
    itemClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(colors = gradientColors)
                //color = Color(0xFF22475b)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 36.dp)
    ) {

        item {

            // user's image
            Image(
                modifier = Modifier
                    .size(size = 120.dp)
                    .clip(shape = CircleShape),
                //painter = painterResource(id = R.drawable.people1),
                painter = painterResource(id = R.drawable.baseline_home_24),
                contentDescription = "Profile Image"
            )

            // user's name
            Text(
                modifier = Modifier
                    .padding(top = 12.dp),
                text = "SISMEDICA",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 30.dp),
                text = "gerencia@sismedica.co",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color.White
            )
        }

        item {
            Spacer(modifier = Modifier.height(30.dp))
            Row(modifier = Modifier
                .fillMaxSize(),
                //.border(width = 1.dp, color = Color.Magenta),
                horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    modifier = Modifier
                        .size(size = 25.dp)
                        .clip(shape = CircleShape),
                    painter = painterResource(id = R.drawable.baseline_home_24),
                    contentDescription = "registrar consumo"
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    modifier = Modifier
                        .padding(top = 12.dp),
                    text = "Inicio",
                    fontSize = 26.sp,
                    color = Color.White
                )
            }
        }



        /*
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier
                .fillMaxSize(),
                //.border(width = 1.dp, color = Color.Magenta),
                horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    modifier = Modifier
                        .size(size = 25.dp)
                        .clip(shape = CircleShape),
                    painter = painterResource(id = R.drawable.baseline_home_24),
                    contentDescription = "Configuracion"
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    modifier = Modifier
                        .padding(top = 12.dp),
                    text = "Configuracion",
                    fontSize = 26.sp,
                    color = Color.White
                )

            }
        }
        */


        item {
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier
                .fillMaxSize(),
                //.border(width = 1.dp, color = Color.Magenta),
                horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    modifier = Modifier
                        .size(size = 25.dp)
                        .clip(shape = CircleShape),
                    painter = painterResource(id = R.drawable.baseline_home_24),
                    contentDescription = "Configuracion"
                )
                Spacer(modifier = Modifier.width(20.dp))
                ClickableText(
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 26.sp
                    ),
                    text = AnnotatedString("Salida de medicamentos") ,
                    onClick = {
                        context.startActivity(Intent(context,UpdateProductActivity::class.java))
                    }
                )
            }
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier
                .fillMaxSize(),
                //.border(width = 1.dp, color = Color.Magenta),
                horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    modifier = Modifier
                        .size(size = 25.dp)
                        .clip(shape = CircleShape),
                    painter = painterResource(id = R.drawable.baseline_home_24),
                    contentDescription = "Configuracion"
                )
                Spacer(modifier = Modifier.width(20.dp))
                ClickableText(
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 26.sp
                    ),
                    text = AnnotatedString("Salida de medicamentos") ,
                    onClick = {
                        context.startActivity(Intent(context, ArticleActivity::class.java))
                    }
                )
            }
        }
        /*
        //TODO pass navigation params here to make the nevigation dinamic
        items(itemsList) { item ->
            NavigationListItem(item = item) {
                itemClick(item.label)
            }
        }
        */
    }
}
