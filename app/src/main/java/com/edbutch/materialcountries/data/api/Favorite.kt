package com.edbutch.materialcountries.data.api
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Favorite(
    @PrimaryKey(autoGenerate = false) var pKey: Int,

    @ColumnInfo(name = "area")
    val area: Double = 0.0,

    @ColumnInfo(name = "nativeName")
    val nativeName: String = "",

    @ColumnInfo(name = "capital")
    val capital: String = "",

    @ColumnInfo(name = "flag")
    val flag: String = "",

    @ColumnInfo(name = "languages")
    val languages: String,

    @ColumnInfo(name = "borders")
    val borders: String,

    @ColumnInfo(name = "subregion")
    val subregion: String = "",

    @ColumnInfo(name = "population")
    val population: Int = 0,

    @ColumnInfo(name = "numericCode")
    val numericCode: String = "",

    @ColumnInfo(name = "name")
    val name: String = "",


    @ColumnInfo(name = "region")
    val region: String = "",

    @ColumnInfo(name = "currencies")
    val currencies: String
) : Serializable