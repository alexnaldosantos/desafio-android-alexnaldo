package br.com.viavarejo.desafio.views.personagem.listagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import br.com.viavarejo.desafio.R
import br.com.viavarejo.desafio.api.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListagemActivity : AppCompatActivity() {
    private val viewModel: ListagemViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem)
        load()
    }

    private fun load() {
        val text = findViewById<TextView>(R.id.loadingListagem)
        viewModel.characters.observe(this){
            when(it){
                is Resource.Success -> text.text = "total de ${it.value.count().toString()}"
                is Resource.Failure -> text.text = "falha ${it.throwable.message}"
                is Resource.Requesting -> text.text = getString(R.string.loading)
            }
        }
        viewModel.getCharacters(0)
    }
}