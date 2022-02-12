package br.com.viavarejo.desafio.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnail(val path: String, val extension: String) : Parcelable {
    val imageUrl: String
    get() = "${path}.${extension}".replace("http://","https://")
}
