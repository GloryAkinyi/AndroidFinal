package com.example.wazitoecommerce.ui.theme.screens.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myproject.R
import com.example.myproject.data.AuthViewModel
import com.example.myproject.navigation.LOGIN_URL


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(navController:NavHostController){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(30.dp))

        Image(painter = painterResource(id = R.drawable.login),
            contentDescription = "",
            modifier = Modifier.size(200.dp))

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Shopify",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )
        Spacer(modifier = Modifier.height(30.dp))

        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confpassword by remember { mutableStateOf("") }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it},
            label = { Text(text = "Full Name", fontFamily = FontFamily.SansSerif)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            shape = RoundedCornerShape(5.dp)

        )

        Spacer(modifier = Modifier.height(10.dp))


        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text(text = "Email Address", fontFamily = FontFamily.SansSerif)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            shape = RoundedCornerShape(5.dp)
        )


        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text(text = "Password", fontFamily = FontFamily.SansSerif)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            shape = RoundedCornerShape(5.dp)

        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = confpassword,
            onValueChange = {confpassword = it},
            label = { Text(text = "Confirm Password", fontFamily = FontFamily.SansSerif)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            shape = RoundedCornerShape(5.dp)

        )

        Spacer(modifier = Modifier.height(10.dp))


        val context = LocalContext.current
        val authViewModel = AuthViewModel(navController, context)
        Button(onClick = {
            authViewModel.signup(name, email, password,confpassword)
        },
            colors = ButtonDefaults.buttonColors(Color.DarkGray),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            shape = RoundedCornerShape(5.dp)) {
            Text(text = "SignUp")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            navController.navigate(LOGIN_URL)
        },
            colors = ButtonDefaults.buttonColors(Color.DarkGray),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            shape = RoundedCornerShape(5.dp)) {
            Text(text = "SignIn")
        }

    }
}

@Composable
@Preview(showBackground = true)
fun SignupScreenPreview(){
        SignupScreen(navController = rememberNavController())

}