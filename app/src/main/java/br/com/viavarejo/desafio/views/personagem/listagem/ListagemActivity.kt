package br.com.viavarejo.desafio.views.personagem.listagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.viavarejo.desafio.R
import br.com.viavarejo.desafio.api.Resource
import br.com.viavarejo.desafio.models.Character
import br.com.viavarejo.desafio.utils.Utils
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListagemActivity : AppCompatActivity() {
    private val viewModel: ListagemViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listagem)

        setup()
    }

    private fun setup() {
        setupRecyclerView()
        viewModel.characters.observe(this) {
            when (it) {
                is Resource.Success -> updateRecycleView(it.value)
                is Resource.Failure -> setProgressBarState(View.GONE, "${it.throwable.message}")
                is Resource.Requesting -> setProgressBarState(
                    View.VISIBLE,
                    getString(R.string.loading)
                )
            }
        }
        getCharacters()
    }

    private fun setupRecyclerView() {
        val rv = findViewById<RecyclerView>(R.id.rv_characters)
        rv.adapter = ListagemAdapter()
        rv.addOnScrollListener(
            Utils.InfiniteScrollListener(rv.layoutManager as LinearLayoutManager) {
                getCharacters()
            })
    }

    private fun getCharacters() {
        viewModel.getCharacters()
    }

    private fun updateRecycleView(characters: List<Character>) {
        val rv = findViewById<RecyclerView>(R.id.rv_characters)
        (rv.adapter as ListagemAdapter).update(characters)
        setProgressBarState(View.GONE)
    }

    private fun setProgressBarState(visibility: Int, message: String = "") {
        val text = findViewById<TextView>(R.id.txt_listagem)
        text.text = message
        val pb = findViewById<ProgressBar>(R.id.pb_characters)
        pb.visibility = visibility
    }
}