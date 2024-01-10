package com.basaran.whoami.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import com.basaran.whoami.R
import com.basaran.whoami.util.AnimatedCountdownTimer

@Composable
fun GameScreen(savedStateHandle: SavedStateHandle) {

    val selectedCategory = savedStateHandle.get<ArrayList<String>>("myArrayList") ?: emptyList()
    var timeLeft by remember { mutableIntStateOf(3) }
    val coroutineScope = rememberCoroutineScope()
    val animatedCountdownTimer = remember { AnimatedCountdownTimer(coroutineScope) }
    val actorList = mutableListOf<Pair<String, Int>>()
    val animalsList = mutableListOf<Pair<String, Int>>()
    val footballerList = mutableListOf<Pair<String, Int>>()
    val historicalFiguresList = mutableListOf<Pair<String, Int>>()
    val marvelList = mutableListOf<Pair<String, Int>>()
    val movieCharactersList = mutableListOf<Pair<String, Int>>()
    val politiciansList = mutableListOf<Pair<String, Int>>()
    val singersList = mutableListOf<Pair<String, Int>>()
    val selectedResultCategory = mutableListOf<Pair<String, Int>>()

    for (category in selectedCategory) {
        when (category) {
            "Actors" -> {
                actorMockData(actorList as ArrayList<Pair<String, Int>>)
                selectedResultCategory.addAll(actorList)
            }

            "Animals" -> {
                animalMockData(animalsList as ArrayList<Pair<String, Int>>)
                selectedResultCategory.addAll(animalsList)
            }

            "Footballer" -> {
                footballerMockData(footballerList as ArrayList<Pair<String, Int>>)
                selectedResultCategory.addAll(footballerList)
            }

            "Historical Figures" -> {
                historicalFiguresMockData(historicalFiguresList as ArrayList<Pair<String, Int>>)
                selectedResultCategory.addAll(historicalFiguresList)
            }

            "Marvel" -> {
                marvelMockData(marvelList as ArrayList<Pair<String, Int>>)
                selectedResultCategory.addAll(marvelList)
            }

            "Movie Characters" -> {
                movieCharactersMockData(movieCharactersList as ArrayList<Pair<String, Int>>)
                selectedResultCategory.addAll(movieCharactersList)
            }

            "Politicians" -> {
                politiciansMockData(politiciansList as ArrayList<Pair<String, Int>>)
                selectedResultCategory.addAll(politiciansList)
            }

            "Singers" -> {
                singersMockData(singersList as ArrayList<Pair<String, Int>>)
                selectedResultCategory.addAll(singersList)
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.backgroundColor))
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

        if (timeLeft == 0) {

            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Row {
                    Text(
                        text = "Selected categories :",
                        modifier = Modifier.padding(8.dp),
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.textColor)
                    )
                    Row(Modifier.horizontalScroll(ScrollState(1), true)) {

                        for (item in selectedCategory) {
                            Text(
                                text = item,
                                modifier = Modifier.padding(8.dp),
                                fontSize = 12.sp,
                                color = colorResource(id = R.color.textColor)
                            )
                        }
                    }

                }
                Column(
                    modifier = Modifier.fillMaxSize().padding(top = 50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val random = selectedResultCategory.random()
                    Image(
                        modifier = Modifier
                            .padding(bottom = 32.dp)
                            .width(200.dp)
                            .height(200.dp),
                        painter = painterResource(id = random.second),
                        contentDescription = ""
                    )
                    Text(
                        text = random.first,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.textColor),
                    )
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.graphicsLayer {
                    scaleX = animatedCountdownTimer.scale
                    scaleY = animatedCountdownTimer.scale
                    alpha = animatedCountdownTimer.alpha
                },
                text = if (timeLeft != 0) "$timeLeft" else "",
                fontSize = 80.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )


        }

        LaunchedEffect(Unit) {
            animatedCountdownTimer.start(3, 0) {
                timeLeft = it
            }
        }
    }
}

fun actorMockData(actorList: ArrayList<Pair<String, Int>>) {
    actorList.addAll(
        listOf(
            Pair("Brad Pitt", R.drawable.brad_pitt),
            Pair("Tom Cruise", R.drawable.brad_pitt),
            Pair("Robert Downey Jr.", R.drawable.brad_pitt),
            Pair("Leonardo DiCaprio", R.drawable.brad_pitt),
            Pair("Will Smith", R.drawable.brad_pitt),
            Pair("Dwayne Johnson", R.drawable.brad_pitt),
            Pair("Mark Wahlberg", R.drawable.brad_pitt),
            Pair("Chris Hemsworth", R.drawable.brad_pitt),
            Pair("Ryan Reynolds", R.drawable.brad_pitt),
            Pair("Ben Affleck", R.drawable.brad_pitt),
            Pair("Chris Pratt", R.drawable.brad_pitt),
            Pair("Chris Evans", R.drawable.brad_pitt),
            Pair("Henry Cavill", R.drawable.brad_pitt),
            Pair("Jason Momoa", R.drawable.brad_pitt),
            Pair("Michael B. Jordan", R.drawable.brad_pitt),
            Pair("Keanu Reeves", R.drawable.brad_pitt),
            Pair("John Cena", R.drawable.brad_pitt),
            Pair("Idris Elba", R.drawable.brad_pitt),
        )
    )
}

fun animalMockData(animalList: ArrayList<Pair<String, Int>>) {
    animalList.addAll(
        listOf(
            Pair("Dog", R.drawable.brad_pitt),
            Pair("Cat", R.drawable.brad_pitt),
            Pair("Horse", R.drawable.brad_pitt),
            Pair("Fish", R.drawable.brad_pitt),
            Pair("Bird", R.drawable.brad_pitt),
            Pair("Monkey", R.drawable.brad_pitt),
            Pair("Sheep", R.drawable.brad_pitt),
            Pair("Cow", R.drawable.brad_pitt),
            Pair("Pig", R.drawable.brad_pitt),
            Pair("Chicken", R.drawable.brad_pitt),
            Pair("Elephant", R.drawable.brad_pitt),
            Pair("Lion", R.drawable.brad_pitt),
            Pair("Tiger", R.drawable.brad_pitt),
            Pair("Zebra", R.drawable.brad_pitt),
            Pair("Giraffe", R.drawable.brad_pitt),
            Pair("Crocodile", R.drawable.brad_pitt),
            Pair("Whale", R.drawable.brad_pitt),
            Pair("Shark", R.drawable.brad_pitt),
        )
    )
}

fun footballerMockData(footballerList: ArrayList<Pair<String, Int>>) {
    footballerList.addAll(
        listOf(
            Pair("Lionel Messi", R.drawable.brad_pitt),
            Pair("Cristiano Ronaldo", R.drawable.brad_pitt),
            Pair("Neymar Jr.", R.drawable.brad_pitt),
            Pair("Kylian Mbappé", R.drawable.brad_pitt),
            Pair("Robert Lewandowski", R.drawable.brad_pitt),
            Pair("Mohamed Salah", R.drawable.brad_pitt),
            Pair("Kevin De Bruyne", R.drawable.brad_pitt),
            Pair("Karim Benzema", R.drawable.brad_pitt),
            Pair("Erling Haaland", R.drawable.brad_pitt),
            Pair("Virgil van Dijk", R.drawable.brad_pitt),
            Pair("Thiago Alcantara", R.drawable.brad_pitt),
            Pair("Jorginho", R.drawable.brad_pitt),
            Pair("Bernardo Silva", R.drawable.brad_pitt),
            Pair("Bruno Fernandes", R.drawable.brad_pitt),
            Pair("Paul Pogba", R.drawable.brad_pitt),
            Pair("Harry Kane", R.drawable.brad_pitt),
            Pair("Romelu Lukaku", R.drawable.brad_pitt)
        )
    )
}

fun historicalFiguresMockData(historicalFiguresList: ArrayList<Pair<String, Int>>) {
    historicalFiguresList.addAll(
        listOf(
            Pair("Albert Einstein", R.drawable.brad_pitt),
            Pair("Marie Curie", R.drawable.brad_pitt),
            Pair("Leonardo da Vinci", R.drawable.brad_pitt),
            Pair("Napoleon Bonaparte", R.drawable.brad_pitt),
            Pair("Mahatma Gandhi", R.drawable.brad_pitt),
            Pair("Martin Luther King Jr.", R.drawable.brad_pitt),
            Pair("Abraham Lincoln", R.drawable.brad_pitt),
            Pair("Winston Churchill", R.drawable.brad_pitt),
            Pair("Nelson Mandela", R.drawable.brad_pitt),
            Pair("Frida Kahlo", R.drawable.brad_pitt),
            Pair("Galileo Galilei", R.drawable.brad_pitt),
            Pair("Isaac Newton", R.drawable.brad_pitt),
            Pair("Charles Darwin", R.drawable.brad_pitt),
            Pair("Alexander the Great", R.drawable.brad_pitt),
            Pair("Julius Caesar", R.drawable.brad_pitt),
            Pair("Cleopatra", R.drawable.brad_pitt),
            Pair("Joan of Arc", R.drawable.brad_pitt),
            Pair("Genghis Khan", R.drawable.brad_pitt),
        )
    )
}

fun marvelMockData(marvelList: ArrayList<Pair<String, Int>>) {
    marvelList.addAll(
        listOf(
            Pair("Iron Man", R.drawable.brad_pitt),
            Pair("Captain America", R.drawable.brad_pitt),
            Pair("Thor", R.drawable.brad_pitt),
            Pair("Hulk", R.drawable.brad_pitt),
            Pair("Black Widow", R.drawable.brad_pitt),
            Pair("Hawkeye", R.drawable.brad_pitt),
            Pair("Spider-Man", R.drawable.brad_pitt),
            Pair("Doctor Strange", R.drawable.brad_pitt),
            Pair("Captain Marvel", R.drawable.brad_pitt),
            Pair("Black Panther", R.drawable.brad_pitt),
            Pair("Scarlet Witch", R.drawable.brad_pitt),
            Pair("Vision", R.drawable.brad_pitt),
            Pair("Gamora", R.drawable.brad_pitt),
            Pair("Drax the Destroyer", R.drawable.brad_pitt),
            Pair("Star-Lord", R.drawable.brad_pitt),
            Pair("Rocket Raccoon", R.drawable.brad_pitt),
            Pair("Groot", R.drawable.brad_pitt),
        )
    )
}

fun movieCharactersMockData(movieCharactersList: ArrayList<Pair<String, Int>>) {
    movieCharactersList.addAll(
        listOf(
            Pair("Indiana Jones", R.drawable.brad_pitt),
            Pair("Han Solo", R.drawable.brad_pitt),
            Pair("Luke Skywalker", R.drawable.brad_pitt),
            Pair("Darth Vader", R.drawable.brad_pitt),
            Pair("Batman", R.drawable.brad_pitt),
            Pair("Superman", R.drawable.brad_pitt),
            Pair("Wonder Woman", R.drawable.brad_pitt),
            Pair("Joker", R.drawable.brad_pitt),
            Pair("Neo", R.drawable.brad_pitt),
            Pair("Trinity", R.drawable.brad_pitt),
            Pair("Agent Smith", R.drawable.brad_pitt),
            Pair("Willy Wonka", R.drawable.brad_pitt),
            Pair("Charlie Bucket", R.drawable.brad_pitt),
            Pair("The Grinch", R.drawable.brad_pitt),
            Pair("E.T. the Extra-Terrestrial", R.drawable.brad_pitt),
            Pair("The Lion King", R.drawable.brad_pitt),
            Pair("Mulan", R.drawable.brad_pitt),
        )
    )
}

fun politiciansMockData(politiciansList: ArrayList<Pair<String, Int>>) {
    politiciansList.addAll(
        listOf(
            Pair("Joe Biden", R.drawable.brad_pitt),
            Pair("Kamala Harris", R.drawable.brad_pitt),
            Pair("Nancy Pelosi", R.drawable.brad_pitt),
            Pair("Mitch McConnell", R.drawable.brad_pitt),
            Pair("Bernie Sanders", R.drawable.brad_pitt),
            Pair("Elizabeth Warren", R.drawable.brad_pitt),
            Pair("Alexandria Ocasio-Cortez", R.drawable.brad_pitt),
            Pair("Kevin McCarthy", R.drawable.brad_pitt),
            Pair("Angela Merkel", R.drawable.brad_pitt),
            Pair("Vladimir Putin", R.drawable.brad_pitt),
            Pair("Xi Jinping", R.drawable.brad_pitt),
            Pair("Narendra Modi", R.drawable.brad_pitt),
            Pair("Justin Trudeau", R.drawable.brad_pitt),
            Pair("Shinzo Abe", R.drawable.brad_pitt),
            Pair("Boris Johnson", R.drawable.brad_pitt),
            Pair("Emmanuel Macron", R.drawable.brad_pitt),
            Pair("Jacinda Ardern", R.drawable.brad_pitt),
            Pair("Greta Thunberg", R.drawable.brad_pitt),
            Pair("Malala Yousafzai", R.drawable.brad_pitt),
        )
    )
}

fun singersMockData(singersList: ArrayList<Pair<String, Int>>) {
    singersList.addAll(
        listOf(
            Pair("Beyoncé", R.drawable.brad_pitt),
            Pair("Adele", R.drawable.brad_pitt),
            Pair("Taylor Swift", R.drawable.brad_pitt),
            Pair("Justin Bieber", R.drawable.brad_pitt),
            Pair("Ariana Grande", R.drawable.brad_pitt),
            Pair("The Weeknd", R.drawable.brad_pitt),
            Pair("Ed Sheeran", R.drawable.brad_pitt),
            Pair("BTS", R.drawable.brad_pitt),
            Pair("BLACKPINK", R.drawable.brad_pitt),
            Pair("Olivia Rodrigo", R.drawable.brad_pitt),
            Pair("Harry Styles", R.drawable.brad_pitt),
            Pair("Billie Eilish", R.drawable.brad_pitt),
            Pair("The Chainsmokers", R.drawable.brad_pitt),
            Pair("Dua Lipa", R.drawable.brad_pitt),
            Pair("Maroon 5", R.drawable.brad_pitt),
            Pair("Imagine Dragons", R.drawable.brad_pitt),
            Pair("Michael Jackson", R.drawable.brad_pitt),
            Pair("Elvis Presley", R.drawable.brad_pitt),
            Pair("Madonna", R.drawable.brad_pitt),
        )
    )
}