package com.example.deliveryui.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val badgeCount: Int = 0
) {

    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Favorites : BottomBarScreen(
        route = "favorites",
        title = "Favorites",
        icon = Icons.Default.Favorite
    )

    object Notification : BottomBarScreen(
        route = "notification",
        title = "Notification",
        icon = Icons.Default.Notifications,
    )

    object Cart : BottomBarScreen(
        route = "shoppingCart",
        title = "Cart",
        icon = Icons.Default.ShoppingCart,
        badgeCount = 5
    )

}
