package com.jdm.home.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val homeRoute = "home"

fun NavGraphBuilder.homeScreen(
    onNavigateToSetting: () -> Unit
) {
    composable(homeRoute) {

    }
}