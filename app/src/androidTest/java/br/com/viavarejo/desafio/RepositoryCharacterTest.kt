package br.com.viavarejo.desafio

import br.com.viavarejo.desafio.api.MarvelApi
import br.com.viavarejo.desafio.api.Resource
import br.com.viavarejo.desafio.api.SafeApi
import br.com.viavarejo.desafio.api.responses.CharacterDataResponse
import br.com.viavarejo.desafio.api.responses.CharacterResponse
import br.com.viavarejo.desafio.models.Character
import br.com.viavarejo.desafio.models.Thumbnail
import br.com.viavarejo.desafio.repositories.MarvelRepositoryCharacter
import br.com.viavarejo.desafio.repositories.MarvelRepositoryCharacterImpl
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.jetbrains.annotations.Nullable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.component.inject
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.mock.MockProviderRule
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.verify
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RepositoryCharacterTest : KoinBaseTest() {

    override fun getKoinModules(): Module = module(override = true) {
        single { mockk<MarvelApi>(relaxed = true) }
        single<MarvelRepositoryCharacter> { MarvelRepositoryCharacterImpl(get()) }
    }

    private val api by inject<MarvelApi>()
    private val repository by inject<MarvelRepositoryCharacter>()

    @Test
    fun givenResourceCharacter_whenGetCharacters_thenReturnsSuccessfully() = runBlocking {
        // given
        val orderBy = slot<String>()
        val ts = slot<String>()
        val apiKey = slot<String>()
        val hash = slot<String>()
        val offset = slot<Int>()
        val limit = slot<Int>()

        coEvery {
            api.getCharacters(
                capture(orderBy),
                capture(ts),
                capture(apiKey),
                capture(hash),
                capture(offset),
                capture(limit)
            )
        } returns DummyData.characterDummy()
        // when
        val result = repository.getCharacters()
        // then
        assertTrue(result is Resource.Success)
    }

    @Test
    fun givenResourceCharacter_whenGetCharactersFail_thenReturnsThrowable() = runBlocking {
        // given
        val orderBy = slot<String>()
        val ts = slot<String>()
        val apiKey = slot<String>()
        val hash = slot<String>()
        val offset = slot<Int>()
        val limit = slot<Int>()

        coEvery {
            api.getCharacters(
                capture(orderBy),
                capture(ts),
                capture(apiKey),
                capture(hash),
                capture(offset),
                capture(limit)
            )
        } throws Exception()
        // when
        val result = repository.getCharacters()
        // then
        assertTrue(result is Resource.Failure)
    }
}