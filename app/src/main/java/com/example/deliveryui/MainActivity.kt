package com.example.deliveryui

import com.example.deliveryui.ui.navigation.BottomBarScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.example.deliveryui.model.Food
import com.example.deliveryui.ui.theme.DeliveryUITheme
import com.example.deliveryui.ui.theme.Orange
import com.example.deliveryui.ui.theme.Rose

class MainActivity : ComponentActivity() {
    @ExperimentalCoilApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            DeliveryUITheme {
                Scaffold(
                    topBar = {
                        TopBar()
                    },
                    floatingActionButton = {
                        FloatingSearchButton()
                    },
                    isFloatingActionButtonDocked = true,
                    floatingActionButtonPosition = FabPosition.Center,
                    bottomBar = {
                        BottomBar(
                            navController = navController,
                            backgroundColor = Color.White,
                            selectedIconColor = Rose,
                            unSelectedIconColor = Color.LightGray
                        )
                    }
                ) {
                    BottomNavHost(navController)
                }
            }
        }
    }

    @ExperimentalCoilApi
    @ExperimentalMaterialApi
    @androidx.compose.runtime.Composable
    private fun BottomNavHost(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = BottomBarScreen.Home.route
        ) {
            composable(route = BottomBarScreen.Home.route) {
                HomeScreen()
            }
            screen("favorite", BottomBarScreen.Favorites.route)
            screen("notification", BottomBarScreen.Notification.route)
            screen("shopping cart", BottomBarScreen.Cart.route)
        }
    }

    @ExperimentalCoilApi
    @ExperimentalMaterialApi
    @Composable
    private fun HomeScreen() {
        Column(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
            Advertising(backgroundImg = R.drawable.delivery, onClick = {})
            Text(
                text = "Categories",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 16.dp)
            )
            ChipSection(
                listOf("Sandwich", "Toast", "Pizza", "Hamburger", "bread"),
                icon = R.drawable.hamburger
            )

            ViewAll()

            val list = mutableListOf<Food>()
            repeat(10) {
                list.add(
                    Food(
                        image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdjf9QfyrfE2bjPoY5qDCVKfxMZmMaIvAU8Q&usqp=CAU.jpg",
                        name = "Pizza",
                        category = "FastFood",
                        icon = "https://www.svgrepo.com/show/297209/meat.svg",
                        price = "14.10"
                    )
                )
            }

            LazyRow {
                items(list) {
                    CustomCard(food = it, onClick = {})
                }
            }

            Spacer(modifier = Modifier.height(100.dp))
        }

    }


    private fun NavGraphBuilder.screen(text: String, route: String) {
        composable(route = route) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = .7f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = text)
            }
        }
    }


    @ExperimentalMaterialApi
    @androidx.compose.runtime.Composable
    fun BottomBar(
        navController: NavHostController,
        modifier: Modifier = Modifier,
        backgroundColor: Color = MaterialTheme.colors.primarySurface,
        selectedIconColor: Color = LocalContentColor.current,
        unSelectedIconColor: Color = LocalContentColor.current.copy(alpha = ContentAlpha.medium)
    ) {
        val screens = listOf(
            BottomBarScreen.Home,
            BottomBarScreen.Favorites,
            BottomBarScreen.Notification,
            BottomBarScreen.Cart
        )
        val backStackEntry = navController.currentBackStackEntryAsState()

        BottomNavigation(
            modifier = modifier,
            backgroundColor = backgroundColor,
            elevation = 5.dp
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    backStackEntry = backStackEntry,
                    navController = navController,
                    selectedIconColor = selectedIconColor,
                    unSelectedIconColor = unSelectedIconColor
                )

            }

        }

    }

    @ExperimentalMaterialApi
    @androidx.compose.runtime.Composable
    private fun RowScope.AddItem(
        screen: BottomBarScreen,
        backStackEntry: State<NavBackStackEntry?>,
        navController: NavHostController,
        selectedIconColor: Color,
        unSelectedIconColor: Color
    ) {
        val selected = screen.route == backStackEntry.value?.destination?.route

        BottomNavigationItem(
            selected = selected,
            onClick = {

                if (!selected)
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
            },
            selectedContentColor = selectedIconColor,
            unselectedContentColor = unSelectedIconColor,
            icon = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = if (selected) 2.dp else 0.dp)
                ) {
                    if (screen.badgeCount > 0) {
                        BadgeBox(
                            backgroundColor = Rose,
                            badgeContent = {
                                Text(
                                    text = screen.badgeCount.toString(),
                                    color = Color.White
                                )
                            }
                        ) {
                            Icon(imageVector = screen.icon, contentDescription = screen.title)
                        }
                    } else {
                        Icon(imageVector = screen.icon, contentDescription = screen.title)
                    }

                    if (selected)
                        Text(text = screen.title, style = MaterialTheme.typography.subtitle1)
                }
            }
        )

    }
}