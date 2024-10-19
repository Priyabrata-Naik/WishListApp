package com.example.secondwishlistapp

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.secondwishlistapp.data.Wish

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    navController: NavController,
    viewModel: WishViewModel
){
//    Here we use rememberScaffoldState() to update the UI & set it manually
    val scaffoldState = rememberScaffoldState()
//    Scaffold define UI structure, it includes top bar, bottom bar, floating action button (FAB), drawer, and content
    Scaffold(
//        Recomposes the Scaffold UI
        scaffoldState = scaffoldState,
        topBar = {
            AppBarView(
                title = "WishList"
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 20.dp),
                contentColor = Color.White,
                backgroundColor = Color.Black,
                onClick = {
//                    Toast.makeText(context, "Add Button Clicked", Toast.LENGTH_LONG).show()
//                    Passing to the AddEditDetailScreen view with the value id = 0L
                    navController.navigate(Screen.AddScreen.route + "/0L")

                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Floating Add Button")
            }
        }

    ) {
//        Retrieves all wishes, initially it was empty list
        val wishlist = viewModel.getAllWishes.collectAsState(initial = listOf())
//        LazyColumn is the same as RecyclerView of Native Android
//        It fills only visible screen with UI field
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .background(Color.Red)
        ){
            items(wishlist.value, key={ wish-> wish.id } ){
                wish ->
                val dismissState = androidx.compose.material.rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToStart) {
                            viewModel.deleteAWish(wish)
                        }
                        true
                    }
                )

                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        val color by animateColorAsState(
                            if(dismissState.dismissDirection == DismissDirection.EndToStart) Color.LightGray else Color.Transparent,
                            label = ""
                        )
                        val alignment = Alignment.CenterEnd
                        Box(
                            Modifier.fillMaxSize().background(color).padding(horizontal = 20.dp),
                            contentAlignment = alignment
                        ){
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Delete Icon",
                                tint = colorResource(R.color.delete_tint)
                            )
                        }

                    },
                    directions = setOf(DismissDirection.EndToStart),
                    dismissThresholds = { FractionalThreshold(1f) },
                    dismissContent = {
                        WishItem(wish = wish) {
                            val id = wish.id
                            navController.navigate(Screen.AddScreen.route + "/$id")
                        }
                    }
                )
            }
        }
    }

}


@Composable
fun WishItem(wish: Wish, onClick: () -> Unit){
//    Card is used for grouping same type of fields
    Card(
        modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
        .clickable {
            onClick()
        },
        elevation = 20.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(20)
    ) {

        Column(modifier = Modifier.padding(16.dp)){
            Text(text = wish.title, fontWeight = FontWeight.ExtraBold, fontSize = 16.sp)
            Text(text = wish.description, fontWeight = FontWeight.W400)
        }
    }
}