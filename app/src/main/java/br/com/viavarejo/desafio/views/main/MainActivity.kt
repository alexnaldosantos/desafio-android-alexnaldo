package br.com.viavarejo.desafio.views.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import br.com.viavarejo.desafio.R
import org.koin.android.ext.android.inject
import java.util.*

class MainActivity : AppCompatActivity() {
    private val presenter: MainActivityPresenter by inject<MainActivityPresenter>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun play(view: View){
        presenter.play(this)
    }
}