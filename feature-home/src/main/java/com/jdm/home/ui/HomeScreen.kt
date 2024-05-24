package com.jdm.home.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
internal fun HomeScreen(
    navController: NavController = rememberNavController(),
    onNavigateToSetting: () -> Unit
) {
    NavHost(navController = navController, startDestination = )
}
