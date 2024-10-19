package com.example.secondwishlistapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(viewModel: WishViewModel = viewModel(),
               navController: NavHostController = rememberNavController(),
               modifier: Modifier = Modifier
){
    NavHost(
        navController= navController,
        startDestination = Screen.HomeScreen.route
    ){
        composable(Screen.HomeScreen.route){
            HomeView(navController, viewModel)
        }

//        Here we pass the id variable to composable screen
//        navArgument defines the property of id
        /*HERE TO REMEMBER
        * The id variable of Wish class is maintained by Database(WishDatabase) after inserted to database
        * Otherwise by default it takes OL as id in new wish items.*/
        composable(Screen.AddScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.LongType
                    defaultValue = 0L
                    nullable = false
                }
            )
        ){ entry->
//            !! -> not-null assertion operator. It is used as I am sure that arguments field is not null. Other wise app will crash
//            We can use safer approach like Elvish Operator.
            val id = if(entry.arguments != null)  entry.arguments!!.getLong("id") else 0L
            AddEditDetailView(id = id, viewModel = viewModel , navController = navController)
        }
    }
}