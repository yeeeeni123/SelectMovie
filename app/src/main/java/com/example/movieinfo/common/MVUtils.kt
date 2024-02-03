package com.example.movieinfo.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import com.example.movieinfo.R

class MVUtils {
    var home = "home"
    var join = "join"
    var login = "login"


    @Composable
    fun textColors() : TextFieldColors {
        return TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.brightRed),
            unfocusedBorderColor = colorResource(id = R.color.brightGray),
            focusedLabelColor = colorResource(id = R.color.brightRed),
            cursorColor = colorResource(id = R.color.brightRed)
        )
    }

    @Composable
    fun pwEditText(label: String, hint: String, keyboardType: KeyboardType): TextFieldValue {
        var text by remember { mutableStateOf(TextFieldValue()) }
        var shouldShowPw by remember { mutableStateOf(false) }

        val passwordResource = if (shouldShowPw) {
            R.drawable.ic_visible
        } else {
            R.drawable.ic_invisible
        }


        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            singleLine = true,
            colors = textColors(),
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            trailingIcon = { IconButton(onClick = {
                shouldShowPw = !shouldShowPw
            }) {
                Icon(painter = painterResource(id = passwordResource), null)
            } },
            visualTransformation = if (shouldShowPw) VisualTransformation.None
            else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            onValueChange = { newValue -> text = newValue },
            label = { Text(label) },
            placeholder = { Text(hint) }
        )
        return text
    }

    @Composable
    fun editText(label: String, hint: String, keyboardType: KeyboardType): TextFieldValue {
        var text by remember { mutableStateOf(TextFieldValue()) }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            singleLine = true,
            colors = textColors(),
            leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            onValueChange = { newValue -> text = newValue },
            label = { Text(label) },
            placeholder = { Text(hint) }
        )
        return text
    }

    @Composable
    fun CheckBoxRow(text: String, value: Boolean, onClick: (Any) -> Unit) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = value, onCheckedChange = onClick,
                colors = CheckboxDefaults.colors(
                    checkedColor = colorResource(id = R.color.brightRed)
                )
            )
            ClickableText(
                text = AnnotatedString(text), onClick = onClick, modifier = Modifier.fillMaxWidth()
            )
        }
    }

}