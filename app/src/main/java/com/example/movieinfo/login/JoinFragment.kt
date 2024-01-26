package com.example.movieinfo.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.movieinfo.base.BaseFragment
import com.example.movieinfo.R.*
import java.time.format.TextStyle

class JoinFragment: BaseFragment() {
    override fun initView() {

    }


    @Composable
    fun TextFieldTest(navCotroller: NavController) {
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
                label = {Text("비밀번호 확인")},
                placeholder = {Text("비밀번호를 입력해주세요")}
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
                label = {Text("이름")},
                placeholder = {Text("이름을 입력해주세요")}
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
                label = {Text("이메일 주소")},
                placeholder = {Text("이메일 주소를 입력해주세요")}
            )


            Column(
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(onClick = {
                    navCotroller.navigate("login")
                }) {
                    Text(text = "회원가입")
                }

            }

        }
    }


}