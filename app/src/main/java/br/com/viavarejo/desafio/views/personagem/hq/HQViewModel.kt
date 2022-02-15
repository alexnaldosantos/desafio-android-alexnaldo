package br.com.viavarejo.desafio.views.personagem.hq

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.viavarejo.desafio.api.Resource
import br.com.viavarejo.desafio.models.Character
import br.com.viavarejo.desafio.models.Detail
import br.com.viavarejo.desafio.repositories.MarvelRepositoryCharacter
import br.com.viavarejo.desafio.repositories.MarvelRepositoryHQ
import kotlinx.coroutines.launch

open class HQViewModel(
    private val repository: MarvelRepositoryHQ,
    public val character: Character
) : ViewModel() {

    private val _hq: MutableLiveData<Resource<Detail>> = MutableLiveData()
    val hq: LiveData<Resource<Detail>>
        get() = _hq

    fun getHQ() = viewModelScope.launch {
        _hq.value = Resource.Requesting
        _hq.value = repository.getHQ(character.id.toString())
    }
}