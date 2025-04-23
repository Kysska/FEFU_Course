package com.example.fefu_course.presentation.ui.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import com.example.fefu_course.R
import com.example.fefu_course.presentation.ui.theme.Typography
import com.example.fefu_course.presentation.ui.theme.primaryColor

@Composable
fun FormattedText() {
    Text(
        text = buildAnnotatedString {
            append("Нажимая на кнопку, вы соглашаетесь с ")

            withStyle(style = SpanStyle(color = primaryColor)) {
                append("политикой конфиденциальности")
            }

            append(" и ")

            withStyle(style = SpanStyle(color = primaryColor)) {
                append("обработки персональных данных")
            }

            append(", а также принимаете ")

            withStyle(style = SpanStyle(color = primaryColor)) {
                append("пользовательское соглашение")
            }
        },
        style = Typography.bodySmall,
        modifier = Modifier
            .wrapContentWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
        textAlign = TextAlign.Center
    )
}
