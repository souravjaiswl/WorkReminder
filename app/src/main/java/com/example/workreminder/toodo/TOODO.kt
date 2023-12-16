package com.example.workreminder.toodo

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.sharp.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.workreminder.GifImageExample
import com.example.workreminder.R
import com.example.workreminder.navigation.Screen
import com.example.workreminder.ui.theme.DarkBlue
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

//@Preview(showBackground = true, showSystemUi = true)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TOODO(navController: NavController, viewModel: TODOViewModel = hiltViewModel()) {
    val notes = viewModel.notes.collectAsState(initial = emptyList())
    var isAlertDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Tasks",
                        color = Color.White,
                        fontSize = 26.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.Serif,
                    )
                },
                actions = {
                    IconButton(onClick = {
                        Log.d(
                            "ButtonClicked",
                            "Delete All Task !"
                        )
                        isAlertDialog = true
                    }) {
                        Icon(
                            imageVector = Icons.Sharp.Delete,
                            contentDescription = null,
                            tint = Color.White
                        )
                        if (isAlertDialog) {
                            AlertDialog(
                                onDismissRequest = {},
                                title = {
                                    Text(
                                        text = "Remove All Tasks?",
                                        color = MaterialTheme.colorScheme.tertiary,
                                        fontSize = 26.sp,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.SansSerif,
                                    )
                                },
                                text = {
                                    Text(
                                        text = "Are you sure you want to remove all Tasks? There is no going back after this action.",
                                        color = MaterialTheme.colorScheme.tertiary,
                                        fontWeight = FontWeight.Thin,
                                        fontFamily = FontFamily.Serif,
                                    )
                                },
                                modifier = Modifier.fillMaxWidth(),
                                dismissButton = {
                                    Button(
                                        onClick = {
                                            isAlertDialog = false
                                        }
                                    ) {
                                        Text(
                                            text = "No",
                                            color = MaterialTheme.colorScheme.tertiary
                                        )
                                    }
                                },
                                confirmButton = {
                                    Button(onClick = {
                                        viewModel.deleteAllNotes()
                                        Toast.makeText(context, "Data Deleted!", Toast.LENGTH_SHORT).show()
                                        isAlertDialog = false
                                    }) {
                                        Text(
                                            text = "Yes",
                                            color = MaterialTheme.colorScheme.tertiary
                                        )
                                    }
                                },
                                containerColor = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.secondary),
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.TopCenter,
        ) {
            GifImageExample(
                data = if (isSystemInDarkTheme()) R.drawable.bbrain else R.drawable.wbrain,
                backgroundColor = Color.Black,
                contentAlignment = Alignment.Center
            )
            LazyColumn(
                modifier = Modifier
                    .padding(1.dp)
            ) {
                items(notes.value) {
                    val DEL = SwipeAction(
                        icon = {
                            Icon(
                                modifier = Modifier
                                    .padding(16.dp),
                                imageVector = Icons.Filled.DeleteForever,
                                contentDescription = "",
                                tint = Color.Red
                            )
                        },
                        background = Color.Transparent,
                        isUndo = true,
                        onSwipe = {
                            viewModel.deleteNote(note = it)
                        },
                    )
                    SwipeableActionsBox(
                        endActions = listOf(DEL),
                        swipeThreshold = 150.dp,
                    ) {
                        // Swipeable content goes here.
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 4.dp, vertical = 4.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Transparent,
                                contentColor = MaterialTheme.colorScheme.tertiary,
                            ),
                            onClick = { navController.navigate(Screen.UpdateTask.getByID(it.id)) },
                            border = BorderStroke(1.dp, MaterialTheme.colorScheme.tertiary),
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .weight(3f),
                                ) {
                                    Text(
                                        text = it.DataTitle,
                                        fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                                        fontWeight = FontWeight.Bold,
                                        fontFamily = FontFamily.Serif,
                                        color = MaterialTheme.colorScheme.tertiary,
                                    )
                                    Text(
                                        text = it.DataDescription,
                                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                                        fontWeight = FontWeight.Thin,
                                        fontFamily = FontFamily.Serif,
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 2,
                                        color = MaterialTheme.colorScheme.tertiary,
                                    )
                                }
                                Card(
                                    modifier = Modifier
                                        .padding(end = 16.dp)
                                        .size(20.dp),
                                    elevation = CardDefaults.cardElevation(0.dp),
                                    shape = CircleShape,
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(it.DataColor)
                                    ),
                                ){}
                            }
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                IconButton(
                    onClick = {
                        Log.d("AddTASK", "Add Task Button Clicked!")
                        navController.navigate(Screen.Setter.route)
                    },
                    modifier = Modifier
                        .padding(30.dp)
                        .background(color = DarkBlue, shape = CircleShape)
                        .then(Modifier.size(50.dp))
//                .border(1.dp, Color.Red, shape = CircleShape)
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = "Icon Button",
                        tint = Color.White
                    )
                }
            }
        }
    }
}