package com.example.fefu_course.presentation.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.fefu_course.R
import com.example.fefu_course.presentation.ui.theme.Typography
import com.example.fefu_course.presentation.ui.theme.backgroundSecondary
import com.example.fefu_course.presentation.vo.CommentView

@Composable
fun CommentList(
    comments: List<CommentView>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_xsmall))
    ) {
        items(comments,
            key = { comment ->
                comment.id
            }) { comment ->
            CommentBox(comment = comment.content)
        }
    }
}

@Composable
fun CommentBox(comment: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_xxsmall))
            .background(backgroundSecondary)
            .padding(dimensionResource(id = R.dimen.padding_small))
    ) {
        Text(text = comment, style = Typography.bodyMedium)
    }
}
