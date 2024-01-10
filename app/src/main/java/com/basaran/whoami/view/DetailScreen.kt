package com.basaran.whoami.view

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import com.basaran.whoami.R
import com.basaran.whoami.navigation.Screen

@Composable
fun DetailScreen(navController: NavController, savedStateHandle: SavedStateHandle) {

    val selectedItem = remember { mutableStateListOf<String>() }
    val mutableListToSave = ArrayList(selectedItem)
    savedStateHandle["myArrayList"] = mutableListToSave

    val list = mutableListOf(
        "Actors",
        "Animals",
        "Footballer",
        "Historical Figures",
        "Marvel",
        "Movie Characters",
        "Politicians",
        "Singers"
    )
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.backgroundColor))
            .padding(start = 28.dp, end = 28.dp),
        //     horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "WHO AM I?",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp, bottom = 8.dp),
            style = MaterialTheme.typography.bodyLarge,
            fontFamily = FontFamily(Font(R.font.caveat)),
            color = colorResource(id = R.color.textColor),
            fontSize = 32.sp,
        )

        ShowLazyList(list, selectedItem)

        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.buttonColor)
            ),
            border = BorderStroke(2.dp, Color.Black),
            onClick = {
                if (selectedItem.isEmpty()) {
                    Toast.makeText(context, "Make at least one selection.", Toast.LENGTH_LONG)
                        .show()
                } else {
                    navController.navigate(
                        Screen.Game.route
                    )
                }
            }) {
            Text(text = "Play Game", fontSize = 16.sp)
        }
    }
}

@Composable
fun ShowLazyList(categories: MutableList<String>, selectedItem: MutableList<String>) {
    LazyColumn {
        items(categories) { category ->
            CheckItem(category, selectedItem)
        }
    }
}

@Composable
fun CheckItem(category: String, selectedItem: MutableList<String>) {
    Row {
        Checkbox(
            checked = selectedItem.contains(category),
            colors = CheckboxDefaults.colors(
                uncheckedColor = Color.White,
                checkedColor = Color.Blue
            ),
            onCheckedChange = {
                if (selectedItem.contains(category)) {
                    selectedItem.remove(category)
                } else {
                    selectedItem.add(category)
                }
            }
        )
        Text(
            text = category,
            modifier = Modifier.align(Alignment.CenterVertically),
            color = colorResource(id = R.color.textColor)
        )
    }
}