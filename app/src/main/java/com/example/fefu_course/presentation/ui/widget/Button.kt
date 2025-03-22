package com.example.fefu_course.presentation.ui.widget

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.fefu_course.R
import com.example.fefu_course.presentation.ui.theme.primaryColor

@Composable
fun BaseButton(
    text: String,
    modifier: Modifier = Modifier,
    onClickListener: () -> Unit
) {
    Button(
        onClick = onClickListener,
        colors = ButtonDefaults.buttonColors(
            containerColor = primaryColor,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.button_corner_radius)),
        modifier = modifier
    ) {
        Text(text = text, style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold))
    }
}
