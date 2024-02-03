package com.example.movieinfo.login

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.example.movieinfo.R
import com.example.movieinfo.base.BaseFragment
import com.example.movieinfo.R.*
import com.example.movieinfo.common.MVUtils
import com.example.movieinfo.common.SampleApplication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Login() : BaseFragment() {

    private lateinit var auth: FirebaseAuth

    override fun initView() {

    }


    fun login(
        coroutineScope: CoroutineScope,
        autoLogin: Boolean,
        context: Context,
        navCotroller: NavController,
        email: String,
        password: String,

    ) {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            showToast(context, "이메일 혹은 비밀번호를 입력해주세요")
            return
        }

        auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (autoLogin) {
                        coroutineScope.launch(Dispatchers.IO) {
                            SampleApplication.getInstance().getDataStore().setText(email)
                        }
                    }
                    showToast(context, "로그인에 성공했습니다.")
                    navCotroller.navigate(MVUtils().home)
                } else {
                    showToast(context = context, "로그인에 실패했습니다.")
                }
            }
    }


    @Composable
    fun LoginScreen(context: Context, navCotroller: NavController) {
        val (checked, setChecked) = remember { mutableStateOf(false) }

        val coroutineScope = rememberCoroutineScope()

        Column(
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 50.dp, 0.dp, 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.movie_login),
                    contentDescription = null,
                    Modifier.size(100.dp)
                )
            }

            var userId = MVUtils().editText("이메일 아이디", "아이디를 입력해주세요", KeyboardType.Email)
            var userPw = MVUtils().pwEditText("비밀번호", "비밀번호를 입력해주세요", KeyboardType.Password)

            Column(
                Modifier
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    MVUtils().CheckBoxRow(
                        text = "자동 로그인",
                        value = checked,
                        onClick = { setChecked(!checked) })
                }

                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 16.dp, 0.dp, 0.dp)
                        .height(50.dp)
                        .background(
                            color = colorResource(id = color.brightRed),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clickable {
                            login(coroutineScope, checked, context, navCotroller, userId.text, userPw.text)
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("로그인", color = Color.White, fontSize = 16.sp)
                }

                Text(text = "회원가입",
                    style = androidx.compose.ui.text.TextStyle(
                        textAlign = TextAlign.Right
                    ),
                    modifier = Modifier
                        .padding(0.dp, 10.dp, 10.dp, 0.dp)
                        .fillMaxWidth()
                        .clickable {
                            navCotroller.navigate(MVUtils().join)
                        })
            }


        }
    }



}