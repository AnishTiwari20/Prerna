package com.example.prerna.utility

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prerna.R
import com.example.prerna.data.FavoriteViewModel
import com.example.prerna.data.quote


@Composable
fun QuoteCard(quoteRes: Int, favoriteViewModel: FavoriteViewModel) {
    val isFavorite = favoriteViewModel.isFavorite(quote(quoteRes))
    val context = LocalContext.current
    val quoteText = stringResource(id = quoteRes)

    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(16.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .size(width = 400.dp, height = 260.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.weight(6f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = quoteText,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
            Row(modifier = Modifier
                .background(color = MaterialTheme.colorScheme.secondaryContainer)
                .padding(8.dp)
                .fillMaxWidth()
                .weight(1.2f),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(modifier = Modifier.weight(2f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween) {

                    Row(modifier = Modifier
                        .clickable {
                            shareQuote(context = context, quoteText)
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Share,
                            modifier = Modifier.size(16.dp),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSecondaryContainer)
                        Spacer(Modifier.width(8.dp))
                        Text(text = stringResource(id = R.string.share), color = MaterialTheme.colorScheme.onSecondaryContainer)
                    }



                    //Like wala
                    Row(
                        modifier = Modifier.clickable {
                            favoriteViewModel.toggleFavorite(quote = quote(quoteRes))
                        }
                    ) {
                        // Display the appropriate icon based on the state
                        Icon(
                            imageVector = if(!isFavorite) Icons.Default.FavoriteBorder else Icons.Default.Favorite,
                            modifier = Modifier.size(20.dp),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = stringResource(id = R.string.like),
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }
                Row(modifier = Modifier.weight(3f).fillMaxWidth(),
                    horizontalArrangement = Arrangement.End) {
                    Text(
                        text = "quotes by Prerna",
                        fontWeight =  FontWeight.Thin,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

            }


        }
    }

}

fun shareQuote(context: Context, text: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    context.startActivity(shareIntent)
}
