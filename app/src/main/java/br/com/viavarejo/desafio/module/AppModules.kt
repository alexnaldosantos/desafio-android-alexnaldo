package br.com.viavarejo.desafio.module

import br.com.viavarejo.desafio.views.main.MainActivityPresenter
import br.com.viavarejo.desafio.services.*
import org.koin.dsl.module

val appModules = module {
    single<RouterActivityService> { RouterActivityServiceImpl(get()) }
    factory { MainActivityPresenter(get()) }
}