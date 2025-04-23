package com.example.fefu_course.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.fefu_course.R
import com.example.fefu_course.ui.widget.AppBar
import com.example.fefu_course.ui.widget.BaseButton
import com.example.fefu_course.ui.widget.BaseTextField
import com.example.fefu_course.ui.widget.PasswordTextField

@Composable
fun SignInScreen(navController: NavController) {
    Scaffold(
        topBar = {
            AppBar(title = stringResource(id = R.string.signin_screen_title)) {
                navController.navigateUp()
            }
        },
        content = { paddingValues ->

            var login by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
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
                    value = login,
                    onValueChange = { login = it },
                    label = stringResource(id = R.string.login),
                    validate = null
                )

                PasswordTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = stringResource(id = R.string.password),
                    validate = null
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_small)))

                BaseButton(
                    text = stringResource(id = R.string.signin_screen_button),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(id = R.dimen.button_height))
                ) {
                }
            }
        }
    )
}
