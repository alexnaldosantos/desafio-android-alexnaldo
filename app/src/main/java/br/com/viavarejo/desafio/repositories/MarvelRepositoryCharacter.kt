package br.com.viavarejo.desafio.repositories

import br.com.viavarejo.desafio.api.Resource
import br.com.viavarejo.desafio.models.Character
import br.com.viavarejo.desafio.models.Detail

open interface MarvelRepositoryCharacter {
    suspend fun getCharacters(): Resource<List<Character>>
}