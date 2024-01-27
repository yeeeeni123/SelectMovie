package com.example.movieinfo.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movieinfo.base.BaseFragment
import com.example.movieinfo.R.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class JoinFragment : BaseFragment() {

    private lateinit var auth: FirebaseAuth

    override fun initView() {

    }

    fun join(email: TextFieldValue, password: TextFieldValue) : Boolean {
        var check = false
        auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email.text, password.text)
            .addOnCompleteListener { task ->
                check = task.isSuccessful
            }
        return check
    }


    @Composable
    fun TextFieldTest(navCotroller: NavController) {
        var userId by remember { mutableStateOf(TextFieldValue()) }
        var userPw by remember { mutableStateOf(TextFieldValue()) }
        var userPwCheck by remember { mutableStateOf(TextFieldValue()) }
        var shouldShowPw by remember { mutableStateOf(false) }

        val passwordResource = if (shouldShowPw) {
            drawable.ic_visible
        } else {
            drawable.ic_invisible
        }


        Column(
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(
                Modifier.fillMaxWidth()
                    .padding(0.dp,50.dp,0.dp,20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Image(
                    painter=painterResource(drawable.movie_login),
                    contentDescription=null,
                    Modifier.size(100.dp)
                )
            }

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = userId,
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                onValueChange = { newValue -> userId = newValue },
                label = { Text("이메일 아이디") },
                placeholder = { Text("이메일 아이디를 입력해주세요") }
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = userPw,
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        shouldShowPw = !shouldShowPw
                    }) {
                        Icon(
                            painter = painterResource(id = passwordResource),
                            null
                        )
                    }
                },
                visualTransformation = if (shouldShowPw) VisualTransformation.None
                else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { newValue -> userPw = newValue },
                label = { Text("비밀번호") },
                placeholder = { Text("비밀번호를 입력해주세요") }
            )


            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(0.dp, 0.dp, 0.dp, 16.dp),
                value = userPwCheck,
                singleLine = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )
                },
                trailingIcon = {
                    IconButton(onClick = {
                        shouldShowPw = !shouldShowPw
                    }) {
                        Icon(
                            painter = painterResource(id = passwordResource),
                            null
                        )
                    }
                },
                visualTransformation = if (shouldShowPw) VisualTransformation.None
                else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = { newValue -> userPw = newValue },
                label = { Text("비밀번호 확인") },
                placeholder = { Text("비밀번호를 입력해주세요") }
            )


            Column(
                Modifier.fillMaxWidth()
                    .height(50.dp)
                    .background(
                        color = colorResource(id = color.brightRed),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable {
                        val check = join(userId, userPw)
                        if (check) {
                            navCotroller.navigate("login")
                        } else{
                            //회원가입 실패
                        }
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Text("회원가입", color = Color.White, fontSize = 16.sp)
            }




        }
    }


}

