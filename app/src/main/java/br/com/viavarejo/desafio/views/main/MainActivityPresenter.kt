package br.com.viavarejo.desafio.views.main

import android.content.Context
import br.com.viavarejo.desafio.services.RouterActivityService

public open class MainActivityPresenter(private val router : RouterActivityService) {
    fun play(context: Context) {
        router.gotoListagem(context)
    }
}