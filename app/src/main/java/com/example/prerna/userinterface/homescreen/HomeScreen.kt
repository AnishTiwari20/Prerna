package com.example.prerna.userinterface.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prerna.R
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.prerna.data.Category
import com.example.prerna.data.FavoriteViewModel
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
import com.example.prerna.data.myCategoryList
import com.example.prerna.data.quote
import com.example.prerna.data.success
import com.example.prerna.data.successQuotes
import com.example.prerna.data.wisdom
import com.example.prerna.data.wisdomQuotes
import com.example.prerna.utility.TopScreenBar
import com.example.prerna.utility.shareQuote


@Composable
fun HomeScreen(navController: NavController, id: Int) {
    val homeScreenViewModel: HomeScreenViewModel = viewModel()
    val homeScreenUiState by homeScreenViewModel.homeScreenState.collectAsState()


    var showDialog by remember { mutableStateOf(false) }
    var textFieldValue by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }
    val options = listOf("प्रेम", "हास्य", "प्रेरणादायक", "सफलता", "भजन", "ज्ञान", "मित्रता", "जीवन")




    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopScreenBar(R.string.app_name, imageVector = Icons.Default.Menu, navController = navController)
        },
        content = {
            // Main content of the screen
            Column(modifier = Modifier.padding(it)) {
                AajKaSuvichar(homeScreenUiState = homeScreenUiState, id)
                CategoryPart(navController)

                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text("Add Quote", Modifier.padding(start = 12.dp)) },
                        containerColor = MaterialTheme.colorScheme.background,
                        text = {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                OutlinedTextField(
                                    modifier = Modifier.fillMaxWidth(),
                                    label = {
                                            Row(modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.Start
                                                ) {
                                                Text(text = "Pen down your Quote")
                                                Spacer(Modifier.width(8.dp))
                                                Icon(imageVector = Icons.Default.Edit, contentDescription = null, modifier = Modifier.size(20.dp))
                                            }

                                    },
                                    value = textFieldValue,
                                    onValueChange = {textFieldValue = it}
                                )
                                // OutlinedTextField with DropdownMenu
                                OutlinedTextField(
                                    modifier = Modifier.fillMaxWidth()
                                        .clickable {
                                                   expanded = true
                                    },
                                    value = selectedOption,
                                    onValueChange = { selectedOption = it },
                                    label = { Text("Select Category") },
                                    readOnly = true,
                                    trailingIcon = {
                                        Icon(
                                            imageVector = Icons.Filled.ArrowDropDown,
                                            contentDescription = null,
                                            modifier = Modifier.clickable { expanded = true }
                                        )
                                    }
                                )

                                Spacer(Modifier.height(12.dp))

                                // DropdownMenu
                                DropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false }
                                ) {
                                    options.forEach { option ->
                                        DropdownMenuItem(
                                            text = { Text(option) },
                                            onClick = {
                                                selectedOption = option
                                                expanded = false
                                            }
                                        )
                                    }
                                }
                            }
                        },
                        //"प्रेम", "हास्य", "प्रेरणादायक", "सफलता", "भजन", , "मित्रता", "जीवन"
                        confirmButton = {
                            TextButton(onClick = {
                                when (selectedOption) {
                                    "प्रेम" -> {
                                        loves.add(textFieldValue.toString())
                                    }
                                    "हास्य" -> {
                                        funny.add(textFieldValue.toString())
                                    }
                                    "प्रेरणादायक" -> {
                                        motivation.add(textFieldValue.toString())
                                    }
                                    "सफलता" -> {
                                        success.add(textFieldValue.toString())
                                    }
                                    "भजन" -> {
                                        bhajan.add(textFieldValue)
                                    }
                                    "ज्ञान" -> {
                                        wisdom.add(textFieldValue.toString())
                                    }
                                    "जीवन" -> {
                                        life.add(textFieldValue)
                                    }
                                    "मित्रता" -> {
                                        friendship.add(textFieldValue.toString())
                                    }

                                }

                                showDialog = false
                            }) {
                                Text("Add")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDialog = false }) {
                                Text("Cancel")
                            }
                        }
                    )
                }
            }
        },
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .padding(top = 0.dp, start = 0.dp, end = 8.dp, bottom = 8.dp)
                    .size(64.dp) // Adjust size as needed
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(12.dp)
                    ) // Adjust corner radius as needed
                    .clickable(onClick = {
                        showDialog = true
                    }),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Icon",
                    tint = Color.White, // Adjust padding as needed
                    modifier = Modifier.size(40.dp)
                )
            }
        }

    )
}



@Composable
fun AajKaSuvichar(homeScreenUiState: HomeScreenState, id:Int) {
    val context = LocalContext.current

    val quoteText = stringResource(id = id)

    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
        shape = RoundedCornerShape(36.dp),
        modifier = Modifier.padding(12.dp),
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {

            Text(text = stringResource(id = R.string.aaj_ka_suvichar_heading),
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(8.dp)
            )

            Card(
                shape = RoundedCornerShape(36.dp)
            ) {

                Column() {
                    Text(text = stringResource(id = id),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(500),
                        modifier = Modifier.padding(start = 18.dp, top = 36.dp, end = 16.dp)
                    )

                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        OutlinedButton(onClick = {
                                                 shareQuote(context = context, "*आज का सुविचार:* - ${quoteText}")
                        },
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(stringResource(id = R.string.copy), style = MaterialTheme.typography.bodyLarge)
                        }

                        Button(onClick = {},
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(stringResource(id = homeScreenUiState.aajKeSuvicharKiCategory), style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }

            }
        }
    }
}

fun generateRandomQuote(): Int {
    //8 quote files are there
    val randomWords = listOf(
        friendshipQuotes.random(),
        funnyQuotes.random(),
        lifeQuotes.random(),
        loveQuotes.random(),
        motivationQuotes.random(),
        successQuotes.random(),
        wisdomQuotes.random(),
        bhajans.random()
    )
    val finalRandomQuote = randomWords.random()
    return finalRandomQuote.quoteResId
}

@Composable
fun CategoryPart(navController: NavController) {
    val listOfCategory = myCategoryList

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(listOfCategory) { category ->
            CategoryBox(
                category = category,
                modifier = Modifier.padding(8.dp),
                route = category.route,
                navController = navController
                // Adjust the padding as needed
            )
        }
    }
}

@Composable
fun CategoryBox(category: Category, modifier: Modifier = Modifier, route: String, navController: NavController) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .clickable {
                navController.navigate(route)
            }
            .padding(12.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = category.imageRes), contentDescription = null, modifier = Modifier.size(100.dp))
            Spacer(Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(id = category.nameRes), fontSize = 20.sp)
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null, modifier = Modifier.size(16.dp))
            }
        }
    }
}





