package br.com.viavarejo.desafio.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(val resourceURI: String, val name: String, val type: String?, val role : String? , var image : String?) :
    Parcelable