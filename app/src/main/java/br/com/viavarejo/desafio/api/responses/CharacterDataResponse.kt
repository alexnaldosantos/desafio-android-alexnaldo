package br.com.viavarejo.desafio.api.responses
import br.com.viavarejo.desafio.models.Character

data class CharacterDataResponse(val offset: Int, val limit: Int, val total: Int, val count: Int,
                         var results: List<Character>)