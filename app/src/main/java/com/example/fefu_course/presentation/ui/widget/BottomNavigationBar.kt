package com.example.fefu_course.presentation.ui.widget

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.fefu_course.presentation.navigation.BottomNavigationRoot
import com.example.fefu_course.presentation.ui.theme.Typography
import com.example.fefu_course.presentation.ui.theme.boldTextColor
import com.example.fefu_course.presentation.ui.theme.hintColor
import com.example.fefu_course.presentation.ui.theme.nullColor
import com.example.fefu_course.presentation.ui.theme.primaryColor

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavigationRoot.Activity,
        BottomNavigationRoot.User
    )
    var selectedItem by remember { mutableStateOf(BottomNavigationRoot.Activity.route) }

    NavigationBar(
        containerColor = Color.Transparent,
        windowInsets = WindowInsets(0, 0, 0, 0)
    ) {
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.iconUnselectedId),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        stringResource(id = screen.labelResId),
                        style = if (selectedItem == screen.route) {
                            Typography.titleSmall
                        } else {
                            Typography.bodySmall
                        }
                    )
                },
                selected = selectedItem == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                    selectedItem = screen.route
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = nullColor,
                    selectedTextColor = boldTextColor,
                    unselectedTextColor = hintColor,
                    selectedIconColor = primaryColor
                )
            )
        }
    }
}
