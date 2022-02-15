package br.com.viavarejo.desafio.module

import br.com.viavarejo.desafio.repositories.*
import br.com.viavarejo.desafio.views.main.MainActivityPresenter
import br.com.viavarejo.desafio.services.*
import br.com.viavarejo.desafio.utils.NetworkUtils
import br.com.viavarejo.desafio.views.personagem.detalhe.DetalheViewModel
import br.com.viavarejo.desafio.views.personagem.hq.HQViewModel
import br.com.viavarejo.desafio.views.personagem.listagem.ListagemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single<RouterActivityService> { RouterActivityServiceImpl(get()) }
    factory { MainActivityPresenter(get()) }
    single<MarvelRepositoryCharacter> { MarvelRepositoryCharacterImpl(get()) }
    single<MarvelRepositoryHQ> { MarvelRepositoryHQImpl(get()) }
    single { NetworkUtils.createApiService() }
    viewModel { ListagemViewModel(get(), get()) }
    viewModel { params ->
        DetalheViewModel(
            get(),
            params[0]
        )
    }
    viewModel { params ->
        HQViewModel(
            get(),
            params[0]
        )
    }
}