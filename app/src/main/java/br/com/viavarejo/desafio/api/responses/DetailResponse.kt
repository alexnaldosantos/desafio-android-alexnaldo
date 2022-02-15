package br.com.viavarejo.desafio.api.responses

data class DetailResponse(val code: Int, val status: String, val copyright: String,
                          val attributionText: String, val attributionHTML: String,
                          val etag: String, val data: CharacterDetailData)