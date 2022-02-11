package br.com.viavarejo.desafio.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

enum class WEEK {
    lastWeek,
    thisWeek,
    nextWeek,
    none
}

@Parcelize
data class CharacterDetail(val id: Int,
                           val digitalId: String?,
                           val title: String?,
                           val issueNumber: String?,
                           val variantDescription: String?,
                           val description: String?,
                           val modified: String?,
                           val isbn: String?,
                           val upc: String?,
                           val diamondCode: String?,
                           val ean: String?,
                           val issn: String?,
                           val format: String?,
                           val pageCount: Int,
                           val series: Item?,
                           val dates: MutableList<Date>?,
                           val prices: MutableList<Price>?,
                           val creators: CollectionItem?,
                           val characters: CollectionItem?,
                           val comics: CollectionItem ?,
                           val resourceURI: String,
                           val startYear : Int,
                           val endYear : Int,
                           val thumbnail: Thumbnail?,
                           val images: MutableList<Thumbnail>?,
                           val urls: MutableList<ItemUrl>?,
                           var page : Int,
                           var fullName : String?,
                           var week: WEEK = WEEK.none) : Parcelable, SearchObjectItem() {
    override fun getType(): Int {
        if(this.endYear != 0 && this.startYear != 0) {
            return SearchObjectItem.TYPE_SERIES
        }

        if(fullName != null) {
            return SearchObjectItem.TYPE_PERSON
        }

        return SearchObjectItem.TYPE_COMIC
    }
}