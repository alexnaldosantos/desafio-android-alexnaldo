package br.com.viavarejo.desafio.repositories

import br.com.viavarejo.desafio.api.*
import br.com.viavarejo.desafio.api.responses.CharacterDataResponse
import br.com.viavarejo.desafio.models.Character
import br.com.viavarejo.desafio.utils.Utils
import java.util.*

// don't do it for real app !!!
const val MARVEL_PRIVATE_KEY = "b9e9cfcc343f1d38066f795f10bf2a313dab7452"
const val MARVEL_API_KEY = "8105ca2fc25bd49562b17cb07b576415"

open class MarvelRepositoryImpl (private val api: MarvelApi) : MarvelRepository {

    private val defaultLimit = 20
    private var offset = 0
    private val timestamp: Long = Date().time

    private fun hash() = Utils.md5(timestamp.toString() + MARVEL_PRIVATE_KEY + MARVEL_API_KEY)

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
    private fun cacheCharacters(data: CharacterDataResponse) : List<Character> {
        offset += data.results.count()
        cache.addAll(data.results)
        return cache
    }
}