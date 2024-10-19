package com.example.secondwishlistapp.data

import kotlinx.coroutines.flow.Flow

/*Responsible for abstracting data operations.
It fetches data from the database (via the DAO) and provides it to the ViewModel in a clean way.
Repository class can have more operations like network calls or other sources*/
// So it is a recommend way to use the repository class.
class WishRepository(private val wishDao: WishDao) {

    suspend fun addAWish(wish: Wish){
        wishDao.addAWish(wish)
    }

    fun getAllWishes(): Flow<List<Wish>> = wishDao.getAllWishes()

    fun getAWishById(id: Long): Flow<Wish> {
        return wishDao.getAWishById(id)
    }

    suspend fun updateAWish(wish: Wish){
        wishDao.updateAWish(wish)
    }

    suspend fun deleteAWish(wish: Wish){
        wishDao.deleteAWish(wish)
    }

}