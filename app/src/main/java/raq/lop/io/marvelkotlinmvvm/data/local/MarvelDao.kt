package raq.lop.io.marvelkotlinmvvm.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import raq.lop.io.marvelkotlinmvvm.data.model.character.CharacterModel

@Dao
interface MarvelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characterModel: CharacterModel): Long

    @Query("SELECT * FROM characterModel ORDER BY id")
    fun getAll(): Flow<List<CharacterModel>>

    @Delete
    suspend fun delete(characterModel: CharacterModel)
}