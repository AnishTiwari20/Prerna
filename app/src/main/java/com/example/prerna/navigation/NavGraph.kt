package com.example.prerna.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.prerna.data.FavoriteViewModel
import com.example.prerna.data.UserAddedFavoriteViewModel
import com.example.prerna.data.bhajan
import com.example.prerna.data.bhajans
import com.example.prerna.data.friendship
import com.example.prerna.data.friendshipQuotes
import com.example.prerna.data.funny
import com.example.prerna.data.funnyQuotes
import com.example.prerna.data.life
import com.example.prerna.data.lifeQuotes
import com.example.prerna.data.loveQuotes
import com.example.prerna.data.loves
import com.example.prerna.data.motivation
import com.example.prerna.data.motivationQuotes
import com.example.prerna.data.success
import com.example.prerna.data.successQuotes
import com.example.prerna.data.wisdom
import com.example.prerna.data.wisdomQuotes
import com.example.prerna.userinterface.favoritesuvicharscreen.FavoriteQuotesScreen
import com.example.prerna.userinterface.homescreen.HomeScreen
import com.example.prerna.userinterface.homescreen.generateRandomQuote
import com.example.prerna.userinterface.quoteScreens.BhajanScreen
import com.example.prerna.userinterface.quoteScreens.FriendshipScreen
import com.example.prerna.userinterface.quoteScreens.FunnyScreen
import com.example.prerna.userinterface.quoteScreens.LifeScreen
import com.example.prerna.userinterface.quoteScreens.LoveScreen
import com.example.prerna.userinterface.quoteScreens.MotivationScreen
import com.example.prerna.userinterface.quoteScreens.SuccessScreen
import com.example.prerna.userinterface.quoteScreens.WisdomScreen

@Composable
fun NavGraph(navController: NavHostController) {
    val id: Int = generateRandomQuote()
    val favoriteViewModel: FavoriteViewModel = viewModel()
    val userAddedFavoriteViewModel: UserAddedFavoriteViewModel = viewModel()
    NavHost(navController = navController, startDestination = NavRoutes.homeScreen) {
        composable(NavRoutes.homeScreen) { HomeScreen(navController, id) }

        composable(NavRoutes.bhajanScreen) {
            BhajanScreen(
                bhajanList = bhajans,
                navController = navController,
                favoriteViewModel = favoriteViewModel,
                userAddedFavoriteViewModel = userAddedFavoriteViewModel
            )
        }

        composable(NavRoutes.friendshipScreen) {
            FriendshipScreen(
                friendshipquotesList = friendshipQuotes,
                navController = navController,
                favoriteViewModel = favoriteViewModel,
                userAddedFavoriteViewModel  = userAddedFavoriteViewModel
            )
        }

        composable(NavRoutes.loveScreen) {
            LoveScreen(
                loveQuotes = loveQuotes,
                navController, favoriteViewModel,
                userAddedFavoriteViewModel  = userAddedFavoriteViewModel
            )
        }

        composable(NavRoutes.funnyScreen) {
            FunnyScreen(
                funnyQuotesList = funnyQuotes,
                navController,
                favoriteViewModel,
                userAddedFavoriteViewModel  = userAddedFavoriteViewModel
            )
        }

        composable(NavRoutes.lifeScreen) {
            LifeScreen(
                lifeQuotesList = lifeQuotes,
                navController,
                favoriteViewModel,
                userAddedFavoriteViewModel  = userAddedFavoriteViewModel
            )
        }

        composable(NavRoutes.successScreen) {
            SuccessScreen(
                successQuotesList = successQuotes,
                navController,
                favoriteViewModel,
                userAddedFavoriteViewModel  = userAddedFavoriteViewModel
            )
        }

        composable(NavRoutes.motivationScreen) {
            MotivationScreen(
                motivationalQuotesList = motivationQuotes,
                navController,
                favoriteViewModel,
                userAddedFavoriteViewModel  = userAddedFavoriteViewModel
            )
        }

        composable(NavRoutes.wisdomScreen) {
            WisdomScreen(
                wisdomQuotesList = wisdomQuotes,
                navController = navController,
                favoriteViewModel,
                userAddedFavoriteViewModel  = userAddedFavoriteViewModel
            )
        }

        composable(NavRoutes.favQuoteScreen) {
            FavoriteQuotesScreen( navController = navController,  favoriteViewModel = favoriteViewModel, userAddedFavoriteViewModel)}
    }
}