package raq.lop.io.marvelkotlinmvvm.repository

import raq.lop.io.marvelkotlinmvvm.data.local.MarvelDao
import raq.lop.io.marvelkotlinmvvm.data.model.character.CharacterModel
import raq.lop.io.marvelkotlinmvvm.data.remote.Service
import java.lang.reflect.Constructor
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val api: Service,
    private val dao: MarvelDao
)
{
    suspend fun list(nameStartsWith: String? =null)= api.list(nameStartsWith)
    suspend fun getComics(characterId: Int) = api.getComics(characterId)

    suspend fun insert(characterModel: CharacterModel) = dao.insert(characterModel)
    fun getAll() = dao.getAll()
    suspend fun delete(characterModel: CharacterModel) = dao.delete(characterModel)
}