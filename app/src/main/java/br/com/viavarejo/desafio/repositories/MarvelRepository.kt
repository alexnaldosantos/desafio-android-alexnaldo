package br.com.viavarejo.desafio.repositories

import androidx.lifecycle.LiveData
import br.com.viavarejo.desafio.api.Resource
import br.com.viavarejo.desafio.models.Character

open interface MarvelRepository {
    suspend fun getCharacters(page: Int): Resource<List<Character>>
}