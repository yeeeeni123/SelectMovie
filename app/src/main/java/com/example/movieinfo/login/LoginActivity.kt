package com.example.movieinfo.login

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieinfo.R.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextFieldTest()
        }
    }
}

@Composable
fun TextFieldTest() {
    var userId by remember { mutableStateOf(TextFieldValue()) }
    var userPw by remember { mutableStateOf(TextFieldValue()) }
    var shouldShowPw by remember { mutableStateOf(false) }

    val passwordResource = if (shouldShowPw) {
        drawable.ic_visible
    } else {
        drawable.ic_invisible
    }


    Column(
        Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = userId,
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null)},
            onValueChange = {newValue -> userId = newValue},
            label = {Text("아이디")},
            placeholder = {Text("아이디를 입력해주세요")}
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = userPw,
            singleLine = true,
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null)},
            trailingIcon = {
                IconButton(onClick = {
                    shouldShowPw = !shouldShowPw
                }) {
                    Icon(painter = painterResource(id = passwordResource),
                        null)
                }
            },
            visualTransformation = if(shouldShowPw) VisualTransformation.None
            else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            onValueChange = {newValue -> userPw = newValue},
            label = {Text("비밀번호")},
            placeholder = {Text("비밀번호를 입력해주세요")}
        )


        Column(
            Modifier.padding(16.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(onClick = {

            }) {
                Text(text = "로그인")
            }

        }
    }
}




@Preview(showBackground = true)
@Composable
fun LoginPreView() {
    TextFieldTest()
}