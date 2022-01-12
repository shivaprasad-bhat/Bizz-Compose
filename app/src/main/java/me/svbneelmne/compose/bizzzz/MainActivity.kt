package me.svbneelmne.compose.bizzzz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.svbneelmne.compose.bizzzz.MainActivity.Companion.KEY_NAME
import me.svbneelmne.compose.bizzzz.MainActivity.Companion.KEY_TITLE
import me.svbneelmne.compose.bizzzz.MainActivity.Companion.KEY_USERNAME
import me.svbneelmne.compose.bizzzz.ui.theme.BizzzTheme

class MainActivity : ComponentActivity() {
    companion object {
        const val KEY_NAME = "Shivaprasad"
        const val KEY_TITLE = "Android App Developer"
        const val KEY_USERNAME = "@svbneelmane"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BizzzTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    CreateBizCard()
                }
            }
        }
    }
}

fun getProfileData(): List<ProfileData> {
    return listOf(
        ProfileData(
            name = "Whatsapp",
            url = "+91 8747068436",
            imageAsset = R.drawable.whatsapp
        ),
        ProfileData(
            name = "Email",
            url = "https://svbneelmane.me",
            imageAsset = R.drawable.email
        ),
        ProfileData(
            name = "Twitter",
            url = "https://twitter.com/svbneelmane",
            imageAsset = R.drawable.twitter
        ),
        ProfileData(
            name = "Website",
            url = "https://svbneelmane.me",
            imageAsset = R.drawable.website
        ),
        ProfileData(
            name = "Facebook",
            url = "https://fb.com/svbneelmane",
            imageAsset = R.drawable.facebook
        ),
        ProfileData(
            name = "LinkedIn",
            url = "https://linkedin.com/in/svbneelmane",
            imageAsset = R.drawable.linkedin
        )
    )
}


@Composable
fun CreateBizCard() {
    val buttonClickState = remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = 4.dp,
        ) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileImage()
                Divider(
                    thickness = 5.dp
                )
                ProfileNameAndInfo()
                PortfolioButton(buttonClickState)
            }
        }
    }
}

@Composable
private fun PortfolioButton(buttonClickState: MutableState<Boolean>) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                buttonClickState.value = !buttonClickState.value
            },
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "Portfolio", style = MaterialTheme.typography.button)
        }
        if (buttonClickState.value) {
            Content()
        } else {
            Box {}
        }
    }
}


@Composable
private fun ProfileNameAndInfo() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 5.dp)
    ) {
        Text(
            text = KEY_NAME,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primaryVariant
        )
        Text(
            text = KEY_TITLE,
            modifier = Modifier.padding(3.dp)
        )
        Text(
            text = KEY_USERNAME,
            modifier = Modifier.padding(3.dp),
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
private fun ProfileImage(
    modifier: Modifier = Modifier,
    @DrawableRes image: Int = R.drawable.shivaprasad
) {
    Surface(
        modifier = modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        elevation = 4.dp,
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "Profile Image",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun Content() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxSize(),
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(width = 2.dp, color = Color.LightGray),
        ) {
            Portfolio(data = getProfileData())
        }
    }
}

@Composable
fun Portfolio(data: List<ProfileData>) {
    LazyColumn {
        items(data) { item ->
            Card(
                modifier = Modifier
                    .padding(13.dp)
                    .fillMaxWidth(),
                shape = RectangleShape,
                elevation = 5.dp
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(MaterialTheme.colors.surface)

                ) {
                    ProfileImage(
                        modifier = Modifier.size(100.dp),
                        image = item.imageAsset
                    )
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(alignment = Alignment.CenterVertically)
                    ) {
                        Text(text = item.name, fontWeight = FontWeight.Bold)
                        Text(text = item.url, style = MaterialTheme.typography.body2)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BizzzTheme {
        CreateBizCard()
    }
}