package raq.lop.io.marvelkotlinmvvm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import raq.lop.io.marvelkotlinmvvm.data.model.character.CharacterModel

@Database(entities = [CharacterModel::class], version=1, exportSchema = false)
@TypeConverters(MarvelConverters::class)
abstract class MarvelDatabase : RoomDatabase(){
    abstract fun marvelDao(): MarvelDao
}