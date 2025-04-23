package com.example.fefu_course.presentation.features.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.fefu_course.R
import com.example.fefu_course.presentation.ui.theme.Typography
import com.example.fefu_course.presentation.ui.widget.BaseButton

@Composable
fun WelcomeScreen(onNavigateToSignIn: () -> Unit, onNavigateToSignUp: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = dimensionResource(id = R.dimen.padding_large)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_medium))
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_large)))

        Image(
            painter = painterResource(id = R.drawable.welcomescreenimage),
            contentDescription = stringResource(id = R.string.image_description),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(dimensionResource(id = R.dimen.padding_xsmall)),
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = stringResource(id = R.string.welcome_screen_title),
            style = Typography.displayLarge,
            modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
            textAlign = TextAlign.Center
        )

        Text(
            text = stringResource(id = R.string.welcome_screen_description),
            style = Typography.bodySmall
        )

        BaseButton(
            text = stringResource(id = R.string.signup),
            modifier = Modifier.height(dimensionResource(id = R.dimen.button_height)),
            onClickListener = onNavigateToSignUp
        )

        Text(
            text = stringResource(id = R.string.welcome_screen_text_signin),
            style = Typography.labelMedium,
            modifier = Modifier.clickable(onClick = onNavigateToSignIn)
        )
    }
}
