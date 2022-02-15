package br.com.viavarejo.desafio.services

import android.content.Context
import br.com.viavarejo.desafio.models.Character

interface RouterActivityService {
    fun gotoListagem(context: Context)
    fun gotoListagemDetail(context: Context, character: Character)
    fun gotoHQ(context: Context, character: Character)
}