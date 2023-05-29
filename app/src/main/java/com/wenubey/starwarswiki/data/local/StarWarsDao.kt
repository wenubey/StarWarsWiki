package com.wenubey.starwarswiki.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.wenubey.starwarswiki.data.local.entities.CharacterEntity

@Dao
interface StarWarsDao {

    @Upsert
    suspend fun upsertAll(characters: List<CharacterEntity>)

    @Query("SELECT * FROM characters")
    fun pagingSource(): PagingSource<Int, CharacterEntity>

    @Query("SELECT * FROM characters WHERE name LIKE '%' || :query || '%'")
    fun getSearchCharacters(query: String): List<CharacterEntity>

    @Query("DELETE FROM characters")
    suspend fun clearAll()
}

