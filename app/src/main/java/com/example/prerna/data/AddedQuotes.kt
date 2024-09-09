package com.example.prerna.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel



data class addedQuote(
    val suvichar: String
)
val loves = mutableListOf<String>()
val wisdom = mutableListOf<String>()
val motivation = mutableListOf<String>()
val success = mutableListOf<String>()
val funny = mutableListOf<String>()
val friendship = mutableListOf<String>()
val bhajan = mutableListOf<String>(
    "RaghuPatiRaghav"
)
val life = mutableListOf<String>()
class UserAddedFavoriteViewModel : ViewModel() {

    var loveQuotes by mutableStateOf(loves)
    var wisdomQuotes by mutableStateOf(wisdom)
    var motivationQuotes by mutableStateOf(motivation)
    var successQuotes by mutableStateOf(success)
    var funnyQuotes by mutableStateOf(funny)
    var friendshipQuotes by mutableStateOf(friendship)
    var bhajanQuotes by mutableStateOf(bhajan)
    var lifeQuotes by mutableStateOf(life)


    fun deleteQuote(category: String, quote: String) {
        when (category) {
            "bhajan" -> bhajanQuotes = bhajanQuotes.filter { it != quote }.toMutableList()
            "love" -> loveQuotes = loveQuotes.filter { it != quote }.toMutableList()
            "motivation" -> motivationQuotes = motivationQuotes.filter { it != quote }.toMutableList()
            "success" -> successQuotes = successQuotes.filter{it != quote}.toMutableList()
            "wisdom" -> wisdomQuotes = wisdomQuotes.filter{it!=quote}.toMutableList()
            "funny" -> funnyQuotes = funnyQuotes.filter { it != quote}.toMutableList()
            "friendship" -> friendshipQuotes = friendshipQuotes.filter {it != quote}.toMutableList()
            "life" -> lifeQuotes = lifeQuotes.filter { it != quote}.toMutableList()
            // Add cases for other categories
        }
    }


    val quoteLists = listOf(loves, funny, motivation, life, success, bhajan, wisdom, friendship)
    var userfavoriteQuotes by mutableStateOf(listOf<String>())

    fun toggleFavorite(quote: String) {
        if (userfavoriteQuotes.contains(quote)) {
            userfavoriteQuotes = userfavoriteQuotes - quote
        } else {
            userfavoriteQuotes = userfavoriteQuotes + quote
        }
    }

    fun isFavorite(quote: String): Boolean {
        return userfavoriteQuotes.contains(quote)
    }
}



