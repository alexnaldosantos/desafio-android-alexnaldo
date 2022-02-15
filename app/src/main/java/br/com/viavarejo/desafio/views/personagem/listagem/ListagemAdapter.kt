package br.com.viavarejo.desafio.views.personagem.listagem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.viavarejo.desafio.R
import br.com.viavarejo.desafio.databinding.ItemListagemBinding
import br.com.viavarejo.desafio.models.Character
import com.squareup.picasso.Picasso


class ListagemAdapter(private val delegate: ViewHolder.Delegate):
    RecyclerView.Adapter<ListagemAdapter.ViewHolder>() {

    private var dataSet = listOf<Character>()

    class ViewHolder(view: View, val delegate: Delegate) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.name)
        val imageView: ImageView = view.findViewById(R.id.image)
        internal lateinit var character: Character
        init {
            imageView.setOnClickListener {
                delegate.onItemClick(character, it)
            }
        }

        interface Delegate {
            fun onItemClick(character: Character, view: View)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_listagem, viewGroup, false)
        return ViewHolder(view, delegate)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val character = dataSet[position]
        viewHolder.textView.text = character.name
        viewHolder.character = character
        Picasso.get().load(character.thumbnail.imageUrl).into(viewHolder.imageView);
    }

    override fun getItemCount() = dataSet.size

    fun update(characters: List<Character>) {
        dataSet = characters
        notifyItemInserted(dataSet.size)
    }
}
