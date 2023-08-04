package com.david.tot.ui.article

import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

@Composable
fun ScreenDetail(local_id: String, articleViewModel: ArticleViewModel) {

    //screenDetailViewModel.articleLocalId = local_id
    var launchUpdateProductActivity by rememberSaveable { mutableStateOf(false) }
    var launchDeleteProductActivity by rememberSaveable { mutableStateOf(false) }

    Text(text = "H1")
    /*
    for(product in recipeViewModel.recipeModel){
        if (product.id == remoteIdProduct.toInt()){
            recipeViewModel.productLocalId = product.local_id
            recipeViewModel.productRemoteId= product.id
            recipeViewModel.productName= product.name
            recipeViewModel.productDescription= product.description
            recipeViewModel.productImage= product.image
            recipeViewModel.productPrice= product.price
            val p = 1
            val gg = 1+p
        }else{
            val p = 0
            val gg = 0+p
        }
    }
    */

    /*
    if(launchUpdateProductActivity){
        launchUpdateProductActivity = false
        val context = LocalContext.current
        val intent = Intent(context, UpdateProductActivity::class.java)
        intent.putExtra("id_local", screenArticleViewModel.productLocalId.toString())
        intent.putExtra("id_remote", screenArticleViewModel.productRemoteId.toString())
        intent.putExtra("name", screenArticleViewModel.productName)
        intent.putExtra("description", screenArticleViewModel.productDescription)
        intent.putExtra("image", screenArticleViewModel.productImage)
        intent.putExtra("price", screenArticleViewModel.productPrice.toString())
        context.startActivity(intent)
    }

    if(launchDeleteProductActivity){
        launchDeleteProductActivity = false
        val context = LocalContext.current
        val intent = Intent(context, DeleteProductActivity::class.java)
        intent.putExtra("id_local", screenArticleViewModel.productLocalId.toString())
        intent.putExtra("id_remote", screenArticleViewModel.productRemoteId.toString())
        intent.putExtra("name", screenArticleViewModel.productName)
        intent.putExtra("description", screenArticleViewModel.productDescription)
        intent.putExtra("image", screenArticleViewModel.productImage)
        intent.putExtra("price", screenArticleViewModel.productPrice.toString())
        context.startActivity(intent)
    }
    */
    /*
    Column( horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .border(1.dp, Color.Gray, RectangleShape)
            .fillMaxWidth()
            .padding(20.dp)) {
        Row(
            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = screenDetailViewModel.article.articleDescription,
                textAlign = TextAlign.Center, color = Color.Black, fontSize = 32.sp, fontWeight = FontWeight.Black)
        }
        Row(
            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = screenDetailViewModel.article.articleDescription,
                textAlign = TextAlign.Center, color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Black)
        }
        Row(
            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = "Precio: "+screenDetailViewModel.article.quantityAvailable + " " + screenDetailViewModel.article.unitOfMeasure  ,
                textAlign = TextAlign.Center, color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Black)
        }
        /*
        Row(
            modifier = Modifier
                .padding(all = 12.dp)
                .height(150.dp),
            horizontalArrangement = Arrangement.Center,
        ){
            Image(
                painter = rememberImagePainter(IMAGE_BASE_URL+screenArticleViewModel.productImage),
                contentDescription = null,
                Modifier
                    .fillMaxSize()
                    .height(50.dp)
            )
        }

         */


        Row(
            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
        ){
            androidx.compose.material3.Button(
                onClick = { launchUpdateProductActivity=true},
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .height(60.dp)
            ) {
                androidx.compose.material3.Text("EDITAR")
            }
        }
        Row(
            modifier = Modifier.padding(all = 20.dp),horizontalArrangement = Arrangement.Center
        ){
            androidx.compose.material3.Button(
                onClick = { launchDeleteProductActivity=true},
                modifier = Modifier
                    .padding(bottom = 10.dp)
                    .height(60.dp)
            ) {
                androidx.compose.material3.Text("ELIMINAR")
            }
        }
    }


     */
    /*
    Column( horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.border(1.dp, Color.Gray, RectangleShape).fillMaxWidth().padding(20.dp)) {
        Row(
            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = recipe.name,
                textAlign = TextAlign.Center, color = Color.Black, fontSize = 32.sp, fontWeight = FontWeight.Black)
        }
        Row(
            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = recipe.description,
                textAlign = TextAlign.Center, color = Color.Black, fontSize = 32.sp, fontWeight = FontWeight.Black)
        }
        Row(
            modifier = Modifier.padding(all = 12.dp),horizontalArrangement = Arrangement.Center
        ){
            val bundle = Bundle()
            bundle.putDouble("longitude",recipe.coordinate.longitude)
            bundle.putDouble("latitude",recipe.coordinate.latitude)
            val context = LocalContext.current
            val intent = Intent(context,MapActivity::class.java)
            intent.putExtras(bundle)
            Button(onClick = {startActivity(context,intent,bundleOf())},elevation =  ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 15.dp,
                disabledElevation = 0.dp
            )) {
                Text(text = stringResource(R.string.map_button_text))
            }
        }
        Row(
            modifier = Modifier.padding(all = 12.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Image(
                painter = rememberImagePainter(recipe.image),
                contentDescription = null,
                Modifier
                    .fillMaxSize()
                    .height(200.dp)
            )
        }
    }
    */
    //Thread.sleep(500)
    //recipeViewModel.intentLauncher
    val r =78
}
