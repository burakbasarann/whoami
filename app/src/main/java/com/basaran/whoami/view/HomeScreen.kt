package com.basaran.whoami.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.basaran.whoami.R
import com.basaran.whoami.navigation.Screen
import com.basaran.whoami.ui.theme.WhoamiTheme

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.backgroundColor))
            .padding(start = 28.dp, end = 28.dp)
    ) {
        Text(
            text = "WHO AM I?",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp, bottom = 24.dp),
            style = MaterialTheme.typography.bodyLarge,
            fontFamily = FontFamily(Font(R.font.caveat)),
            color = colorResource(id = R.color.textColor),
            fontSize = 32.sp,
        )
        CardView()


        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.buttonColor)
            ),
            border = BorderStroke(2.dp, Color.Black),
            onClick = {
                navController.navigate(Screen.Detail.route)
            }) {
            Text(text = "Play Game", fontSize = 16.sp)
        }
    }
}

@Composable
fun CardView() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardDefaults.cardColors(
            contentColor = Color.White,
            containerColor = colorResource(id = R.color.cardViewGray)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 30.dp
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "Guess to your hearts content",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp, start = 32.dp, end = 32.dp),
                textAlign = TextAlign.Center,
                fontSize = 26.sp,
                color = colorResource(id = R.color.textColor),
                fontFamily = FontFamily(Font(R.font.ubuntu_medium)),
            )
            Text(
                text = "Are you a woman or a man? Or are you a fictional character with superpowers? Ask the right questions. Reduce the options and try to guess your character. If you can guess before your friends, you can wear the king's crown.",
                color = colorResource(id = R.color.textColor),
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 4.dp, end = 4.dp, bottom = 8.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WhoamiTheme {
        HomeScreen(navController = rememberNavController())
    }
}