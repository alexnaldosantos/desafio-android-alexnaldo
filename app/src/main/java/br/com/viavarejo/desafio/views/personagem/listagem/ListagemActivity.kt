package br.com.viavarejo.desafio.views.personagem.listagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.viavarejo.desafio.R
import br.com.viavarejo.desafio.api.Resource
import br.com.viavarejo.desafio.models.Character
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListagemActivity : AppCompatActivity() {
    private val viewModel: ListagemViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem)
        load()
    }

    private fun setupRecyclerView(characters: List<Character>) {
        val rv = findViewById<RecyclerView>(R.id.rv_characters)
        rv.adapter = ListagemAdapter(characters)
        progressBarState(View.GONE, "${characters.size.toString()} personagens")
    }

    private fun load() {
        viewModel.characters.observe(this){
            when(it){
                is Resource.Success -> setupRecyclerView(it.value)
                is Resource.Failure -> progressBarState(View.GONE,"${it.throwable.message}")
                is Resource.Requesting -> progressBarState(View.VISIBLE, getString(R.string.loading))
            }
        }
        viewModel.getCharacters(0)
    }

    private fun progressBarState(visibility: Int, message: String = "") {
        val text = findViewById<TextView>(R.id.txt_listagem)
        text.text = message
        val pb = findViewById<ProgressBar>(R.id.pb_characters)
        pb.visibility = visibility
    }
}