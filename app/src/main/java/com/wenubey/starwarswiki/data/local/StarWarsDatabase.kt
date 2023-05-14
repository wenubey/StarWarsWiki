package com.wenubey.starwarswiki.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wenubey.starwarswiki.data.local.entities.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StarWarsTypeConverter::class)
abstract class StarWarsDatabase : RoomDatabase() {
    abstract val dao: StarWarsDao
}
