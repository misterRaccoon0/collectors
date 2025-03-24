package com.buratino.collex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.sharp.AccountCircle
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.AddCircle
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.Menu
import androidx.compose.material.icons.sharp.Notifications
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material.icons.sharp.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.buratino.collex.ui.theme.CollexTheme
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CollexTheme {
                Main()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(){
    val navController = rememberNavController()
    @Suppress("SpellCheckingInspection") val snackbarHostState = remember{ SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .consumeWindowInsets(PaddingValues()),
        topBar = {

            TopAppBar(
                modifier = Modifier
                    .padding(0.dp)
                    .consumeWindowInsets(PaddingValues())
                    .fillMaxWidth()
                ,
                title = {
                    Box (
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center,
                    ){
                        Box(
                            modifier = Modifier.align(Alignment.CenterStart),
                        ) {
                            CompositionLocalProvider(LocalMinimumInteractiveComponentSize.provides(0.dp)) {
                                IconButton(onClick = {}) {
                                    Icon(Icons.Sharp.Menu, contentDescription = "Menu")
                                }
                            }
                        }
                            Box(
                                contentAlignment = Alignment.Center
                            ){
                                Image(
                                    modifier = Modifier
                                        .height(48.dp)
                                        .padding(6.dp),
                                    painter = painterResource(R.drawable.collex),
                                    contentDescription = "App Icon"
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .align(Alignment.CenterEnd)
                            ){
                                Row(
                                    modifier = Modifier.padding(horizontal = 5.dp)
                                ){
                                    CompositionLocalProvider(LocalMinimumInteractiveComponentSize.provides(0.dp)) {
                                        IconButton(
                                            onClick = {},
                                            modifier = Modifier
                                                .fillMaxHeight()
                                        ) {
                                            Icon(
                                                Icons.Sharp.Search,
                                                contentDescription = "Search"
                                            )
                                        }
                                        IconButton(
                                            onClick = {
                                                navController.navigate(Notification)
                                            },
                                            modifier = Modifier
                                                .fillMaxHeight()
                                        ) {
                                            Icon(
                                                Icons.Sharp.Notifications,
                                                contentDescription = "Notifications"
                                            )
                                        }
                                        IconButton(
                                            onClick = {
                                                navController.navigate(Orders)
                                            },
                                            modifier = Modifier
                                                .fillMaxHeight()
                                        ) {
                                            Icon(
                                                Icons.Sharp.ShoppingCart,
                                                contentDescription = "Orders"
                                            )
                                        }
                                    }
                                }
                            }
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar (
                modifier = Modifier
                    .height(102.dp)
                    .padding(0.dp),
                actions = {
                    val btnTextSize = 1.66.em
                    val iconBtnModifier = Modifier
                        .weight(1f, true)
                        .fillMaxHeight()
                        .align(Alignment.CenterVertically)
                    IconButton(
                        onClick = {
                            navController.navigate(Home)
                        },
                        modifier = iconBtnModifier
                    ) {
                            Column (
                                modifier = Modifier.align(Alignment.CenterVertically),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,

                            ){
                                Icon(Icons.Sharp.Home, contentDescription = "Home")
                                Text(
                                    "Home",
                                    fontSize = btnTextSize,
                                    lineHeight = btnTextSize
                                )
                            }
                    }
                    IconButton(
                        onClick = {
                            navController.navigate(Explore)
                        },
                        modifier = iconBtnModifier,
                    ) {
                        Column (
                            modifier = Modifier
                                .fillMaxSize()
                                .align(Alignment.CenterVertically),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Icon(painter = painterResource(R.drawable.explore), contentDescription = "Explore")
                            Text(
                                "Explore",
                                fontSize = btnTextSize,
                                lineHeight = btnTextSize
                            )
                        }
                    }
                    IconButton(
                        onClick = {
                            navController.navigate(Create)
                        },
                        modifier = iconBtnModifier
                    ) {
                        Column (
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Icon(Icons.Sharp.AddCircle, contentDescription = "Post")
                            Text(
                                "Post",
                                fontSize = btnTextSize,
                                lineHeight = btnTextSize
                            )
                        }
                    }
                    IconButton(
                        onClick = {
                            navController.navigate(Chat)
                        },
                        modifier = iconBtnModifier) {
                        Column (
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Icon(painter = painterResource(R.drawable.chat), contentDescription = "Chat")
                            Text(
                                "Chat",
                                fontSize = btnTextSize,
                                lineHeight = btnTextSize
                            )
                        }
                    }
                    IconButton(
                        onClick = {
                            navController.navigate(Account)
                        },
                        modifier = iconBtnModifier) {
                        Column (
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            Icon(Icons.Sharp.AccountCircle, contentDescription = "Account")
                            Text(
                                "Account",
                                fontSize = btnTextSize,
                                lineHeight = btnTextSize
                            )
                        }
                    }
                }
            )
        }
    ) {
        innerPadding -> Column(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
        ){
            DynamicDashboard(navController = navController)
        }
    }

}

@Serializable data object Notification
@Serializable data object Home
@Serializable data object Explore
@Serializable data object Orders
@Serializable data object Create
@Serializable data object Chat
@Serializable data object Account

data class ProductData(
    val title: String,
    val imageUrl : String,
    val price : Double,
    val tags : JSONArray,
)

data class CategoryRow(
    val title : String,
    val posts : List<ProductData>,
    val scrollState : ScrollState
){
    @Composable
    fun copy()= CategoryRow(title,posts,rememberScrollState())
}
@Serializable
data class RawNotificationRow(
    val time : Double,
    val notifications : JsonObject
)
data class NotificationItem(
    val imageUrl: String,
    val title : String,
    val notificationReference : String
)
data class NotificationRow(
    val description : String,
    val notificationList: List<NotificationItem>
)
data class SettingsAccountProfile(
    val profilePicture : ImageBitmap,
    val username : String
)

@Composable
fun NotificationPage(notificationList : List<NotificationRow>, notificationListState : LazyListState){
    Column {
        Row {
            Text(
                "Notifications",
                modifier = Modifier,
                fontSize = 3.em
            )
        }
        LazyColumn(
            state = notificationListState
        ) {
            items(notificationList) {

            }
        }
    }
}

@Composable
fun HomePage(feedList : List<CategoryRow>, feedState : LazyListState){
    ProductColumnView(feedList,feedState)
}

@Composable
fun CategoryRowView(category : CategoryRow){
    Column {
        Row {
            Text(
                category.title,
                fontSize = 4.em
            )
        }
        Row (
            modifier = Modifier.horizontalScroll(category.scrollState)
        ){
            category.posts.map { data ->
                Column {
                    Text(data.imageUrl)
                    Text(data.title)
                    Text(data.price.toString())
                    Text(arrayOf(data.tags).joinToString(","))
                }
            }
        }
    }
}
@Composable
fun ProductColumnView(feedList : List<CategoryRow>, feedState : LazyListState){
    LazyColumn (
        state = feedState
    ){
        items(feedList) {
            category -> CategoryRowView(category)
        }
    }
}

@Composable
fun ProductView(){
    Column {
        Column {
            ImageButton(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .border(border = BorderStroke(1.dp, Color.Black), shape = RectangleShape)
            ) {
                Image(
                    painter = painterResource(R.drawable.collex),
                    contentDescription = "Product Image"
                )
            }
            Column {
                Text("Product Title")
                Text("Product Price")
                Text("Product Tags")
            }
        }
    }
}

@Composable
fun ExplorePage(options: List<String>, selectedOption: String, onOptionChange : (String) -> Unit){
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedOption by rememberSaveable { mutableStateOf(selectedOption) }
    Column {
        Row(
            modifier = Modifier
                .padding(horizontal = 18.dp, vertical = 12.dp)
        ) {
            Text(
                "Explore",
                modifier = Modifier.weight(1f,true),
                fontSize = 5.5.em
                )
            Row(
                modifier = Modifier
                    .padding(0.dp)
                    .align(Alignment.CenterVertically)
            ){
                CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides 0.dp){
                    TextButton(
                        modifier = Modifier
                            .wrapContentHeight()
                            .size(width=128.dp,height=32.dp)
                            .padding(0.dp)
                            .border(border = BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(20))
                            .align(Alignment.CenterVertically),
                        onClick = {
                            expanded = !expanded
                        },
                        contentPadding = PaddingValues(),
                    ) {
                        Text(
                            selectedOption,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 12.dp)
                                .weight(1f, true)
                                .layout({
                                    measurable, constraints ->
                                        val placeable = measurable.measure(constraints.copy(minWidth = 0, minHeight = 0))
                                        layout (constraints.maxWidth, constraints.maxHeight){
                                            val y = (constraints.maxHeight - placeable.height) / 2
                                            placeable.placeRelative(0,y)
                                        }
                                })
                            ,
                            fontSize = 2.86.em,
                            textAlign = TextAlign.Center,
                            color = Color.DarkGray
                        )
                        Icon(if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown, contentDescription = "expanded", tint = Color.Black)
                    }
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    },
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = {
                                Text(option, color = Color.DarkGray)
                            },
                            onClick = {
                                selectedOption = option
                                onOptionChange(option)
                                expanded = false
                            },
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun OrdersPage(){
    Column {
        Box {
            TextButton(onClick = { /*TODO*/ }) {
                Text("Orders")
            }
            TextButton(onClick = { /*TODO*/ }) {
                Text("Orders")
            }
            TextButton(onClick = { /*TODO*/ }) {
                Text("Orders")
            }
        }
        Column{
            LazyColumn {
                items(10){
                    Column {
                        Text("Order")
                        Text("Order")
                        Text("Order")
                    }
                }
            }
        }
    }
}

data class ChatConversation(
    val userId : Int,
    val conversation : LazyListState
)
@Serializable
data class RawConversation(
    val userId: Int,
    val conversation : JsonObject
)
@Composable
fun CreatePage(){
    Column {
        Row {
            Text(
                "Post a product.",
                modifier = Modifier,
                fontSize = 3.em
            )
        }
    }
}
@Composable
fun ChatPage(){
    Column {
        Row (
            modifier = Modifier.padding(horizontal=22.dp, vertical = 10.dp)
        ){
            Text(
                "Chat",
                modifier = Modifier,
                fontSize = 6.em
            )
        }
    }
}
@Composable
fun AccountPage(){
    Text("Account", fontSize = 3.em)
}
@Composable
fun AccountSettingsPage(accountProfile: SettingsAccountProfile){
    Column {
        Row {
            Image(accountProfile.profilePicture, contentDescription = "profile")
            Text(
                accountProfile.username
            )
        }
        Row {  }
        Column {
            Column {
                Text("Account Settings")
                Row {
                    TextButton(onClick = { /*TODO*/ }) {
                        Text("Edit Profile")
                    }

                }
            }
            Column {
                TextButton(onClick = { /*TODO*/ }) {
                    
                }
            }
        }
    }
}
@Composable
fun UserProfilePage(){

}
@Composable
fun PublicProfilePage(){

}
@Composable
fun PaymentPage(){

}

@Composable
fun DynamicDashboard(navController : NavHostController){
    val row = CategoryRow(
        "Shoes",
        listOf(
            ProductData("Jordan's", "/image/jordan", 4_000.5, JSONArray(listOf("shoe","rare","Y2K"))),
            ProductData("Nike", "/image/nike", 4_000.5, JSONArray(listOf("shoe","rare","Y2K"))),
            ProductData("Adidas", "/image/adidas", 4_000.5, JSONArray(listOf("shoe","rare","Y2K")))
        ),
        rememberScrollState()
    )
    val rows = listOf<CategoryRow>(row,row.copy(),row.copy())
    val feedState = rememberLazyListState()
    val options = listOf<String>("Discover", "Local")
    var selectedOption by rememberSaveable { mutableStateOf(options[0]) }
    NavHost(navController = navController, startDestination = Home){
        composable<Home>{
            HomePage(rows, feedState)
        }
        composable<Explore>{
            ExplorePage(options,selectedOption, onOptionChange = {option -> selectedOption = option})
        }
        composable <Create>{
            CreatePage()
        }
        composable<Chat>{
            ChatPage()
        }
        composable<Account>{
            AccountPage()
        }
        composable<Notification>{
            NotificationPage()
        }
        composable <Orders>{
            OrdersPage()
        }
    }
}