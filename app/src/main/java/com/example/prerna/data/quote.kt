package com.example.prerna.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class quote(
    val quoteResId: Int
)
class FavoriteViewModel : ViewModel() {
    var favoriteQuotes by mutableStateOf(listOf<quote>())

    fun toggleFavorite(quote: quote) {
        if (favoriteQuotes.contains(quote)) {
            favoriteQuotes = favoriteQuotes - quote
        } else {
            favoriteQuotes = favoriteQuotes + quote
        }
    }

    fun isFavorite(quote: quote): Boolean {
        return favoriteQuotes.contains(quote)
    }
}


