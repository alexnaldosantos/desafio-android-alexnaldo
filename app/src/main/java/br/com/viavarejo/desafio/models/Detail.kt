package br.com.viavarejo.desafio.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Detail(
    val id: Int,
    val title: String?,
    val description: String?,
    val prices: List<Price>?,
    val thumbnail: Thumbnail?,
    var moreExpensive: Price?
) : Parcelable {
}