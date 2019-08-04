package com.edbutch.materialcountries.data.api

import androidx.room.*
import com.edbutch.materialcountries.data.db.Country.CurrenciesItem
import com.edbutch.materialcountries.data.db.Country.LanguagesItem
import com.edbutch.materialcountries.data.db.Country.RegionalBlocsItem
import com.edbutch.materialcountries.data.db.Country.Translations


import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Dao
interface FavoritesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: Favorite): Long


    @Delete
    fun deleteFavorite(favorite: Favorite): Int

    @Query("SELECT * FROM Favorite")
    fun getFavorites(): List<Favorite>
    

}