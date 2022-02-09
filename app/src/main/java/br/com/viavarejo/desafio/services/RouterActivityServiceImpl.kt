package br.com.viavarejo.desafio.services

import android.content.Context
import android.content.Intent
import br.com.viavarejo.desafio.CustomApp
import br.com.viavarejo.desafio.views.personagem.listagem.ListagemActivity

class RouterActivityServiceImpl(private val applicationContext: Context) : RouterActivityService {

    override fun gotoListagem(context: Context){
        val intent = Intent(context, ListagemActivity::class.java)
        context.startActivity(intent)
    }
}