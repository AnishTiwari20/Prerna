package com.example.prerna.userinterface.favoritesuvicharscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.prerna.R
import com.example.prerna.data.FavoriteViewModel
import com.example.prerna.data.UserAddedFavoriteViewModel
import com.example.prerna.utility.QuoteCard
import com.example.prerna.utility.TopScreenBar
import com.example.prerna.utility.UserAddedQuoteCard

@Composable
fun FavoriteQuotesScreen( navController: NavController,
                          favoriteViewModel: FavoriteViewModel,
                          userAddedFavoriteViewModel: UserAddedFavoriteViewModel,
                          ) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopScreenBar(R.string.Favorite, isEnabled = false, navController = navController)
        },
        content = { paddingValues ->
            Column(modifier = Modifier.fillMaxSize()) {
                if(favoriteViewModel.favoriteQuotes.isEmpty() and userAddedFavoriteViewModel.userfavoriteQuotes.isEmpty()) {
                    Column(modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = "No Favorites")
                    }
                }
                else {
                    LazyColumn(contentPadding = paddingValues) {
                        itemsIndexed(favoriteViewModel.favoriteQuotes) { index, quote ->
                            QuoteCard(quote.quoteResId, favoriteViewModel)
                        }

                        itemsIndexed(userAddedFavoriteViewModel.userfavoriteQuotes) { index, quote ->
                            UserAddedQuoteCard(dustBin = false, quote, userAddedFavoriteViewModel, onDelete = {userAddedFavoriteViewModel.deleteQuote("", quote)})
                        }
                    }
                }
            }
        }
    )
    Spacer(Modifier.height(28.dp))
}