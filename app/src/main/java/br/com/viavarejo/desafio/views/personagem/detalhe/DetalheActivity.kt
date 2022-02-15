package br.com.viavarejo.desafio.views.personagem.detalhe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import br.com.viavarejo.desafio.R
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.ParametersHolder


class DetalheActivity : AppCompatActivity() {

    companion object Parameters {
        const val PARAM_CHARACTER = "character"
    }

    private val viewModel: DetalheViewModel by viewModel(parameters = { getParameters() })

    private val text: TextView
    get() = findViewById(R.id.name)
    private val image: ImageView
    get() = findViewById(R.id.image)
    private val description: TextView
    get() = findViewById(R.id.description)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe)

        render()
    }

    private fun render() {
        val character = viewModel.character
        text.text = character!!.name
        description.text = character.description
        Picasso.get().load(character.thumbnail.imageUrl).into(image)
    }

    private fun getParameters(): ParametersHolder {
        return ParametersHolder(mutableListOf(intent.getParcelableExtra(DetalheActivity.PARAM_CHARACTER)))
    }

    fun gotoHQ(view: View){
        viewModel.gotoHQ(this)
    }
}