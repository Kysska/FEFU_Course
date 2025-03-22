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
import com.example.fefu_course.ui.theme.errorColor
import com.example.fefu_course.ui.theme.primaryColor
import com.example.fefu_course.ui.theme.textFieldBorderColor

@Composable
private fun CommonTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    errorMessage: String?,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable (() -> Unit)? = null
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
            focusedBorderColor = primaryColor,
            errorBorderColor = errorColor,
            errorLabelColor = errorColor,
            errorTextColor = errorColor
        ),
        isError = errorMessage != null,
        supportingText = {
            if (errorMessage != null) {
                Text(text = errorMessage, color = errorColor)
            }
        },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        trailingIcon = trailingIcon
    )
}

@Composable
fun BaseTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    validate: String?
) {
    CommonTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        errorMessage = validate
    )
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    validate: String?
) {
    val isPasswordVisible = remember { mutableStateOf(false) }

    CommonTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        errorMessage = validate,
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
