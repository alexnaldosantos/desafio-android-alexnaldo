package br.com.viavarejo.desafio

import androidx.test.filters.SmallTest
import br.com.viavarejo.desafio.api.MarvelApi
import br.com.viavarejo.desafio.api.Resource
import br.com.viavarejo.desafio.repositories.MarvelRepositoryCharacter
import br.com.viavarejo.desafio.repositories.MarvelRepositoryCharacterImpl
import br.com.viavarejo.desafio.views.personagem.listagem.ListagemViewModel
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verifySequence
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.inject
import org.koin.core.module.Module
import org.koin.dsl.module

@SmallTest
@RunWith(JUnit4::class)
class ListagemViewModelTest : KoinBaseTest() {

    override fun getKoinModules(): Module = module(override = true) {
        single { mockk<MarvelApi>(relaxed = true) }
        single<MarvelRepositoryCharacter> { MarvelRepositoryCharacterImpl(get()) }
        viewModel { ListagemViewModel(get(), get()) }
    }

    private val api by inject<MarvelApi>()
    private val viewModel by inject<ListagemViewModel>()

    @Test
    fun givenRequest_when_GetCharacters_then_Observers() = runBlocking {
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
        } returns DummyData.characterResponseDummy()
        val observer = viewModel.characters.mockObserveOnChanged()
        // when
        viewModel.getCharacters()
        //then
        verifySequence {
            observer.onChanged( Resource.Requesting )
            observer.onChanged( Resource.Success( listOf( DummyData.characterDummy() ) ) )
        }
    }
}