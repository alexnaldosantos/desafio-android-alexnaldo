package br.com.viavarejo.desafio.api

import androidx.lifecycle.LiveData
import br.com.viavarejo.desafio.api.responses.CharacterResponse
import br.com.viavarejo.desafio.api.responses.DetailResponse
import org.jetbrains.annotations.Nullable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MarvelApi {
    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @SuppressWarnings("SameParameterValue") @Nullable @Query("orderBy") orderBy: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String,
        @Nullable @Query("offset") offset: Int,
        @Query("limit") limit: Int = 20
    ): CharacterResponse

    @GET("/v1/public/characters/{characterId}/comics")
    suspend fun getComicsByCharacterId(
        @Path("characterId") characterId: String,
        @Query("ts") ts: String,
        @Query("apikey") apiKey: String,
        @Query("hash") hash: String
    )
            : DetailResponse
}