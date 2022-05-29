package raq.lop.io.marvelkotlinmvvm.repository

import raq.lop.io.marvelkotlinmvvm.data.remote.Service
import java.lang.reflect.Constructor
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val api: Service
)
{
    suspend fun list(nameStartsWith: String? =null)= api.list(nameStartsWith)
    suspend fun getComics(characterId: Int) = api.getComics(characterId)
}