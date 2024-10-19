package com.example.secondwishlistapp

// The sealed keyword is used for restriction of inheritance of outside of the file.
// Inside sealed class
// we must define Objects according to the requirements which by defaults inherit Parent Class
sealed class Screen(val route: String) {
//    The object keyword defines that only one instance will be created through out the program
    object HomeScreen: Screen("home_screen")
    object AddScreen: Screen("add_screen")
}

