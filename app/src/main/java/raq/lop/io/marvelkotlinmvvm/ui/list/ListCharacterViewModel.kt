package raq.lop.io.marvelkotlinmvvm.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import raq.lop.io.marvelkotlinmvvm.data.model.character.CharacterModelResponse
import raq.lop.io.marvelkotlinmvvm.repository.MarvelRepository
import raq.lop.io.marvelkotlinmvvm.ui.state.ResourceState
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ListCharacterViewModel @Inject constructor(
    private val repository: MarvelRepository
): ViewModel() {
    private val _list =
        MutableStateFlow<ResourceState<CharacterModelResponse>>(ResourceState.Loading())
    val list: StateFlow<ResourceState<CharacterModelResponse>> = _list

    init{
        fetch()
    }

    private fun fetch() = viewModelScope.launch {
        safeFetch()
    }

    private suspend fun safeFetch(){
        try{
            val response = repository.list()
            _list.value = handleResponse(response)
        }catch (t: Throwable){
            val response = repository.list()
            when(t){
                is IOException -> _list.value = ResourceState.Error(message = "Erro na conexão", data = response.body())
                else -> _list.value = ResourceState.Error(message = "Falha durante conversão de dados", data=response.body())
            }
        }

    }

    private fun handleResponse(response: Response<CharacterModelResponse>): ResourceState<CharacterModelResponse> {
        if(response.isSuccessful){
            response.body()?.let {
                values ->
                return ResourceState.Success(values)
            }
        }

        return ResourceState.Error(response.message(), response.body())
    }
}