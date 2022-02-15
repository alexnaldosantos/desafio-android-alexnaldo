package br.com.viavarejo.desafio.views.personagem.hq

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import br.com.viavarejo.desafio.R
import br.com.viavarejo.desafio.api.Resource
import br.com.viavarejo.desafio.models.Detail
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.ParametersHolder

class HQActivity : AppCompatActivity() {

    companion object Parameters {
        const val PARAM_CHARACTER = "character"
    }

    private val viewModel: HQViewModel by viewModel(parameters = { getParameters() })

    private val text: TextView
        get() = findViewById(R.id.name)
    private val image: ImageView
        get() = findViewById(R.id.image)
    private val description: TextView
        get() = findViewById(R.id.description)
    private val price: TextView
        get() = findViewById(R.id.price)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hq)
        setup()
    }

    private fun setup() {
        viewModel.hq.observe(this) {
            when (it) {
                is Resource.Success -> render(it.value)
                is Resource.Failure -> setProgressBarState(View.GONE, "${it.throwable.message}")
                is Resource.Requesting -> setProgressBarState(
                    View.VISIBLE,
                    getString(R.string.loading)
                )
            }
        }
        getHQ()
    }

    private fun getHQ() {
        viewModel.getHQ()
    }

    private fun setProgressBarState(visibility: Int, message: String = "") {
        val pb = findViewById<ProgressBar>(R.id.pb_hq)
        pb.visibility = visibility
    }

    private fun render(detail: Detail) {
        text.text = detail.title
        description.text = detail.description
        price.text = detail.moreExpensive?.price.toString()
        Picasso.get().load(detail.thumbnail?.imageUrl).into(image)
        setProgressBarState(View.GONE)
    }

    private fun getParameters(): ParametersHolder {
        return ParametersHolder(mutableListOf(intent.getParcelableExtra(HQActivity.PARAM_CHARACTER)))
    }
}