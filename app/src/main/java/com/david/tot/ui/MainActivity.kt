package com.david.tot.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Sync
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
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.amazonaws.auth.CognitoCachingCredentialsProvider
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.PutObjectRequest
import com.david.tot.R
import com.david.tot.ui.theme.TotTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.david.tot.ui.consumible.*
import com.david.tot.ui.cameraxtutorial.Main2Activity
import com.david.tot.ui.authenticable.AuthenticableViewModel
import com.david.tot.ui.pre.PreActivity
import com.david.tot.ui.reloadable.ReloadableActivity
import com.david.tot.ui.settings.SettingsActivity
import com.david.tot.ui.spendable.SpendableActivity
import com.david.tot.ui.sync.SyncActivity
import com.david.tot.ui.sync.SyncViewModel
import com.david.tot.util.Dates
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.io.File


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //TODO
    //TODO no permitir consultar en el API nada hasta que no se termine de sincronizar!!!!
    //OJO
    //desde que empiezas a trabajar offline no te trae nueva informacion hasta que no sincronize toda la cola

    //



    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //requestPermissions()
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
                    //recipeViewModel.getPhotoList()
                    val consumibleViewModel = viewModel<ConsumibleViewModel>()
                    val authenticableViewModel=viewModel<AuthenticableViewModel>()
                    //val recipeViewModel = viewModel<RecipeViewModel>()

                    //DUMMY CREDENTIALS
                    //SHARED PREFERENCES
                    /*
                    val photoUrl = "/storage/self/primary/Download"
                    val prefs = FastPrefs(contextForToast)
                    prefs.setString("Reportable",photoUrl)
                    val value = prefs.getString("Reportable","defaultValue")
                    Log.e("TG",""+value)
                    */
                    //authenticableViewModel.addOneHardcodedAuthenticableToLocalDb()
                    NavigationHost()
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
                        content = { padding ->
                            Box(modifier = Modifier.padding(padding)) {
                                BodyList(consumibleViewModel,authenticableViewModel)
                            }
                        },
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
                    )
                    /*
                    {

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
            IconButton(onClick = { /* doSomething() */ }) {
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
*/


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

            val myImage: Drawable? = ResourcesCompat.getDrawable(context.resources, R.drawable.logo, null)

            // user's image
            Image(
                modifier = Modifier
                    .size(size = 120.dp)
                    .clip(shape = CircleShape),
                //painter = painterResource(id = R.drawable.people1),
                painter = painterResource(id = R.drawable.loga),
                //painter = painter(id = R.drawable.baseline_home_24),
                contentDescription = "Profile Image"
            )

            // user's name
            Text(
                modifier = Modifier
                    .padding(top = 12.dp),
                text = "GENERAL LEDGER ERP",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(top = 8.dp, bottom = 30.dp),
                text = "Sistema de información gerencial",
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color.White
            )
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
                    contentDescription = "Inicio"
                )
                Spacer(modifier = Modifier.width(20.dp))
                ClickableText(
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 26.sp
                    ),
                    text = AnnotatedString("Inicio") ,
                    onClick = {

                        context.startActivity(Intent(context,MainActivity::class.java))
                    }
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
                    //painter =  painterResource(id = R.drawable.backup_white_18dp_rotated),
                    painter = painterResource(R.drawable.backup_white_18dp),
                    contentDescription = "Syncronizar"
                )
                Spacer(modifier = Modifier.width(20.dp))
                ClickableText(
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 26.sp
                    ),
                    text = AnnotatedString("Syncronizar") ,
                    onClick = {
                        context.startActivity(Intent(context, SyncActivity::class.java))
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
                    painter = painterResource(id = R.drawable.fact_check_white_18dp),
                    contentDescription = "Preoperativo"
                )
                Spacer(modifier = Modifier.width(20.dp))
                ClickableText(
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 26.sp
                    ),
                    text = AnnotatedString("Pre operativo") ,
                    onClick = {
                        context.startActivity(Intent(context, PreActivity::class.java))
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
                    painter = painterResource(id = R.drawable.fact_check_white_18dp),
                    contentDescription = "Pos operativo"
                )
                Spacer(modifier = Modifier.width(20.dp))
                ClickableText(
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 26.sp
                    ),
                    text = AnnotatedString("Pos operativo") ,
                    onClick = {
                        context.startActivity(Intent(context, PreActivity::class.java))
                        //context.startActivity(Intent(context,UpdateProductActivity::class.java))
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
                    painter = painterResource(id = R.drawable.build_white_18dp),
                    contentDescription = "Reporte de novedades"
                )
                Spacer(modifier = Modifier.width(20.dp))
                ClickableText(
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 26.sp
                    ),
                    text = AnnotatedString("Reporte de novedades"),
                    onClick = {
                        context.startActivity(Intent(context, Main2Activity::class.java))
                        //context.startActivity(Intent(context, RequirableActivity::class.java))
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
                    painter = painterResource(id = R.drawable.payments_fill1_wght400_grad0_opsz24),
                    contentDescription = "Reporte de Gastos"
                )
                Spacer(modifier = Modifier.width(20.dp))
                ClickableText(
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 26.sp
                    ),
                    text = AnnotatedString("Reporte de Gastos") ,
                    onClick = {
                        context.startActivity(Intent(context, SpendableActivity::class.java))
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
                    painter = painterResource(id = R.drawable.home_repair_service_filled),
                    contentDescription = "Solicitud de Reposición"
                )
                Spacer(modifier = Modifier.width(20.dp))
                ClickableText(
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 26.sp
                    ),
                    text = AnnotatedString("Solicitud de reposición") ,
                    onClick = {
                        context.startActivity(Intent(context, ReloadableActivity::class.java))
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
                    painter = painterResource(id = R.drawable.receipt_long_white_18dp),
                    contentDescription = "Salida consumibles"
                )
                Spacer(modifier = Modifier.width(20.dp))
                ClickableText(
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 26.sp
                    ),
                    text = AnnotatedString("Salida consumibles") ,
                    onClick = {
                        context.startActivity(Intent(context, ArticleActivity::class.java))
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
                    painter = painterResource(id = R.drawable.settings_white_18dp),
                    contentDescription = "Configuracion"
                )
                Spacer(modifier = Modifier.width(20.dp))
                ClickableText(
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 26.sp
                    ),
                    text = AnnotatedString("Configuración") ,
                    onClick = {
                        context.startActivity(Intent(context, SettingsActivity::class.java))
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
                    painter = painterResource(id = R.drawable.logout_white_18dp),
                    contentDescription = "Salir"
                )
                Spacer(modifier = Modifier.width(20.dp))
                ClickableText(
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 26.sp
                    ),
                    text = AnnotatedString("Salir") ,
                    onClick = {
                        //context.startActivity(Intent(context, Main2Activity::class.java))
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


fun subir(mContext: Context){
    CoroutineScope(Dispatchers.IO).launch {
        //val file = File("/storage/self/primary/Download/APP_1694121670333.png")

        // Initialize Cognito Identity Provider and S3 clients
        val credentialsProvider = CognitoCachingCredentialsProvider(
            mContext,
            "us-east-1:c432f057-8e05-435e-a7fb-308c70fe1cb5",
            Regions.US_EAST_1
        )

        val s3Client = AmazonS3Client(credentialsProvider)

        // Specify the S3 bucket name and object key (file name)
        //val bucketName = "your_bucket_name"
        val bucketName = "gl-human-resources/drugDelivery"

        //val objectKey = "your_desired_object_key" // e.g., "uploads/image.jpg"

        val objectKey = "APP_"+ Dates().imageIdentifier() +".jpg"
        //val objectKey = "exampleimage.jpg" // e.g., "uploads/image.jpg"

        // Create a file object from the selected image
        //val selectedImageUri: Uri = ... // Get the URI of the selected image

        //val file = File("/storage/self/primary/Download/APP_1694121670333.png")
        //Log.e("TAGCC",""+cc)
        val file = File(mContext.getFilesDir().getPath()+"/exampleimage.png")

        // Upload the file to S3
        val putObjectRequest = PutObjectRequest(bucketName, objectKey, file).withCannedAcl(
            CannedAccessControlList.PublicRead)
        s3Client.putObject(putObjectRequest)

        Log.e("TAGCC",""+putObjectRequest)
        Log.e("TAGCC",""+putObjectRequest.toString())
    }
}
