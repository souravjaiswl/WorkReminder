package com.example.workreminder.toodo

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Setter(navController: NavController, viewModel: SetterViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        Log.d(
                            "ButtonClicked",
                            "Back Button on the Save Task !"
                        )
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                title = {
                    Text(
                        text = "Add Task",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.Serif,
                    )
                },
                actions = {
                    IconButton(onClick = {
                        Log.d(
                            "ButtonClicked",
                            "Save Task !"
                        )
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.secondary),
            )
        }
    ) { values ->
        var isExpanded by rememberSaveable { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(values),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.primary)
                    .padding(8.dp),
            ) {
                OutlinedTextField(
                    value = viewModel.title,
                    onValueChange = { viewModel.title = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(1.dp),
                    label = { Text(text = "Title") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType =
                        KeyboardType.Password
                    ),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        cursorColor = MaterialTheme.colorScheme.tertiary,
                        focusedBorderColor = MaterialTheme.colorScheme.tertiary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.tertiary,
                    )
                )
                ExposedDropdownMenuBox(
                    expanded = isExpanded,
                    onExpandedChange = { isExpanded = it }
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(5.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Card(
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .size(16.dp),
                                elevation = CardDefaults.cardElevation(0.dp),
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(viewModel.modeColor),
                                ),
                            ) {}
                            OutlinedTextField(
                                value = when (Color(viewModel.modeColor)) {
                                    Color.Green -> "LOW"
                                    Color.Yellow -> "MEDIUM"
                                    else -> "HIGH"
                                },
                                onValueChange = {},
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                                },
                                colors = TextFieldDefaults.outlinedTextFieldColors(
                                    focusedBorderColor = Color.Transparent,
                                    unfocusedBorderColor = Color.Transparent,
                                    disabledBorderColor = Color.Transparent,
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor()
                            )
                        }
                    }
                    ExposedDropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = {
                            isExpanded = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        DropdownMenuItem(
                            text = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Card(
                                        modifier = Modifier
                                            .padding(end = 8.dp)
                                            .size(16.dp),
                                        elevation = CardDefaults.cardElevation(0.dp),
                                        shape = CircleShape,
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color.Green,
                                        ),
                                    ) {}
                                    Text(text = "LOW")
                                }
                            },
                            onClick = {
                                viewModel.modeColor = Color.Green.toArgb()
                                isExpanded = false
                            },
                            modifier = Modifier
                                .fillMaxWidth(),
                        )
                        DropdownMenuItem(
                            text = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Card(
                                        modifier = Modifier
                                            .padding(end = 8.dp)
                                            .size(16.dp),
                                        elevation = CardDefaults.cardElevation(0.dp),
                                        shape = CircleShape,
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color.Yellow,
                                        ),
                                    ) {}
                                    Text(text = "MEDIUM")
                                }
                            },
                            onClick = {
                                viewModel.modeColor = Color.Yellow.toArgb()
                                isExpanded = false
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        DropdownMenuItem(
                            text = {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Card(
                                        modifier = Modifier
                                            .padding(end = 8.dp)
                                            .size(16.dp),
                                        elevation = CardDefaults.cardElevation(0.dp),
                                        shape = CircleShape,
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color.Red,
                                        ),
                                    ) {}
                                    Text(text = "HIGH")
                                }
                            },
                            onClick = {
                                viewModel.modeColor = Color.Red.toArgb()
                                isExpanded = false
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }//---------------------------------------------------------------------------------
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxSize(),
                        shape = RoundedCornerShape(5.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary),
                    ) {
                        OutlinedTextField(
                            value = viewModel.discription,
                            onValueChange = { viewModel.discription = it },
                            modifier = Modifier
                                .fillMaxWidth(),
                            placeholder = { Text(text = "Description") },
                            colors = OutlinedTextFieldDefaults.colors(
                                cursorColor = MaterialTheme.colorScheme.tertiary,
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                disabledBorderColor = Color.Transparent,
                            )
                        )
                    }
                }
            }
        }
    }
}