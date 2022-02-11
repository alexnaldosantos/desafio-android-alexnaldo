package br.com.viavarejo.desafio.module

import br.com.viavarejo.desafio.repositories.MarvelRepository
import br.com.viavarejo.desafio.repositories.MarvelRepositoryImpl
import br.com.viavarejo.desafio.views.main.MainActivityPresenter
import br.com.viavarejo.desafio.services.*
import br.com.viavarejo.desafio.utils.NetworkUtils
import br.com.viavarejo.desafio.views.personagem.listagem.ListagemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    single<RouterActivityService> { RouterActivityServiceImpl(get()) }
    factory { MainActivityPresenter(get()) }
    single<MarvelRepository> { MarvelRepositoryImpl(get()) }
    single { NetworkUtils.createApiService() }
    viewModel { ListagemViewModel(get()) }
}