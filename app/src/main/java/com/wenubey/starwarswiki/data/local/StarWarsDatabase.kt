package com.wenubey.starwarswiki.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wenubey.starwarswiki.data.local.entities.CharacterEntity
import com.wenubey.starwarswiki.data.local.entities.FilmEntity

@Database(entities = [CharacterEntity::class, FilmEntity::class], version = 1, exportSchema = false)
abstract class StarWarsDatabase: RoomDatabase() {

    abstract val dao: StarWarsDao
}