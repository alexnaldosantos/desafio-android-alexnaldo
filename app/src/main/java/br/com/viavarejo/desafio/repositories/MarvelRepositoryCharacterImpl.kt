package br.com.viavarejo.desafio.repositories

import br.com.viavarejo.desafio.api.MarvelApi
import br.com.viavarejo.desafio.api.Resource
import br.com.viavarejo.desafio.api.SafeApi
import br.com.viavarejo.desafio.api.responses.CharacterDataResponse
import br.com.viavarejo.desafio.models.Character

open class MarvelRepositoryCharacterImpl(api: MarvelApi) : MarvelRepositoryCharacter,
    MarvelRepositoryBase(api) {

    private val defaultLimit = 20
    private var offset = 0

    override suspend fun getCharacters(): Resource<List<Character>> {
        val call = SafeApi.safeCall {
            api.getCharacters(
                "name",
                timestamp.toString(),
                MARVEL_API_KEY,
                hash(),
                offset,
                defaultLimit
            )
        }
        return when (call) {
            is Resource.Success -> Resource.Success(cacheCharacters(call.value.data))
            is Resource.Failure -> call
            is Resource.Requesting -> call
        }
    }

    private val cache = mutableListOf<Character>()
    private fun cacheCharacters(data: CharacterDataResponse): List<Character> {
        offset += data.results.count()
        cache.addAll(data.results)
        return cache
    }
}