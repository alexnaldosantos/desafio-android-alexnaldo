package br.com.viavarejo.desafio.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CollectionItem(val available: Int, val collectionURI: String?,
                          val items: MutableList<Item>?, val returned: Int) : Parcelable