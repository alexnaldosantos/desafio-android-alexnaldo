package br.com.viavarejo.desafio.repositories

import br.com.viavarejo.desafio.api.Resource
import br.com.viavarejo.desafio.models.Detail

open interface MarvelRepositoryHQ {
    suspend fun getHQ(characterId: String): Resource<Detail>
}