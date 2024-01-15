package com.basaran.whoami.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import com.basaran.whoami.R
import com.basaran.whoami.util.AnimatedCountdownTimer
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun GameScreen(savedStateHandle: SavedStateHandle) {

    val selectedCategory = savedStateHandle.get<ArrayList<String>>("myArrayList") ?: emptyList()
    var timeLeft by remember { mutableIntStateOf(3) }
    val coroutineScope = rememberCoroutineScope()
    val animatedCountdownTimer = remember { AnimatedCountdownTimer(coroutineScope) }
    val actorList = mutableListOf<Pair<String, String>>()
    val animalsList = mutableListOf<Pair<String, String>>()
    val footballerList = mutableListOf<Pair<String, String>>()
    val historicalFiguresList = mutableListOf<Pair<String, String>>()
    val marvelList = mutableListOf<Pair<String, String>>()
    val movieCharactersList = mutableListOf<Pair<String, String>>()
    val politiciansList = mutableListOf<Pair<String, String>>()
    val singersList = mutableListOf<Pair<String, String>>()
    val selectedResultCategory = mutableListOf<Pair<String, String>>()
    var random = Pair("", "")

    for (category in selectedCategory) {
        when (category) {
            "Actors" -> {
                actorMockData(actorList as ArrayList<Pair<String, String>>)
                selectedResultCategory.addAll(actorList)
            }

            "Animals" -> {
                animalMockData(animalsList as ArrayList<Pair<String, String>>)
                selectedResultCategory.addAll(animalsList)
            }

            "Footballer" -> {
                footballerMockData(footballerList as ArrayList<Pair<String, String>>)
                selectedResultCategory.addAll(footballerList)
            }

            "Historical Figures" -> {
                historicalFiguresMockData(historicalFiguresList as ArrayList<Pair<String, String>>)
                selectedResultCategory.addAll(historicalFiguresList)
            }

            "Marvel" -> {
                marvelMockData(marvelList as ArrayList<Pair<String, String>>)
                selectedResultCategory.addAll(marvelList)
            }

            "Movie Characters" -> {
                movieCharactersMockData(movieCharactersList as ArrayList<Pair<String, String>>)
                selectedResultCategory.addAll(movieCharactersList)
            }

            "Politicians" -> {
                politiciansMockData(politiciansList as ArrayList<Pair<String, String>>)
                selectedResultCategory.addAll(politiciansList)
            }

            "Singers" -> {
                singersMockData(singersList as ArrayList<Pair<String, String>>)
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
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (selectedResultCategory.isNotEmpty()) {
                        random = selectedResultCategory.random()
                    }
                    GlideImage(
                        model = random.second,
                        contentDescription = "loadImage",
                        modifier = Modifier
                            .padding(bottom = 32.dp)
                            .width(250.dp)
                            .height(250.dp)
                    ) {
                        it.error(R.drawable.brad_pitt)
                            .load(random.second)
                    }
                    Text(
                        text = random.first,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.textColor),
                    )
                    Column(
                        modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.End
                    ) {
                        Button(
                            modifier = Modifier
                                .padding(top = 24.dp, bottom = 12.dp, end = 12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.buttonColor)
                            ),
                            border = BorderStroke(2.dp, Color.Black),
                            onClick = {
                                random = selectedResultCategory.random()
                                animatedCountdownTimer.start(3, 0) {
                                    timeLeft = it
                                }
                            }) {
                            Text(text = "Next", fontSize = 16.sp)
                        }
                    }
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

fun actorMockData(actorList: ArrayList<Pair<String, String>>) {
    actorList.addAll(
        listOf(
            Pair(
                "Brad Pitt",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Brad_Pitt_2019_by_Glenn_Francis.jpg/220px-Brad_Pitt_2019_by_Glenn_Francis.jpg"
            ),
            Pair(
                "Tom Cruise",
                "https://variety.com/wp-content/uploads/2023/07/GettyImages-1511418315.jpg?w=1024"
            ),
            Pair(
                "Robert Downey Jr.",
                "https://m.media-amazon.com/images/M/MV5BNzg1MTUyNDYxOF5BMl5BanBnXkFtZTgwNTQ4MTE2MjE@._V1_.jpg"
            ),
            Pair(
                "Leonardo DiCaprio",
                "https://cdn.britannica.com/65/227665-050-D74A477E/American-actor-Leonardo-DiCaprio-2016.jpg"
            ),
            Pair(
                "Will Smith",
                "https://m.media-amazon.com/images/M/MV5BNTczMzk1MjU1MV5BMl5BanBnXkFtZTcwNDk2MzAyMg@@._V1_FMjpg_UX1000_.jpg"
            ),
            Pair(
                "Dwayne Johnson",
                "https://m.media-amazon.com/images/M/MV5BOWU1ODBiNGUtMzVjNi00MzdhLTk0OTktOWRiOTIxMWNhOGI2XkEyXkFqcGdeQXVyMTU2OTM5NDQw._V1_.jpg"
            ),
            Pair(
                "Mark Wahlberg",
                "https://m.media-amazon.com/images/M/MV5BMTU0MTQ4OTMyMV5BMl5BanBnXkFtZTcwMTQxOTY1NA@@._V1_FMjpg_UX1000_.jpg"
            ),
            Pair(
                "Chris Hemsworth",
                "https://upload.wikimedia.org/wikipedia/commons/e/e8/Chris_Hemsworth_by_Gage_Skidmore_2_%28cropped%29.jpg"
            ),
            Pair(
                "Ryan Reynolds",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/Deadpool_2_Japan_Premiere_Red_Carpet_Ryan_Reynolds_%28cropped%29.jpg/1200px-Deadpool_2_Japan_Premiere_Red_Carpet_Ryan_Reynolds_%28cropped%29.jpg"
            ),
            Pair(
                "Ben Affleck",
                "https://cdn.britannica.com/33/242333-050-95A19CE8/Actor-Ben-Affleck-premiere-AIR-March-2023.jpg"
            ),
            Pair(
                "Chris Pratt",
                "https://cdn.britannica.com/48/216648-050-4A42C937/American-actor-Chris-Pratt-2020.jpg"
            ),
            Pair(
                "Chris Evans",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8d/ChrisEvans2023.jpg/640px-ChrisEvans2023.jpg"
            ),
            Pair(
                "Henry Cavill",
                "https://tr.web.img3.acsta.net/c_310_420/pictures/210/127/21012705_2013061512270994.jpg"
            ),
            Pair(
                "Jason Momoa",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/2/22/Jason_Momoa_%2843055621224%29_%28cropped%29.jpg/800px-Jason_Momoa_%2843055621224%29_%28cropped%29.jpg"
            ),
            Pair(
                "Michael B. Jordan",
                "https://m.media-amazon.com/images/M/MV5BMjExOTY3NzExM15BMl5BanBnXkFtZTgwOTg1OTAzMTE@._V1_.jpg"
            ),
            Pair(
                "Keanu Reeves",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/3/33/Reuni%C3%A3o_com_o_ator_norte-americano_Keanu_Reeves_%2846806576944%29_%28cropped%29.jpg/1200px-Reuni%C3%A3o_com_o_ator_norte-americano_Keanu_Reeves_%2846806576944%29_%28cropped%29.jpg"
            ),
            Pair("John Cena", "https://tr.web.img2.acsta.net/pictures/17/06/14/13/48/489688.jpg"),
            Pair(
                "Idris Elba",
                "https://cdn.britannica.com/41/188641-050-AB88F70B/Idris-Elba-British.jpg"
            ),
        )
    )
}

fun animalMockData(animalList: ArrayList<Pair<String, String>>) {
    animalList.addAll(
        listOf(
            Pair(
                "Dog",
                "https://cdn-prod.medicalnewstoday.com/content/images/articles/322/322868/golden-retriever-puppy.jpg"
            ),
            Pair(
                "Cat",
                "https://i.natgeofe.com/n/4cebbf38-5df4-4ed0-864a-4ebeb64d33a4/NationalGeographic_1468962_3x2.jpg?w=1638&h=1092"
            ),
            Pair(
                "Horse",
                "https://iadsb.tmgrup.com.tr/7ddb86/0/0/0/0/1926/1086?u=https://idsb.tmgrup.com.tr/2018/05/22/horses-the-wings-of-mankind-1527015927739.jpg"
            ),
            Pair(
                "Fish",
                "https://images.ctfassets.net/ww1ie0z745y7/2ZLgATkZvsbHjsnrPRzBYu/e592901dccc526622e39898e9271a7ef/Goldfish.jpeg?q=75"
            ),
            Pair("Bird", "https://cdn.download.ams.birds.cornell.edu/api/v1/asset/202984001/1200"),
            Pair(
                "Monkey",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/Bonnet_macaque_%28Macaca_radiata%29_Photograph_By_Shantanu_Kuveskar.jpg/220px-Bonnet_macaque_%28Macaca_radiata%29_Photograph_By_Shantanu_Kuveskar.jpg"
            ),
            Pair(
                "Sheep",
                "https://www.kalmbachfeeds.com/wp-content/uploads/2022/08/sheep-grazing-on-pasture-up-close.jpg"
            ),
            Pair("Cow", "https://cdn.britannica.com/55/174255-050-526314B6/brown-Guernsey-cow.jpg"),
            Pair(
                "Pig",
                "https://upload.wikimedia.org/wikipedia/commons/b/bf/Pig_farm_Vampula_9.jpg"
            ),
            Pair(
                "Chicken",
                "https://ninovatavukculuk.com/wp-content/uploads/2022/10/lohman-tavuk-1.jpg"
            ),
            Pair(
                "Elephant",
                "https://i.natgeofe.com/k/e7ba8001-23ac-457f-aedb-abd5f2fdda62/moms5_16x9.png"
            ),
            Pair(
                "Lion",
                "https://www.usatoday.com/gcdn/-mm-/1637044d22191b9453b9c17456ea74428fd3761d/c=299-0-1067-768/local/-/media/2022/03/16/USATODAY/usatsports/imageForEntry5-ODq.jpg"
            ),
            Pair(
                "Tiger",
                "https://files.worldwildlife.org/wwfcmsprod/images/Tiger_resting_Bandhavgarh_National_Park_India/hero_small/6aofsvaglm_Medium_WW226365.jpg"
            ),
            Pair(
                "Zebra",
                "https://upload.wikimedia.org/wikipedia/commons/d/dd/Burchell%27s_Zebra_%28Etosha%29.jpg"
            ),
            Pair(
                "Giraffe",
                "https://www.worldatlas.com/upload/cf/93/4b/shutterstock-1385689649.jpg"
            ),
            Pair(
                "Crocodile",
                "https://c02.purpledshub.com/uploads/sites/62/2014/11/GettyImages-123529247-2a29d6c.jpg?w=1029&webp=1"
            ),
            Pair(
                "Whale",
                "https://www.worldatlas.com/r/w1200-q80/upload/ff/33/80/adobestock-573332061.jpeg"
            ),
            Pair(
                "Shark",
                "https://d1jyxxz9imt9yb.cloudfront.net/animal/218/meta_image/regular/20---IMG_0865_reduced.jpg"
            ),
        )
    )
}

fun footballerMockData(footballerList: ArrayList<Pair<String, String>>) {
    footballerList.addAll(
        listOf(
            Pair(
                "Lionel Messi",
                "https://cdn.britannica.com/35/238335-050-2CB2EB8A/Lionel-Messi-Argentina-Netherlands-World-Cup-Qatar-2022.jpg"
            ),
            Pair(
                "Cristiano Ronaldo",
                "https://upload.wikimedia.org/wikipedia/commons/d/d7/Cristiano_Ronaldo_playing_for_Al_Nassr_FC_against_Persepolis%2C_September_2023_%28cropped%29.jpg"
            ),
            Pair("Neymar Jr.", "https://b.fssta.com/uploads/application/soccer/headshots/713.png"),
            Pair(
                "Kylian Mbappé",
                "https://imageio.forbes.com/specials-images/imageserve/626c7cf3616c1112ae834a2b/0x0.jpg?format=jpg&crop=1603,1603,x1533,y577,safe&height=416&width=416&fit=bounds"
            ),
            Pair(
                "Robert Lewandowski",
                "https://fcb-abj-pre.s3.amazonaws.com/img/jugadors/LEWANDOWSKI-min.jpg"
            ),
            Pair(
                "Mohamed Salah",
                "https://assets.goal.com/v3/assets/bltcc7a7ffd2fbf71f5/blt8fdd1ef9212958b9/6404de8cb135f128bd0e062a/GettyImages-1471469891.jpg?auto=webp&format=pjpg&width=3840&quality=60"
            ),
            Pair(
                "Kevin De Bruyne",
                "https://www.mancity.com/meta/media/z00hnhu0/kevin-de-bruyne.png"
            ),
            Pair(
                "Karim Benzema",
                "https://upload.wikimedia.org/wikipedia/commons/b/b6/Karim_Benzema_2021.jpg"
            ),
            Pair(
                "Erling Haaland",
                "https://upload.wikimedia.org/wikipedia/commons/6/6e/Erling_Haaland_2023_%28cropped-v2%29.jpg"
            ),
            Pair(
                "Virgil van Dijk",
                "https://e0.365dm.com/18/01/2048x1152/skysports-virgil-van-dijk-liverpool-everton_4199322.jpg?20180105214936"
            ),
            Pair(
                "Thiago Alcantara",
                "https://tmssl.akamaized.net/images/foto/galerie/thiago-alcantara-fc-liverpool-2022-2023-1688395277-110495.jpeg?lm=1688395302"
            ),
            Pair(
                "Jorginho",
                "https://cloudfront-us-east-2.images.arcpublishing.com/reuters/K5TMWLOIBNPQVDKEGGNRHHE47I.jpg"
            ),
            Pair(
                "Bernardo Silva",
                "https://static.independent.co.uk/2023/04/19/23/11-ed332fab269443b6a09c1322955c42c5.jpg"
            ),
            Pair("Bruno Fernandes", "https://pbs.twimg.com/media/FirdJHJXEAICtss.jpg"),
            Pair(
                "Paul Pogba",
                "https://upload.wikimedia.org/wikipedia/commons/c/cc/FRA-ARG_%2811%29_-_Paul_Pogba_%28cropped_2%29.jpg"
            ),
            Pair(
                "Harry Kane",
                "https://cdnuploads.aa.com.tr/uploads/Contents/2023/11/29/thumbs_b_c_c046d80bd871a61d2869776205806063.jpg?v=124857"
            ),
            Pair(
                "Romelu Lukaku",
                "https://upload.wikimedia.org/wikipedia/commons/d/dc/Romelu_Lukaku_2021.jpg"
            )
        )
    )
}

fun historicalFiguresMockData(historicalFiguresList: ArrayList<Pair<String, String>>) {
    historicalFiguresList.addAll(
        listOf(
            Pair(
                "Albert Einstein",
                "https://hips.hearstapps.com/hmg-prod/images/albert-einstein-sticks-out-his-tongue-when-asked-by-news-photo-1681316749.jpg"
            ),
            Pair(
                "Marie Curie",
                "https://cdn.britannica.com/10/74610-050-19CB330C/Marie-Curie-Paris-laboratory.jpg"
            ),
            Pair(
                "Leonardo da Vinci",
                "https://hips.hearstapps.com/hmg-prod/images/portrait-of-leonardo-da-vinci-1452-1519-getty.jpg"
            ),
            Pair(
                "Napoleon Bonaparte",
                "https://cdn.britannica.com/93/115193-050-6FFE7E0F/First-Consul-Bonaparte-canvas-Antoine-Jean-Gros-Legion-1802.jpg"
            ),
            Pair(
                "Mahatma Gandhi",
                "https://cdn.britannica.com/91/82291-050-EB7A276A/Mohandas-K-Gandhi-leader-Mahatma-Indian.jpg"
            ),
            Pair(
                "Martin Luther King Jr.",
                "https://www.scholastic.com/content/parents/en/family-life/parent-child/teaching-kids-about-martin-luther-king-jr/_jcr_content.img.full.high.png"
            ),
            Pair(
                "Abraham Lincoln",
                "https://i0.wp.com/historycolored.com/wp-content/uploads/2021/12/Abe-LincolnColor.jpg?fit=1008%2C1169&ssl=1"
            ),
            Pair(
                "Winston Churchill",
                "https://m.media-amazon.com/images/I/71O8d6qpp6L._AC_UF1000,1000_QL80_.jpg"
            ),
            Pair(
                "Nelson Mandela",
                "https://hips.hearstapps.com/hmg-prod/images/_photo-by-per-anders-petterssongetty-images.jpg"
            ),
            Pair(
                "Frida Kahlo",
                "https://wp.oggusto.com/wp-content/uploads/2022/08/batch_frida-kahlo-hayati-eserleri-ve-bilinmeyenleri-frida-kahlo-hayati-eserleri-ve-bilinmeyenleri-177.jpg"
            ),
            Pair(
                "Galileo Galilei",
                "https://evrimagaci.org/public/content_media/a722a5df03a966a95afadfcb2a6234ef.jpg"
            ),
            Pair(
                "Isaac Newton",
                "https://www.neh.gov/sites/default/files/styles/1000x1000_square/public/2018-07/2011_01-02_Newton_01.jpg?h=c7a80cea&itok=oS4tbkAz"
            ),
            Pair(
                "Charles Darwin",
                "https://www.rmg.co.uk/sites/default/files/styles/full_width_1440/public/Charles_Robert_Darwin_by_John_Collier%20banner.jpg?itok=wJBFHlUl"
            ),
            Pair(
                "Alexander the Great",
                "https://www.shenyunperformingarts.org/data/image/original/2021/02/20/fbc73ee8f22e7cbf9e2b7883ce7516bb.png"
            ),
            Pair(
                "Julius Caesar",
                "https://sites.dartmouth.edu/jacko/files/2019/11/julius-caesar.jpg"
            ),
            Pair(
                "Cleopatra",
                "https://images1.wionews.com/images/wion/900x1600/2023/11/17/1700211289486_Webstory32.jpg"
            ),
            Pair("Joan of Arc", "https://www.worldhistory.org/img/r/p/1500x1500/14751.jpg"),
            Pair(
                "Genghis Khan",
                "https://i.natgeofe.com/n/32820ebd-4660-4b3a-b360-8ae16c328489/kublai-khan-portrait.jpg?w=1534&h=2022"
            ),
            Pair(
                "Mustafa Kemal Atatürk",
                "https://ataturkansiklopedisi.gov.tr/bilgi/Dosya:Mustafa_Kemal_ATAT%C3%9CRK.jpg"
            ),
        )
    )
}

fun marvelMockData(marvelList: ArrayList<Pair<String, String>>) {
    marvelList.addAll(
        listOf(
            Pair(
                "Iron Man",
                "https://cdn.britannica.com/49/182849-050-4C7FE34F/scene-Iron-Man.jpg"
            ),
            Pair(
                "Captain America",
                "https://assetsio.reedpopcdn.com/Steve-Rogers-Cap.jpg?width=1200&height=1200&fit=crop&quality=100&format=png&enable=upscale&auto=webp"
            ),
            Pair(
                "Thor",
                "https://static.wikia.nocookie.net/marvelcinematicuniverse/images/2/22/Thor_in_LoveAndThunder_Poster.jpg/revision/latest?cb=20231021012616"
            ),
            Pair("Hulk", "https://sm.ign.com/t/ign_tr/screenshot/default/hulk_ec2z.1280.jpg"),
            Pair(
                "Black Widow",
                "https://lumiere-a.akamaihd.net/v1/images/image_b97b56f3.jpeg?region=0%2C0%2C540%2C810"
            ),
            Pair(
                "Hawkeye",
                "https://images.immediate.co.uk/production/volatile/sites/3/2019/07/avengers-8bb66cd.jpg?quality=90&resize=727,485"
            ),
            Pair(
                "Spider-Man",
                "https://m.media-amazon.com/images/M/MV5BZDEyN2NhMjgtMjdhNi00MmNlLWE5YTgtZGE4MzNjMTRlMGEwXkEyXkFqcGdeQXVyNDUyOTg3Njg@._V1_FMjpg_UX1000_.jpg"
            ),
            Pair(
                "Doctor Strange",
                "https://static.wikia.nocookie.net/disney/images/d/dc/Doctor_Strange_-_Profile.png/revision/latest?cb=20220804200852"
            ),
            Pair(
                "Captain Marvel",
                "https://static.wikia.nocookie.net/marvelcinematicuniverse/images/3/3d/Captain_Marvel_Profile.png/revision/latest?cb=20231110014222"
            ),
            Pair(
                "Black Panther",
                "https://media.newyorker.com/photos/5a875e3f33aebd0cab9bab12/1:1/w_1079,h_1079,c_limit/Brody-Passionate-Politics-Black-Panther.jpg"
            ),
            Pair(
                "Scarlet Witch",
                "https://assets.teenvogue.com/photos/5ba25a5be8d90e3d1c5414a7/master/pass/tout.jpg"
            ),
            Pair(
                "Vision",
                "https://static.wikia.nocookie.net/marvelcinematicuniverse/images/2/2f/Vision_AIW_Profile.jpg/revision/latest?cb=20231024023951"
            ),
            Pair("Gamora", "https://upload.wikimedia.org/wikipedia/en/0/08/Gamora-cover.jpg"),
            Pair(
                "Drax the Destroyer",
                "https://www.indiewire.com/wp-content/uploads/2021/05/drax.png"
            ),
            Pair(
                "Star-Lord",
                "https://static.wikia.nocookie.net/marvelcinematicuniverse/images/4/4e/Star-Lord_Vol._3.jpg/revision/latest?cb=20231113214053"
            ),
            Pair(
                "Rocket Raccoon",
                "https://oyster.ignimgs.com/mediawiki/apis.ign.com/marvel-studios-cinematic-universe/1/18/Rocket-raccoon-has-his-very-own-guardians-of-the-galaxy-poster-preview.jpg"
            ),
            Pair(
                "Groot",
                "https://theguywiththehook.files.wordpress.com/2020/04/img_3305.jpg?w=1001"
            ),
        )
    )
}

fun movieCharactersMockData(movieCharactersList: ArrayList<Pair<String, String>>) {
    movieCharactersList.addAll(
        listOf(
            Pair(
                "Indiana Jones",
                "https://media-cldnry.s-nbcnews.com/image/upload/t_fit-1500w,f_auto,q_auto:best/newscms/2016_11/1459036/160315-indiana-jones-harrison-ford-205p.jpg"
            ),
            Pair(
                "Han Solo",
                "https://static.wikia.nocookie.net/starwars/images/0/01/Hansoloprofile.jpg/revision/latest?cb=20100129155042"
            ),
            Pair(
                "Luke Skywalker",
                "https://static.wikia.nocookie.net/starwars/images/d/d9/Luke-rotjpromo.jpg/revision/latest?cb=20091030151422"
            ),
            Pair(
                "Darth Vader",
                "https://static.wikia.nocookie.net/characterprofile/images/f/f6/Darth_vader_9_render_by_aracnify-d92wamu.png/revision/latest?cb=20160203192012"
            ),
            Pair(
                "Batman",
                "https://static.wikia.nocookie.net/p__/images/f/fd/Batman_%28Prime_Earth%29.jpg/revision/latest?cb=20230718090804&path-prefix=protagonist"
            ),
            Pair(
                "Joker",
                "https://static.wikia.nocookie.net/batman/images/c/c0/Facepaint.png/revision/latest/scale-to-width-down/1200?cb=20200320213434"
            ),
            Pair(
                "Neo",
                "https://www.indyturk.com/sites/default/files/styles/1368x911/public/article/main_image/2021/04/28/647956-1969329913.jpg?itok=vzHY-i33"
            ),
            Pair(
                "Trinity",
                "https://static.wikia.nocookie.net/matrix/images/6/67/Trinityfull.jpg/revision/latest?cb=20060803214449"
            ),
            Pair(
                "Agent Smith",
                "https://upload.wikimedia.org/wikipedia/en/1/1f/Agent_Smith_%28The_Matrix_series_character%29.jpg"
            ),
            Pair(
                "Willy Wonka",
                "https://cdn.vox-cdn.com/thumbor/PVJITbAakI93rQKEOzU7NeIRKUw=/0x0:2100x1234/1200x0/filters:focal(0x0:2100x1234):no_upscale()/cdn.vox-cdn.com/uploads/chorus_asset/file/25179208/MCDCHAN_EC087.jpg"
            ),
            Pair(
                "Charlie Bucket",
                "https://a1cf74336522e87f135f-2f21ace9a6cf0052456644b80fa06d4f.ssl.cf2.rackcdn.com/images/characters/large/800/Charlie-Bucket.Charlie-and-the-Chocolate-Factory.webp"
            ),
            Pair(
                "The Grinch",
                "https://m.media-amazon.com/images/M/MV5BNWNiNTczNzEtMjQyZC00MjFmLTkzMDMtODk4ZGMyZmE0N2E4XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_.jpg"
            ),
            Pair(
                "E.T. the Extra-Terrestrial",
                "https://m.media-amazon.com/images/S/pv-target-images/8da09fea47590d604cc871a98b28960b4f77c3f08a004c8669c4c6c9f96793f5.jpg"
            ),
            Pair(
                "The Lion King",
                "https://m.media-amazon.com/images/M/MV5BMjIwMjE1Nzc4NV5BMl5BanBnXkFtZTgwNDg4OTA1NzM@._V1_.jpg"
            ),
            Pair(
                "Mulan",
                "https://lumiere-a.akamaihd.net/v1/images/p_mulan_20529_83d3893a.jpeg?region=0%2C0%2C540%2C810"
            ),
            Pair(
                "James Bond",
                "https://tr.web.img3.acsta.net/r_654_368/img/a0/7c/a07c941573c10ef922502d23eab39f33.jpg"
            ),
            Pair(
                "Rocky Balboa",
                "https://i.pinimg.com/550x/14/40/b1/1440b14d37cf7c24dbb1cec4fa1747b7.jpg"
            ),
        )
    )
}

fun politiciansMockData(politiciansList: ArrayList<Pair<String, String>>) {
    politiciansList.addAll(
        listOf(
            Pair(
                "Joe Biden",
                "https://www.whitehouse.gov/wp-content/uploads/2021/04/P20210303AS-1901-cropped.jpg?w=1536"
            ),
            Pair(
                "Kamala Harris",
                "https://cdn.britannica.com/09/193109-050-51B44FEE/Kamala-Harris.jpg"
            ),
            Pair(
                "Nancy Pelosi",
                "https://i.abcnewsfe.com/a/df873416-c701-4104-94b4-3fe9704af7ef/nancy-pelosi-ap-moe-057-230908_1694191166338_hpMain.jpg"
            ),
            Pair(
                "Angela Merkel",
                "https://cdn.britannica.com/17/172817-050-B5A3AE9B/Angela-Merkel-2012.jpg"
            ),
            Pair("Olaf Scholz", "https://www.spdfraktion.de/system/files/images/scholz-olaf_0.jpg"),
            Pair(
                "Emmanuel Macron",
                "https://cdnuploads.aa.com.tr/uploads/Contents/2023/01/28/thumbs_b_c_c28529e5ef54d1c3f198ab7c7e137ac2.jpg?v=154957"
            ),
            Pair(
                "Vladimir Putin",
                "https://s.france24.com/media/display/cd078b54-9a66-11ee-bcbb-005056bfb2b6/w:1280/p:4x3/2023-12-14T092503Z_1698665403_RC2XW4A8XIFV_RTRMADP_3_RUSSIA-PUTIN.JPG"
            ),
            Pair(
                "Xi Jinping",
                "https://assets.bwbx.io/images/users/iqjWHBFdfxIU/iuIvvu_0cci0/v0/-1x-1.jpg"
            ),
            Pair(
                "Narendra Modi",
                "https://www.livemint.com/lm-img/img/2023/08/14/1600x900/PTI08-06-2023-000031A-0_1692022172230_1692022193566.jpg"
            ),
            Pair(
                "Justin Trudeau",
                "https://assets.weforum.org/sf_account/image/UgD8pKdNbiwi8GQQZxl49hBZxQs1xp1V1incce1bSBc.jpg"
            ),
            Pair(
                "Shinzo Abe",
                "https://www.ft.com/__origami/service/image/v2/images/raw/https%3A%2F%2Fd1e00ek4ebabms.cloudfront.net%2Fproduction%2Fc551c7cd-add7-491a-b308-c2e9565cc17f.jpg?source=next-article&fit=scale-down&quality=highest&width=700&dpr=1"
            ),
            Pair(
                "Boris Johnson",
                "https://media.cnn.com/api/v1/images/stellar/prod/220412085815-boris-johnson-0407.jpg?c=16x9&q=h_833,w_1480,c_fill"
            ),
            Pair(
                "Jacinda Ardern",
                "https://cdn.britannica.com/19/197119-050-79765BF4/Jacinda-Ardern-2017.jpg"
            ),
            Pair(
                "Binyamin Netanyahu",
                "https://image.cnbcfm.com/api/v1/image/107348788-17029032682023-12-17t100745z_1211599458_rc2yy4a7uy9l_rtrmadp_0_israel-politics.jpeg?v=1702903319&w=929&h=523&vtcrop=y"
            ),
            Pair(
                "Giorgia Meloni",
                "https://media-cldnry.s-nbcnews.com/image/upload/t_fit-760w,f_auto,q_auto:best/rockcms/2022-10/221022-giorgia-meloni-ha-b14566.jpg"
            ),
        )
    )
}

fun singersMockData(singersList: ArrayList<Pair<String, String>>) {
    singersList.addAll(
        listOf(
            Pair(
                "Beyoncé",
                "https://assets.teenvogue.com/photos/65870cb0062dbbca4e282c10/1:1/w_4000,h_4000,c_limit/1580039832"
            ),
            Pair(
                "Adele",
                "https://upload.wikimedia.org/wikipedia/commons/5/52/Adele_for_Vogue_in_2021.png"
            ),
            Pair(
                "Taylor Swift",
                "https://m.media-amazon.com/images/M/MV5BZGM0YjhkZmEtNGYxYy00OTk0LThlNDgtNGQzM2YwNjU0NDQzXkEyXkFqcGdeQXVyMTU3ODQxNDYz._V1_.jpg"
            ),
            Pair(
                "Justin Bieber",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Justin_Bieber_in_2015.jpg/800px-Justin_Bieber_in_2015.jpg"
            ),
            Pair(
                "Ariana Grande",
                "https://hips.hearstapps.com/hmg-prod/images/ariana_grande_photo_jon_kopaloff_getty_images_465687098.jpg"
            ),
            Pair(
                "The Weeknd",
                "https://ew.com/thmb/5gb2ieo6Wf7L6RUcoG-gV6EpeRY=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/The-Weeknd-01-050823-0ac10c81ad0b475b88f51f032ad578f3.jpg"
            ),
            Pair(
                "Ed Sheeran",
                "https://cdn.britannica.com/17/249617-050-4575AB4C/Ed-Sheeran-performs-Rockefeller-Plaza-Today-Show-New-York-2023.jpg"
            ),
            Pair(
                "BTS",
                "https://people.com/thmb/mKWX8xTZIEL4_XHgMBbAOsaB-fc=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc():focal(979x595:981x597)/bts-members-1-03a9c478f1794c448bcb5f74bf94812c.jpg"
            ),
            Pair(
                "BLACKPINK",
                "https://www.billboard.com/wp-content/uploads/2022/08/03-BLACKPINK-cr-YG-Entertainment-press-2022-billboard-1548.jpg?w=1024"
            ),
            Pair(
                "Olivia Rodrigo",
                "https://upload.wikimedia.org/wikipedia/commons/3/3d/Olivia_Rodrigo_at_Vice_President%27s_West_Wing_office_%282%29.jpg"
            ),
            Pair(
                "Harry Styles",
                "https://m.media-amazon.com/images/M/MV5BMTUxMzU2MTk1OF5BMl5BanBnXkFtZTgwNzg4NjAwMzI@._V1_.jpg"
            ),
            Pair(
                "Billie Eilish",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/BillieEilishO2160622_%2811_of_45%29_%2852152972296%29_%28cropped_2%29.jpg/640px-BillieEilishO2160622_%2811_of_45%29_%2852152972296%29_%28cropped_2%29.jpg"
            ),
            Pair(
                "The Chainsmokers",
                "https://media.wired.com/photos/64e6b0e44d4dba3e7edb6f6f/master/pass/Chainsmokers-Interview-Security-Redux-10.04123419.jpg"
            ),
            Pair(
                "Dua Lipa",
                "https://m.media-amazon.com/images/M/MV5BOWRiMzRlZGUtNjA1Zi00OGJlLTg3Y2QtYjQ3MDNhOTQ1OWVjXkEyXkFqcGdeQXVyODY0NzcxNw@@._V1_FMjpg_UX1000_.jpg"
            ),
            Pair(
                "Maroon 5",
                "https://www.rollingstone.com/wp-content/uploads/2018/06/rs-165985-187_General-PR-Terry-Richardson-PR-HR.jpg"
            ),
            Pair(
                "Imagine Dragons",
                "https://people.com/thmb/DnpRg6Rwh_lgrICqb-u0ocPJUUk=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc():focal(734x280:736x282)/Imagine-Dragons-hit-071423-tout-468a4571891b41dc8d39c312eaf87e49.jpg"
            ),
            Pair(
                "Michael Jackson",
                "https://people.com/thmb/kH5WPADak70KeWwlyIOA4Lbz4Ng=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc():focal(709x499:711x501)/michael-jackson-210aa5866c7d4dd58de8e3af57fe919a.jpg"
            ),
            Pair(
                "Elvis Presley",
                "https://cdn.britannica.com/85/202285-050-EF215325/Elvis-Presley-Girl-Happy-Boris-Sagal.jpg"
            ),
            Pair(
                "Madonna",
                "https://media.vogue.co.uk/photos/63e386d436c1848184f85b25/2:3/w_2560%2Cc_limit/GettyImages-1463285291.jpg"
            ),
        )
    )
}