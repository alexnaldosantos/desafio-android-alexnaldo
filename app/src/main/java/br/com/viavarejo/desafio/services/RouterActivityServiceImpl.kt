package br.com.viavarejo.desafio.services

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import br.com.viavarejo.desafio.views.personagem.listagem.ListagemActivity
import br.com.viavarejo.desafio.models.Character
import br.com.viavarejo.desafio.views.personagem.detalhe.DetalheActivity
import br.com.viavarejo.desafio.views.personagem.hq.HQActivity

class RouterActivityServiceImpl(private val applicationContext: Context) : RouterActivityService {

    override fun gotoListagem(context: Context){
        val intent = Intent(context, ListagemActivity::class.java)
        context.startActivity(intent)
    }

    override fun gotoListagemDetail(context: Context, character: Character) {
        val intent = Intent(context, DetalheActivity::class.java)
        .putExtra(DetalheActivity.PARAM_CHARACTER, character)
        context.startActivity(intent)
    }

    override fun gotoHQ(context: Context, character: Character) {
        val intent = Intent(context, HQActivity::class.java)
            .putExtra(HQActivity.PARAM_CHARACTER, character)
        context.startActivity(intent)
    }
}