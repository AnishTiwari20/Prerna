package com.example.prerna.utility

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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prerna.R
import com.example.prerna.data.UserAddedFavoriteViewModel
import com.example.prerna.data.quote


@Composable
fun UserAddedQuoteCard(
    dustBin: Boolean,
    quote: String,
    userAddedFavoriteViewModel: UserAddedFavoriteViewModel,
    onDelete: () ->Unit,
) {

    val isFavorite = userAddedFavoriteViewModel.isFavorite(quote)
    val context = LocalContext.current

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
            if(dustBin) {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End) {
                    Icon(modifier = Modifier.padding(top = 16.dp, end = 16.dp)
                        .clickable {
                            onDelete()
                        },
                        imageVector = Icons.Default.Delete, contentDescription = null)
                }
            }
            Column(
                modifier = Modifier.weight(5f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = quote,
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
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
                            shareQuote(context = context, quote)
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
                            userAddedFavoriteViewModel.toggleFavorite(quote)
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
