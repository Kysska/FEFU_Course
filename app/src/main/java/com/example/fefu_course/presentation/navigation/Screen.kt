package com.example.fefu_course.presentation.navigation

import androidx.annotation.DrawableRes
import com.example.fefu_course.R

sealed class Root(val route: String) {
    data object Auth : Root("authScreen")
}

sealed class BottomNavigationRoot(val route: String, val labelResId: Int, @DrawableRes val iconSelectedId: Int, @DrawableRes val iconUnselectedId: Int) {
    data object Activity : BottomNavigationRoot("activityScreen", labelResId = R.string.bottom_navigation_activity, iconSelectedId = R.drawable.sports_selected, iconUnselectedId = R.drawable.sports_unselected)
    data object User : BottomNavigationRoot("userScreen", labelResId = R.string.bottom_navigation_user, iconSelectedId = R.drawable.person_selected, iconUnselectedId = R.drawable.person_unselected)

    companion object {
        val bottomBarRoutes = setOf(BottomNavigationRoot.Activity, BottomNavigationRoot.User)
    }
}

sealed class AuthScreen(val route: String) {
    data object Welcome : AuthScreen("welcome")
    data object SignUp : AuthScreen("signUp")
    data object SignIn : AuthScreen("signIn")
}

sealed class MainScreen(val route: String) {
    data object ActivityScreen : MainScreen("activity")
    data object UserScreen : MainScreen("user")
    data object ActivityDetail : MainScreen(ACTIVITY_DETAIL_ROOT) {
        fun createRoute(id: Int): String {
            return "activity_detail/$id"
        }
    }
    companion object {
        private const val ACTIVITY_DETAIL_ROOT = "activity_detail/{activityId}"
        const val ACTIVITY_DETAIL_ID = "activityId"
    }
}

sealed class ActivityTab(val route: String, val labelResId: Int) {
    data object UserActivity : ActivityTab("userActivity", R.string.user_activity_tab_row)
    data object MyActivity : ActivityTab("myActivity", R.string.my_activity_tab_row)

    companion object {
        val tabs = listOf(ActivityTab.MyActivity, ActivityTab.UserActivity)
    }
}

sealed class UserScreen(val route: String) {
    data object ProfileScreen : UserScreen("profile")
}
