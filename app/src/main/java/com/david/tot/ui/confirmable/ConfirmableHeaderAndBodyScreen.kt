package com.david.tot.ui.confirmable

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.david.tot.ui.authenticable.AuthenticableViewModel
import com.david.tot.ui.authenticable.AuthenticableScreenComponentHeader
import com.david.tot.ui.cameraxtutorial.Main2Activity
import com.yeslab.fastprefs.FastPrefs

@Composable
fun ConfirmableHeaderAndBodyScreen(contextActivity:ConfirmableActivity, confirmableViewModel: ConfirmableViewModel, authenticableViewModel: AuthenticableViewModel) {
    //authenticableViewModel.getAlldrugsDeliveryConsumerViewHeader()
    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //var text by rememberSaveable { mutableStateOf("") }
        //var text by rememberSaveable { mutableStateOf("") }
        Row(
            modifier = Modifier.padding(all = 0.dp).height(140.dp),
            //horizontalArrangement = Arrangement.Center
        ) {
            AuthenticableScreenComponentHeader(authenticableViewModel)
        }
        Row(
            modifier = Modifier.padding(all = 2.dp),
            //horizontalArrangement = Arrangement.Center
        ) {
            BodyConfirmableList(contextActivity,confirmableViewModel,authenticableViewModel)
        }
    }
}

/*
@Composable
fun HomeScreenPreview(articleViewModel:ArticleViewModel,drugsDeliveryConsumerViewHeaderViewModel: DrugsDeliveryConsumerViewHeaderViewModel) {
    ArticleHeaderAndBodyScreen(articleViewModel, drugsDeliveryConsumerViewHeaderViewModel)
}
 */

@Composable
fun MusicScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            //.background(colorResource(id = R.color.colorPrimaryDark))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Music View",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MusicScreenPreview() {
    MusicScreen()
}

@Composable
fun MoviesScreen(contextActivity:ConfirmableActivity) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            //.background(colorResource(id = R.color.colorPrimaryDark))
            .wrapContentSize(Alignment.Center)
    ) {
        val prefs = FastPrefs(contextActivity)
        prefs.setString("caller","ConfirmableActivity")
        contextActivity.startActivity(Intent(contextActivity, Main2Activity::class.java))
        /*
        Text(
            text = "Movies View",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        */
    }
}

/*
@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
    MoviesScreen()
}
*/

@Composable
fun BooksScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            //.background(colorResource(id = R.color.colorPrimaryDark))
            .wrapContentSize(Alignment.Center)
    ) {
        /*
        Text(
            text = "Books View",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
         */
    }
}

@Preview(showBackground = true)
@Composable
fun BooksScreenPreview() {
    BooksScreen()
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            //.background(colorResource(id = R.color.colorPrimaryDark))
            .wrapContentSize(Alignment.Center)
    ) {
        /*
        Text(
            text = "Profile View",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        */
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen()
}