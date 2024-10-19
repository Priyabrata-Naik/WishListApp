package com.example.secondwishlistapp.data

import androidx.room.Database
import androidx.room.RoomDatabase


// Creating database with required fields
@Database(
    entities = [Wish::class],
    version = 1,
    exportSchema = false
)
abstract class WishDatabase: RoomDatabase() {

    abstract fun wishDao(): WishDao

}