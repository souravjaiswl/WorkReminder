package com.example.workreminder.toodo

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.workreminder.navigation.Screen
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Finder(navController: NavController) {
    var search by remember { mutableStateOf("") }
    val lists = listOf(
        "Lion",
        "Lon",
        "Tiger",
        "Tire",
        "Camel",
        "Owl",
        "Cow",
        "Monkey",
        "Rinki",
        "Donkey",
        "Friday",
        "Monday",
        "Cheese",
        "Code"
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                actions = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        CustomSearchView(
                            text = search,
                            onValueChange = {
                                search = it
                            }
                        )
                        IconButton(onClick = {
                            Log.d(
                                "ButtonClicked",
                                "Exit from Search !"
                            )
                            navController.navigate(Screen.TOODO.route)
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "Exit from Search",
                                tint = Color.White
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.secondary),
            )
        }
    ) { values ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primary)
                .padding(values),
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(20.dp),
            ) {
                val filterList: List<String> = if (search.isEmpty()) {
                    lists
                } else {
                    val result = arrayListOf<String>()
                    for (temp in lists) {
                        if (temp.lowercase(Locale.getDefault()).contains(
                                search.lowercase(Locale.getDefault())
                            )
                        ) {
                            result.add(temp)
                        }
                    }
                    result
                }
                items(filterList) { data ->
                    Text(
                        text = data,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchView(
    text: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = { onValueChange(it) },
        placeholder = { Text(text = "Search") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.White
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = Color.White,
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        singleLine = true,
    )
}