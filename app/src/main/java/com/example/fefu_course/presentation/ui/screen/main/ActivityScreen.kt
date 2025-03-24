package com.example.fefu_course.presentation.ui.screen.main

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.fefu_course.presentation.navigation.ActivityScreen
import com.example.fefu_course.presentation.navigation.addMyActivityScreen
import com.example.fefu_course.presentation.navigation.addUserActivityScreen
import com.example.fefu_course.presentation.ui.theme.Typography
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ActivityScreen(navController: NavController) {
    val tabs = listOf(ActivityScreen.MyActivity, ActivityScreen.UserActivity)
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column {
        TabRow(selectedTabIndex = pagerState.currentPage) {
            tabs.forEachIndexed { index, screen ->
                Tab(
                    text = {
                        Text(stringResource(id = screen.labelResId),
                            style = if (pagerState.currentPage == index) {
                                Typography.titleSmall
                            } else {
                                Typography.bodySmall
                            })
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch { pagerState.animateScrollToPage(index) }
                    }
                )
            }
        }

        HorizontalPager(
            count = tabs.size,
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            val tabNavController = rememberNavController()
            Box(modifier = Modifier.fillMaxSize()) {
                NavHost(
                    navController = tabNavController,
                    startDestination = tabs[page].route
                ) {
                    when (tabs[page]) {
                        ActivityScreen.MyActivity -> addMyActivityScreen()
                        ActivityScreen.UserActivity -> addUserActivityScreen()
                    }
                }
            }
        }
    }

    BackHandler {
        navController.popBackStack()
    }
}
