package com.example.fefu_course.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fefu_course.R
import com.example.fefu_course.ui.theme.Typography
import com.example.fefu_course.ui.widget.AppBar
import com.example.fefu_course.ui.widget.BaseButton
import com.example.fefu_course.ui.widget.BaseTextField
import com.example.fefu_course.ui.widget.FormattedText
import com.example.fefu_course.ui.widget.PasswordTextField

@Composable
fun SignUpScreen(navController: NavController) {
    var login by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            AppBar(title = stringResource(id = R.string.signup_screen_title)) {
                navController.navigateUp()
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_small))
            ) {

                BaseTextField(
                    value = login,
                    onValueChange = { login = it },
                    label = stringResource(id = R.string.login),
                    validate = null
                )

                BaseTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = stringResource(id = R.string.signup_screen_name),
                    validate = null
                )

                PasswordTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = stringResource(id = R.string.password),
                    validate = null
                )

                PasswordTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = stringResource(id = R.string.signup_screen_password_repeat),
                    validate = null
                )

                Text(
                    text = stringResource(id = R.string.signup_screen_gender),
                    style = Typography.displayLarge,
                    modifier = Modifier
                        .align(Alignment.Start),
                    textAlign = TextAlign.Left
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
                ) {
                    listOf(
                        stringResource(id = R.string.signup_screen_gender_m),
                        stringResource(id = R.string.signup_screen_gender_w),
                        stringResource(id = R.string.signup_screen_gender_o)
                    ).forEach { gender ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable { selectedGender = gender }
                        ) {
                            RadioButton(
                                selected = selectedGender == gender,
                                onClick = { selectedGender = gender }
                            )
                            Text(text = gender, modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))

                BaseButton(
                    text = stringResource(id = R.string.signup),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.button_height))
                ) {
                }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))

                FormattedText()
            }
        }
    )
}
