package com.jdm.designsystem

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jdm.designsystem.theme.AutoStockTheme

@Composable
fun BaseLayout(
    backgroundColor: Color = AutoStockTheme.colors.white,
    bottomNavItem: List<BottomNavItem>?,
) {
    val navController: NavHostController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent,
        contentColor = backgroundColor,
        snackbarHost = { SnackbarHost(snackBarHostState) },
        bottomBar = {
            if (!bottomNavItem.isNullOrEmpty()) {
                NavigationBar {
                    bottomNavItem.forEachIndexed { _, navigationItem ->
                        HNavigationBarItem(
                            selected = navigationItem.route == currentDestination?.route,
                            onClick = {
                                navController.navigate(navigationItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = navigationItem.icon,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(24.dp)
                                        .height(24.dp)
                                )
                            },
                            selectedIcon = {
                                Icon(
                                    imageVector = navigationItem.icon,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(24.dp)
                                        .height(24.dp)
                                )
                            },
                            label = {
                                Text(text = navigationItem.label, style = JdmTheme.typography.L_XS)
                            }
                        )
                    }
                }
            }
        },
    ) { paddingValue ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Search.route,
            modifier = Modifier.padding(paddingValues = paddingValue)
        ) {
            composable(BottomNavItem.Search.route) {
                SearchScreen()
            }
            composable(BottomNavItem.Favorite.route) {
                FavoriteScreen()
            }
        }
    }
}
