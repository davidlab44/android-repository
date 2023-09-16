package com.david.tot.ui.authenticable

import android.text.TextUtils
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.RowScopeInstance.align
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.david.tot.R


@Composable
fun LoginScreen(
    authenticableViewModel:AuthenticableViewModel,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        item {
            Box {
                Column(
                    modifier
                        .fillMaxWidth()
                        .height(241.dp)
                        .background(MaterialTheme.colors.primary),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {

                }
                Column(
                    //verticalArrangement = Arrangement.Top,
                    modifier=Modifier.padding(top=40.dp,start=120.dp,bottom=50.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    /*
                    Image(
                        painterResource(R.drawable.home_repair_service_fill1_wght400_grad0_opsz24),
                        contentDescription = "QPAY TITLE",
                        modifier.padding(bottom = 16.dp, top = 16.dp)
                    )
                    */

                    Image(
                        painterResource(id = R.drawable.loga),
                        contentDescription = "Finance Analysis",
                        //modifier.fillMaxWidth()
                        modifier = Modifier
                            .size(size = 140.dp)
                            .clip(shape = CircleShape),
                    )
                }
            }

            Column(
                modifier
                    .padding(start = 36.dp, end = 36.dp)
                    .fillMaxSize()
            ) {

                Column(
                    modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = "GENERAL LEDGER E.R.P",
                        modifier
                            .padding(top = 20.dp, bottom = 18.dp),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                /*
                Column(
                    modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = "Sistema de informacion gerencial",
                        modifier
                            .padding(top = 0.dp, bottom = 0.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                        color= Color(0xFFAAAAAA)
                    )
                }
                */

                // The text will remain intact when there is configuration changes
                var email by rememberSaveable { mutableStateOf("") }
                var password by rememberSaveable { mutableStateOf("") }

                // value of isEmailValid true or false
                //val emailIsValid = email.isEmailValid()

                // Icon will changed to check when the email is valid
                //val emailTrailingIcon = if (emailIsValid)
                    //painterResource(id = R.drawable.check_circle_fill1_wght400_grad0_opsz24)
                //else
                    painterResource(id = R.drawable.cancel_fill1_wght400_grad0_opsz24)

                Column(
                    modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    OutlinedTextField(
                        value = "SISMEDICAERP",
                        onValueChange = { it ->

                        },
                        modifier = modifier
                            .fillMaxWidth()
                            //.width(342.dp)
                            .padding(bottom = 9.dp),
                        singleLine = true,
                        label = { Text(text = "Empresa:") },
                        //placeholder = { Text("") },

                    )


                    OutlinedTextField(
                        value = email,
                        onValueChange = { typedEmail ->
                            email = typedEmail
                        },
                        modifier = modifier
                            .fillMaxWidth()
                            //.width(342.dp)
                            .padding(bottom = 9.dp),
                        singleLine = true,
                        label = { Text(text = "User") },
                        placeholder = { Text("") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Person,
                                contentDescription = "Email"
                            )
                        },
                        /*
                        trailingIcon = {
                            Icon(
                                painter = emailTrailingIcon,
                                contentDescription = "Email is valid"
                            )
                        }
                        */
                    )

                    // Password visibility will remain intact when there is configuration changes
                    var passwordVisibility by remember {
                        mutableStateOf(false)
                    }

                    // Icon button for visibility of password
                    val passwordTrailingIcon = if (passwordVisibility)
                        painterResource(id = R.drawable.visibility_fill1_wght400_grad0_opsz24)
                    else
                        painterResource(id = R.drawable.visibility_off_fill1_wght400_grad0_opsz24)

                    OutlinedTextField(
                        value = password,
                        onValueChange = { typedPassword ->
                            password = typedPassword
                        },
                        modifier = modifier
                            .fillMaxWidth(),
                        //.width(342.dp),
                        singleLine = true,
                        label = { Text(text = "Password") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.Lock,
                                contentDescription = "Password"
                            )
                        },
                        trailingIcon = {
                            IconButton(
                                onClick = {
                                    passwordVisibility = !passwordVisibility

                                }
                            ) {
                                Icon(
                                    painter = passwordTrailingIcon,
                                    contentDescription = "Password Visibility"
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        visualTransformation = if (passwordVisibility) VisualTransformation.None
                        else
                            PasswordVisualTransformation()
                    )
                    /*
                    //Forgot password link
                    ClickableText(
                        text = AnnotatedString("Forgot Password?"),
                        style = MaterialTheme.typography.caption,
                        modifier =
                        modifier.padding(top = 18.dp, bottom = 18.dp),
                        onClick = {
                            Log.d(
                                "Login Screen",
                                "Forgot Password clicked!"
                            )
                        }
                    )
                    */

                    //Login Button
                    Button(
                        onClick = {
                            authenticableViewModel.login("ADMIN","Med1co2011")
                        },
                        modifier
                            .fillMaxWidth()
                            //.width(342.dp)
                            .height(64.dp)
                            .padding(top=15.dp,bottom = 0.dp)
                    ) {
                        Text(
                            "Iniciar Sesión",
                            fontSize = 17.sp
                        )
                    }

                    /*
                    //Create Account Button
                    OutlinedButton(
                        onClick = { navController.navigate("create_account") },
                        modifier
                            .fillMaxWidth()
                            // .width(342.dp)
                            .height(64.dp)
                            .padding(bottom = 12.dp)
                    ) {
                        Text(
                            "Create Account",
                            fontSize = 17.sp
                        )
                    }
                    */

                }


            }

        }

    }


}



// Email validity check
fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

