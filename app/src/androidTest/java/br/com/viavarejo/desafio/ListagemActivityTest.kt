package br.com.viavarejo.desafio

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import br.com.viavarejo.desafio.api.MarvelApi
import br.com.viavarejo.desafio.repositories.MarvelRepositoryCharacter
import br.com.viavarejo.desafio.repositories.MarvelRepositoryCharacterImpl
import br.com.viavarejo.desafio.repositories.MarvelRepositoryHQ
import br.com.viavarejo.desafio.repositories.MarvelRepositoryHQImpl
import br.com.viavarejo.desafio.services.RouterActivityService
import br.com.viavarejo.desafio.services.RouterActivityServiceImpl
import br.com.viavarejo.desafio.utils.NetworkUtils
import br.com.viavarejo.desafio.views.main.MainActivityPresenter
import br.com.viavarejo.desafio.views.personagem.listagem.ListagemActivity
import br.com.viavarejo.desafio.views.personagem.listagem.ListagemViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.inject
import org.koin.core.module.Module
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
@LargeTest
class ListagemActivityTest: KoinBaseTest() {

    @get:Rule
    var activityRule: ActivityTestRule<ListagemActivity>
            = ActivityTestRule(ListagemActivity::class.java)

    private val api by inject<MarvelApi>()

    @Test
    fun givenRecyclerView_whenStart_thenShow() {
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

        //then
    }

    override fun getKoinModules(): Module = module(override = true) {
        single<RouterActivityService> { mockk<RouterActivityServiceImpl>(relaxed = true) }
        single { mockk<MarvelApi>(relaxed = true) }
        single<MarvelRepositoryCharacter> { MarvelRepositoryCharacterImpl(get()) }
        viewModel { ListagemViewModel(get(), get()) }
    }
}