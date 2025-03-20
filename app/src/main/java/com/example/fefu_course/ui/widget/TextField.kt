package com.example.fefu_course.ui.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.fefu_course.R
import com.example.fefu_course.ui.theme.Typography
import com.example.fefu_course.ui.theme.primaryColor
import com.example.fefu_course.ui.theme.textFieldBorderColor

@Composable
fun BaseTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, style = Typography.labelSmall) },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedTextColor = textFieldBorderColor,
            focusedTextColor = primaryColor,
            focusedLabelColor = primaryColor,
            unfocusedLabelColor = textFieldBorderColor,
            unfocusedBorderColor = textFieldBorderColor,
            focusedBorderColor = primaryColor
        ),
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    val isPasswordVisible = remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, style = Typography.labelSmall) },
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedTextColor = textFieldBorderColor,
            focusedTextColor = primaryColor,
            focusedLabelColor = primaryColor,
            unfocusedLabelColor = textFieldBorderColor,
            unfocusedBorderColor = textFieldBorderColor,
            focusedBorderColor = primaryColor
        ),
        singleLine = true,
        visualTransformation = if (isPasswordVisible.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = {
                isPasswordVisible.value = !isPasswordVisible.value
            }) {
                Icon(
                    painter = painterResource(
                        id = if (isPasswordVisible.value) {
                            R.drawable.baseline_visibility_24
                        } else {
                            R.drawable.baseline_visibility_off_24
                        }
                    ),
                    contentDescription = if (isPasswordVisible.value) {
                        stringResource(id = R.string.signup_screen_password_visibility)
                    } else {
                        stringResource(id = R.string.signup_screen_password_not_visibility)
                    }
                )
            }
        }
    )
}
