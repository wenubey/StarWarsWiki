package com.wenubey.starwarswiki.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.wenubey.starwarswiki.data.local.entities.CharacterEntity
import com.wenubey.starwarswiki.data.local.entities.FilmEntity
import java.lang.reflect.ParameterizedType

@Database(entities = [CharacterEntity::class, FilmEntity::class], version = 1, exportSchema = false)
@TypeConverters(MyConverter::class)
abstract class StarWarsDatabase: RoomDatabase() {

    abstract val dao: StarWarsDao
}


internal class MyConverter {

    private val moshi: Moshi = Moshi.Builder().build()

    private val adapter: JsonAdapter<List<String>> by lazy {
        val type: ParameterizedType =
            Types.newParameterizedType(List::class.java, String::class.java)
        moshi.adapter(type)
    }

    @TypeConverter
    fun toList(value: String): List<String>? {
        return adapter.fromJson(value)
    }

    @TypeConverter
    fun fromList(value: List<String>): String {
        return adapter.toJson(value)
    }
}
