package br.com.viavarejo.desafio

import android.app.Application
import br.com.viavarejo.desafio.module.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CustomApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@CustomApp)
            modules(listOf(appModules))
        }
    }
}