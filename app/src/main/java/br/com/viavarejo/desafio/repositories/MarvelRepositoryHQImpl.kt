package br.com.viavarejo.desafio.repositories

import br.com.viavarejo.desafio.BuildConfig
import br.com.viavarejo.desafio.api.MarvelApi
import br.com.viavarejo.desafio.api.Resource
import br.com.viavarejo.desafio.api.SafeApi
import br.com.viavarejo.desafio.api.responses.CharacterDetailData
import br.com.viavarejo.desafio.models.Detail

open class MarvelRepositoryHQImpl(api: MarvelApi) : MarvelRepositoryHQ,
    MarvelRepositoryBase(api) {

    override suspend fun getHQ(characterId: String): Resource<Detail> {
        val call = SafeApi.safeCall {
            api.getComicsByCharacterId(
                characterId,
                timestamp.toString(),
                MARVEL_API_KEY,
                hash()
            )
        }
        return when (call) {
            is Resource.Success -> Resource.Success(getMoreExpensiveHQ(call.value.data))
            is Resource.Failure -> call
            is Resource.Requesting -> call
        }
    }

    private fun getMoreExpensiveHQ(data: CharacterDetailData): Detail {
        var detail: Detail? = null
        data.results.forEach { itemDetail ->
            itemDetail.prices?.forEach { price ->
                if (detail == null || price.price > detail?.moreExpensive?.price!!) {
                    detail = itemDetail
                    detail!!.moreExpensive = price
                }
            }
        }
        return detail!!
    }
}