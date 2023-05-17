package com.wenubey.starwarswiki.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.wenubey.starwarswiki.data.local.entities.FilmEntity
import com.wenubey.starwarswiki.data.local.entities.ImageEntity
import com.wenubey.starwarswiki.data.local.entities.PlanetEntity
import com.wenubey.starwarswiki.data.local.entities.SpecieEntity
import com.wenubey.starwarswiki.data.local.entities.StarshipEntity
import com.wenubey.starwarswiki.data.local.entities.VehicleEntity


class StarWarsTypeConverter {

    private inline fun <reified T> fromJson(json: String?, typeToken: TypeToken<out T>): T? {
        return if (json == null) null else Gson().fromJson(json, typeToken.type)
    }

    private fun <T> toJson(data: T?): String? {
        return data?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun imageUrlFromJson(json: String?): ImageEntity? =
        fromJson(json, object : TypeToken<ImageEntity>() {})

    @TypeConverter
    fun imageUrlToJson(imageUrl: ImageEntity?): String? =
        toJson(imageUrl)

    @TypeConverter
    fun planetFromJson(json: String?): PlanetEntity? =
        fromJson(json, object : TypeToken<PlanetEntity>() {})


    @TypeConverter
    fun planetToJson(planetEntity: PlanetEntity?): String? =
        toJson(planetEntity)


    @TypeConverter
    fun filmFromJson(json: String?): List<FilmEntity>? =
        fromJson(json, object : TypeToken<List<FilmEntity>>() {})


    @TypeConverter
    fun filmToJson(films: List<FilmEntity>?): String? =
        toJson(films)


    @TypeConverter
    fun vehicleFromJson(json: String?): List<VehicleEntity>? =
        fromJson(json, object : TypeToken<List<VehicleEntity>>() {})


    @TypeConverter
    fun vehicleToJson(vehicles: List<VehicleEntity>?): String? =
        toJson(vehicles)


    @TypeConverter
    fun specieFromJson(json: String?): List<SpecieEntity>? =
        fromJson(json, object : TypeToken<List<SpecieEntity>>() {})


    @TypeConverter
    fun specieToJson(species: List<SpecieEntity>?): String? =
        toJson(species)


    @TypeConverter
    fun starshipFromJson(json: String?): List<StarshipEntity>? =
        fromJson(json, object : TypeToken<List<StarshipEntity>>() {})


    @TypeConverter
    fun starshipToJson(starships: List<StarshipEntity>?): String? =
        toJson(starships)

}