package com.example.fefu_course.presentation.navigation

import androidx.annotation.DrawableRes
import com.example.fefu_course.R

sealed class Root(val route: String) {
    data object Auth : Root("authScreen")
}

sealed class BottomNavigationRoot(val route: String, val labelResId: Int, @DrawableRes val iconSelectedId: Int, @DrawableRes val iconUnselectedId: Int) {
    data object Activity : BottomNavigationRoot("activityScreen", R.string.bottom_navigation_activity, R.drawable.sports_selected, R.drawable.sports_unselected)
    data object User : BottomNavigationRoot("userScreen", R.string.bottom_navigation_user, R.drawable.person_selected, R.drawable.person_unselected)
}

sealed class AuthScreen(val route: String) {
    data object Welcome : AuthScreen("welcome")
    data object SignUp : AuthScreen("signUp")
    data object SignIn : AuthScreen("signIn")
}

sealed class MainScreen(val route: String) {
    data object ActivityScreen : MainScreen("activity")
    data object UserScreen : MainScreen("user")
}

sealed class ActivityScreen(val route: String, val labelResId: Int) {
    data object UserActivity : ActivityScreen("userActivity", R.string.tab_row_user_activity)
    data object MyActivity : ActivityScreen("myActivity", R.string.tab_row_my_activity)
}

sealed class UserScreen(val route: String) {
    data object ProfileScreen : UserScreen("profile")
}
