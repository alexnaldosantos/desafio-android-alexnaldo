package br.com.viavarejo.desafio

import br.com.viavarejo.desafio.api.responses.CharacterDataResponse
import br.com.viavarejo.desafio.api.responses.CharacterDetailData
import br.com.viavarejo.desafio.api.responses.CharacterResponse
import br.com.viavarejo.desafio.api.responses.DetailResponse
import br.com.viavarejo.desafio.models.Character
import br.com.viavarejo.desafio.models.Detail
import br.com.viavarejo.desafio.models.Price
import br.com.viavarejo.desafio.models.Thumbnail

object DummyData {

    fun characterDummy() = Character(
        1, "name", "description", Thumbnail("path", "jpg")
    )

    fun characterResponseDummy() = CharacterResponse(
        1, "200",
        CharacterDataResponse(
            0, 0, 0, 0, listOf(
                characterDummy()
            )
        )
    )

    fun detailDummy() = Detail(
        1,
        "title",
        "description",
        listOf(Price("type", 0.0)),
        Thumbnail("path", "jpg"),
        Price("type", 0.00)
    )

    fun HQResponseDummy() = DetailResponse(
        0, "200", CharacterDetailData(
            0, 0, 0, 0, listOf(
                detailDummy()
            )
        )
    )
}