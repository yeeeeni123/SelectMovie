package com.example.movieinfo.login

import android.content.Context
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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
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
import com.example.movieinfo.common.MVUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Join : BaseFragment() {

    private lateinit var auth: FirebaseAuth


    override fun initView() {

    }


    fun join(context: Context, navCotroller: NavController, email: String, password: String, passwordCheck : String){
        if(email.isNullOrEmpty() || password.isNullOrEmpty()) {
            showToast(context, "이메일 혹인 비밀번호를 입력해주세요")
            return
        }

        if(!email.contains("@") || password.length < 6) {
            showToast(context, "이메일 형식인지 또는 비밀번호 6자리이상 입력해주세요")
            return
        }

        if (!password.equals(passwordCheck)) {
            showToast(context, "비밀번호와 비밀번호 확인 값이 서로 다릅니다.")
            return
        }

        auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast(context, "회원가입에 성공했습니다.")
                    navCotroller.navigate("login")
                } else  {
                    showToast(context, "회원가입에 실패했습니다.")
                }
            }
    }


    @Composable
    fun JoinScreen(context: Context, navCotroller: NavController) {

        Column(
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(
                Modifier.fillMaxWidth()
                    .padding(0.dp,20.dp,0.dp,10.dp)
                    .clickable {
                        navCotroller.popBackStack()
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End){
                Image(
                    painter=painterResource(drawable.ic_close),
                    contentDescription=null,
                    Modifier.size(25.dp)
                )
            }

            var userId = MVUtils().editText("이메일 아이디", "아이디를 입력해주세요", KeyboardType.Email)
            var userPw = MVUtils().pwEditText("비밀번호", "비밀번호를 입력해주세요", KeyboardType.Password)
            var userPwCheck = MVUtils().pwEditText("비밀번호 확인", "비밀번호를 입력해주세요", KeyboardType.Password)


            Column(
                Modifier.fillMaxWidth()
                    .height(50.dp)
                    .background(
                        color = colorResource(id = color.brightRed),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .clickable {
                        join(context, navCotroller, userId.text, userPw.text, userPwCheck.text)
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally){
                Text("회원가입", color = Color.White, fontSize = 16.sp)
            }

        }
    }




}

