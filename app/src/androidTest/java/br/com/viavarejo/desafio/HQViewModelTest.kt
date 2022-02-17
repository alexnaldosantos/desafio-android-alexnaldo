package br.com.viavarejo.desafio

import androidx.test.filters.SmallTest
import br.com.viavarejo.desafio.api.MarvelApi
import br.com.viavarejo.desafio.api.Resource
import br.com.viavarejo.desafio.repositories.MarvelRepositoryHQ
import br.com.viavarejo.desafio.repositories.MarvelRepositoryHQImpl
import br.com.viavarejo.desafio.views.personagem.hq.HQViewModel
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
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
class HQViewModelTest : KoinBaseTest() {

    override fun getKoinModules(): Module = module(override = true) {
        single { mockk<MarvelApi>(relaxed = true) }
        single<MarvelRepositoryHQ> { MarvelRepositoryHQImpl(get()) }
        viewModel { HQViewModel(get(), DummyData.characterDummy()) }
    }

    private val api by inject<MarvelApi>()
    private val viewModel by inject<HQViewModel>()

    @Test
    fun givenRequest_when_GetHQ_then_Observers() = runBlocking {
        // given
        val ts = slot<String>()
        val apiKey = slot<String>()
        val hash = slot<String>()

        coEvery {
            api.getComicsByCharacterId(
                "1", capture(ts), capture(apiKey), capture(hash)
            )
        } returns DummyData.HQResponseDummy()
        val observer = viewModel.hq.mockObserveOnChanged()
        // when
        viewModel.getHQ()
        //then
        verifySequence {
            observer.onChanged(Resource.Requesting)
            observer.onChanged(Resource.Success(DummyData.detailDummy()))
        }
    }
}