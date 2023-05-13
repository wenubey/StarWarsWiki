package com.wenubey.starwarswiki.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wenubey.starwarswiki.data.local.StarWarsDao
import com.wenubey.starwarswiki.data.local.entities.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class StarWarsDatabase: RoomDatabase() {

    abstract val dao: StarWarsDao
}