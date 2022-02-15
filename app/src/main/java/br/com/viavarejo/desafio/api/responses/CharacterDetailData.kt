package br.com.viavarejo.desafio.api.responses

import br.com.viavarejo.desafio.models.Detail

data class CharacterDetailData(val offset: Int, val limit: Int, val total: Int, val count: Int,
                               var results: MutableList<Detail>)