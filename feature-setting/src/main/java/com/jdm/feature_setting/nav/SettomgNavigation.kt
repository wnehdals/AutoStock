package com.jdm.feature_setting.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val settingRoute = "setting"

fun NavGraphBuilder.settingScreen(
    onNavigateToHome: () -> Unit
) {
    composable(settingRoute) {

    }
}