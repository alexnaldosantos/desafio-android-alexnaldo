package br.com.viavarejo.desafio

import br.com.viavarejo.desafio.api.MarvelApi
import br.com.viavarejo.desafio.api.Resource
import br.com.viavarejo.desafio.repositories.MarvelRepositoryHQ
import br.com.viavarejo.desafio.repositories.MarvelRepositoryHQImpl
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.component.inject
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.test.assertTrue

class RepositoryHQTest : KoinBaseTest() {

    override fun getKoinModules(): Module = module(override = true) {
        single { mockk<MarvelApi>(relaxed = true) }
        single<MarvelRepositoryHQ> { MarvelRepositoryHQImpl(get()) }
    }

    private val api by inject<MarvelApi>()
    private val repository by inject<MarvelRepositoryHQ>()

    @Test
    fun givenResourceHQ_whenGetHQ_thenReturnsSuccessfully() = runBlocking {
        // given
        val ts = slot<String>()
        val apiKey = slot<String>()
        val hash = slot<String>()

        coEvery {
            api.getComicsByCharacterId(
                "1", capture(ts), capture(apiKey), capture(hash)
            )
        } returns DummyData.HQResponseDummy()
        // when
        val result = repository.getHQ("1")
        // then
        assertTrue(result is Resource.Success)
    }

    @Test
    fun givenResourceHQ_whenGetHQFail_thenReturnsThrowable() = runBlocking {
        // given
        val ts = slot<String>()
        val apiKey = slot<String>()
        val hash = slot<String>()

        coEvery {
            api.getComicsByCharacterId(
                "1", capture(ts), capture(apiKey), capture(hash)
            )
        } throws Exception()
        // when
        val result = repository.getHQ("1")
        // then
        assertTrue(result is Resource.Failure)
    }
}