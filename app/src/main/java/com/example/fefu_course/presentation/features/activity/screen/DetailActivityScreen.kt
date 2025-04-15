package com.example.fefu_course.presentation.features.activity.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavController
import com.example.fefu_course.R
import com.example.fefu_course.presentation.features.activity.viewmodel.ActivityViewModel
import com.example.fefu_course.presentation.ui.theme.Typography
import com.example.fefu_course.presentation.ui.theme.backgroundSecondary
import com.example.fefu_course.presentation.ui.theme.primaryColor
import com.example.fefu_course.presentation.ui.widget.MultilineTextField
import com.example.fefu_course.presentation.ui.widget.ScaffoldWithOptionalAppBar
import com.example.fefu_course.presentation.vo.CommentView

@Composable
fun DetailActivityScreen(
    navController: NavController,
    activityId: Int,
    activityViewModel: ActivityViewModel
) {
    val activityState by activityViewModel.activityState.collectAsState()

    var newComment by remember { mutableStateOf("") }

    LaunchedEffect(activityId) {
        activityViewModel.getActivityById(activityId)
    }

    val scrollState = rememberScrollState()

    ScaffoldWithOptionalAppBar(
        showAppBar = true,
        title = activityState.title,
        onClickBackButton = { navController.navigateUp() },
        actions = {
            if (activityState.accountName == null) {
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = null,
                        tint = primaryColor
                    )
                }

                IconButton(onClick = { }) {
                    Icon(
                        Icons.Default.Share,
                        contentDescription = null,
                        tint = primaryColor
                    )
                }
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(id = R.dimen.padding_medium))
                    .verticalScroll(scrollState)
            ) {
                if (activityState.accountName != null) {
                    Text(text = activityState.accountName ?: "", style = Typography.titleSmall)
                }
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_xsmall)))
                Column {
                    Text(
                        text = activityState.distance,
                        style = Typography.displayLarge
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_xsmall)))

                    Text(
                        text = activityState.createdDate,
                        style = Typography.bodySmall
                    )

                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_medium)))

                    Text(
                        text = activityState.duration,
                        style = Typography.displayLarge
                    )
                }
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_xsmall)))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_xsmall))
                ) {
                    Text(
                        text = stringResource(R.string.detail_activity_start_text),
                        style = Typography.bodySmall
                    )
                    Text(
                        text = activityState.startTime,
                        style = Typography.bodySmall
                    )

                    Text(
                        text = stringResource(R.string.time_text_separator),
                        style = Typography.bodySmall
                    )

                    Text(
                        text = stringResource(R.string.detail_activity_end_text),
                        style = Typography.bodySmall
                    )
                    Text(
                        text = activityState.endTime,
                        style = Typography.bodySmall
                    )
                }
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_medium)))
                MultilineTextField(
                    value = newComment,
                    onValueChange = { newComment = it }, label = stringResource(R.string.detail_activity_comment),
                    validate = null,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Send
                    ),
                    keyboardActions = KeyboardActions(
                        onSend = {
                            if (newComment.isNotBlank()) {
                                val commentView =
                                    CommentView(
                                        id = activityState.comments.size + 1,
                                        content = newComment
                                    )
                                activityViewModel.addComment(commentView)
                                newComment = ""
                            }
                        }
                    )
                )

                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_xsmall)))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacer_xsmall))
                ) {
                    activityState.comments.forEach { comment ->
                        CommentBox(comment = comment.content)
                    }
                }
            }
        }
    )
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
