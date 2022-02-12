package br.com.viavarejo.desafio.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(val id: Int, val name: String,
                     val description: String,
                     val modified: String,
                     val thumbnail: Thumbnail,
                     val resourceURI: String?,
                     val comics: CollectionItem,
                     val series: CollectionItem,
                     val stories: CollectionItem,
                     val events: CollectionItem,
                     val urls: MutableList<ItemUrl>,
                     var page : Int) : Parcelable, SearchObjectItem()  {
    override fun getType(): Int {
        return SearchObjectItem.TYPE_CHARACTER
    }
}