package com.example.fefu_course.presentation.features.signup

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fefu_course.R
import com.example.fefu_course.presentation.navigation.BottomNavigationRoot
import com.example.fefu_course.presentation.navigation.Root
import com.example.fefu_course.presentation.ui.theme.Typography
import com.example.fefu_course.presentation.ui.widget.AppBar
import com.example.fefu_course.presentation.ui.widget.BaseButton
import com.example.fefu_course.presentation.ui.widget.BaseTextField
import com.example.fefu_course.presentation.ui.widget.FormattedText
import com.example.fefu_course.presentation.ui.widget.PasswordTextField

@Composable
fun SignUpScreen(
    signUpState: SignUpState,
    navController: NavController,
    onLoginChanged: (String) -> Unit,
    onNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onRetryPasswordChanged: (String) -> Unit,
    onGenderChanged: (Gender) -> Unit,
    onSignUp: (SignUpState) -> Boolean
) {
    val navigateToMainRoot = {
        if (onSignUp(signUpState)) {
            navController.navigate(BottomNavigationRoot.Activity.route) {
                popUpTo(Root.Auth.route) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

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
                    value = signUpState.login,
                    onValueChange = onLoginChanged,
                    label = stringResource(id = R.string.login),
                    validate = signUpState.loginError
                )

                BaseTextField(
                    value = signUpState.name,
                    onValueChange = onNameChanged,
                    label = stringResource(id = R.string.signup_screen_name),
                    validate = signUpState.nameError
                )

                PasswordTextField(
                    value = signUpState.password,
                    onValueChange = {
                        onPasswordChanged(it)
                        onRetryPasswordChanged(signUpState.passwordRetry)
                    },
                    label = stringResource(id = R.string.password),
                    validate = signUpState.passwordError
                )

                PasswordTextField(
                    value = signUpState.passwordRetry,
                    onValueChange = onRetryPasswordChanged,
                    label = stringResource(id = R.string.signup_screen_password_repeat),
                    validate = signUpState.passwordRetryError
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
                        Gender.Men,
                        Gender.Woman,
                        Gender.Other
                    ).forEach { gender ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable { onGenderChanged(gender) }
                        ) {
                            RadioButton(
                                selected = signUpState.gender == gender,
                                onClick = { onGenderChanged(gender) }
                            )
                            Text(text = gender.name, modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))

                BaseButton(
                    text = stringResource(id = R.string.signup),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.button_height)),
                    onClickListener = navigateToMainRoot
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))

                FormattedText()
            }
        }
    )
}
