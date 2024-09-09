package com.example.prerna.utility

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.prerna.R
import com.example.prerna.userinterface.favoritesuvicharscreen.FavoriteQuotesScreen

@Composable
fun TopScreenBar(topNameRes: Int, isEnabled: Boolean = true, imageVector: ImageVector = Icons.Default.ArrowBack, navController: NavController) {
    Column {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = imageVector,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 4.dp)
                    .size(28.dp)
                    .clickable {
                        if (imageVector == Icons.Default.ArrowBack) {
                            navController.navigateUp()
                        }
                    }
            )
            Text(text = stringResource(id = topNameRes),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                fontSize = 28.sp,
                modifier = Modifier.padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 4.dp)
            )
            Icon(imageVector = Icons.Default.FavoriteBorder,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 4.dp)
                    .size(28.dp)
                    .clickable {
                        if(isEnabled) {
                            navController.navigate("favquote")
                        }
                    }
            )

        }
        Divider(thickness = 1.dp)
        Spacer(modifier = Modifier.height(8.dp))

    }

}