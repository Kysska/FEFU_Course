package com.example.fefu_course.presentation.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fefu_course.R
import com.example.fefu_course.presentation.ui.theme.Typography
import com.example.fefu_course.presentation.vo.ActivityItemListView

@Composable
fun ActivityCard(activity: ActivityItemListView, userName: String?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = dimensionResource(id = R.dimen.padding_small),
                horizontal = dimensionResource(
                    id = R.dimen.padding_xsmall
                )
            ),
        elevation = 4.dp,
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(
                    vertical = dimensionResource(id = R.dimen.padding_medium),
                    horizontal = dimensionResource(
                        id = R.dimen.padding_xsmall
                    )
                ),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_xsmall)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = activity.distance,
                    style = Typography.displayLarge
                )

                if (userName != null) {
                    Text(text = userName, style = Typography.titleSmall, textAlign = TextAlign.End)
                }
            }

            Row(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_xsmall)),
            ) {
                Text(
                    text = activity.duration,
                    style = Typography.bodyLarge
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(id = R.dimen.padding_small),
                        start = dimensionResource(id = R.dimen.padding_xsmall)
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = activity.title,
                    style = Typography.bodyLarge,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = activity.createdAt,
                    style = Typography.bodySmall,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
