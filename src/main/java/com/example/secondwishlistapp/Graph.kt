package com.example.secondwishlistapp

import android.content.Context
import androidx.room.Room
import com.example.secondwishlistapp.data.WishDatabase
import com.example.secondwishlistapp.data.WishRepository

object Graph {
//    lateinit refers to defer initialization
    lateinit var database: WishDatabase

//    It is initialized only once, when it is executed
    val wishRepository by lazy{
        WishRepository(wishDao = database.wishDao())
    }

//    Assigning the database variable with the help of Room Database(WishDatabase)
    fun provide(context: Context){
        database = Room.databaseBuilder(context, WishDatabase::class.java, "wishlist.db").build()
    }

}