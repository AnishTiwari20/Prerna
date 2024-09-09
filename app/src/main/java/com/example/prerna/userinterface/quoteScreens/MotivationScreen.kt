package com.example.prerna.userinterface.quoteScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.prerna.R
import com.example.prerna.data.FavoriteViewModel
import com.example.prerna.data.UserAddedFavoriteViewModel
import com.example.prerna.data.quote
import com.example.prerna.utility.QuoteCard
import com.example.prerna.utility.TopScreenBar
import com.example.prerna.utility.UserAddedQuoteCard


@Composable
fun MotivationScreen(
    motivationalQuotesList: List<quote>,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel,
    userAddedFavoriteViewModel: UserAddedFavoriteViewModel
) {
    var motivationQuotes = userAddedFavoriteViewModel.motivationQuotes
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopScreenBar(R.string.motivational, navController = navController)
        },
        content = { paddingValues ->
            LazyColumn(contentPadding = paddingValues) {
                // Items from the first list (bhajanList)
                itemsIndexed(motivationalQuotesList) { index, quote ->
                    QuoteCard(quote.quoteResId, favoriteViewModel)
                }

                if(!motivationQuotes.isEmpty()) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 32.dp, bottom = 16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Your Quotes", fontSize = 24.sp)
                        }
                    }
                }

                // Items from the second list (userBhajans)
                itemsIndexed(motivationQuotes) { index, quote ->
                    UserAddedQuoteCard(dustBin = true, quote, userAddedFavoriteViewModel, onDelete = {userAddedFavoriteViewModel.deleteQuote("motivation", quote)})
                }
            }
        }
    )
    Spacer(Modifier.height(8.dp))
}