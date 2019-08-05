package com.edbutch.materialcountries.data.db

import androidx.room.*

@Dao
interface FavoritesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: Favorite): Long


    @Delete
    fun deleteFavorite(favorite: Favorite): Int

    @Query("SELECT * FROM Favorite")
    fun getFavorites(): List<Favorite>

}