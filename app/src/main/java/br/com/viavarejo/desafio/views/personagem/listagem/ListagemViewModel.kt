package br.com.viavarejo.desafio.views.personagem.listagem

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.viavarejo.desafio.api.Resource
import br.com.viavarejo.desafio.models.Character
import br.com.viavarejo.desafio.repositories.MarvelRepositoryCharacter
import br.com.viavarejo.desafio.services.RouterActivityService
import kotlinx.coroutines.launch

open class ListagemViewModel(private val repository: MarvelRepositoryCharacter, private val router: RouterActivityService) : ViewModel() {

    private val _characters: MutableLiveData<Resource<List<Character>>> = MutableLiveData()
    val characters: LiveData<Resource<List<Character>>>
        get() = _characters

    fun getCharacters() = viewModelScope.launch {
        _characters.value = Resource.Requesting
        _characters.value = repository.getCharacters()
    }

    fun showDetail(context: Context, character: Character) {
        router.gotoListagemDetail(context, character)
    }
}