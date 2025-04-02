package com.example.fefu_course.presentation.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.fefu_course.R
import com.example.fefu_course.presentation.ui.theme.Typography

@Composable
fun EmptyActivities() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(modifier = Modifier, text = stringResource(R.string.empty_my_activities), style = Typography.displayLarge)
        Text(text = stringResource(R.string.empty_my_activities_desc),
            modifier = Modifier
                .padding(
                    top = dimensionResource(
                        id = R.dimen.padding_xsmall
                    )
                ), style = Typography.bodySmall, textAlign = TextAlign.Center)
    }
}
