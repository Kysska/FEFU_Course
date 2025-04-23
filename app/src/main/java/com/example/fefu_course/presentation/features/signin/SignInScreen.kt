package com.example.fefu_course.presentation.features.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.fefu_course.R
import com.example.fefu_course.presentation.ui.widget.BaseButton
import com.example.fefu_course.presentation.ui.widget.BaseTextField
import com.example.fefu_course.presentation.ui.widget.PasswordTextField
import com.example.fefu_course.presentation.ui.widget.ScaffoldWithOptionalAppBar

@Composable
fun SignInScreen(
    state: SignInState,
    onLoginChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onSignIn: () -> Unit,
    onNavigateToMainRoot: () -> Unit,
    onBackNavigate: () -> Unit
) {
    val navigateToMainRoot = {
        onSignIn()
        if (state.isSuccess) {
            onNavigateToMainRoot()
        }
    }

    ScaffoldWithOptionalAppBar(
        showAppBar = true,
        onClickBackButton = onBackNavigate,
        content = {

        Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.padding_small)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_small))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.welcomescreenimage),
                    contentDescription = stringResource(
                        id = R.string.image_description
                    )
                )

                BaseTextField(
                    value = state.login,
                    onValueChange = onLoginChanged,
                    label = stringResource(id = R.string.login),
                    validate = state.loginError
                )

                PasswordTextField(
                    value = state.password,
                    onValueChange = onPasswordChanged,
                    label = stringResource(id = R.string.password),
                    validate = state.passwordError
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))

                BaseButton(
                    text = stringResource(id = R.string.signin_screen_button),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.button_height)),
                    onClickListener = navigateToMainRoot
                )
            }
        }
    )
}
