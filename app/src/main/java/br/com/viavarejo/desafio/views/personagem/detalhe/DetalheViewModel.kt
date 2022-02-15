package br.com.viavarejo.desafio.views.personagem.detalhe

import android.content.Context
import androidx.lifecycle.ViewModel
import br.com.viavarejo.desafio.models.Character
import br.com.viavarejo.desafio.services.RouterActivityService

open class DetalheViewModel(
    private val router: RouterActivityService,
    public val character: Character
) : ViewModel() {

    fun gotoHQ(context: Context) {
        router.gotoHQ(context, this.character)
    }
}