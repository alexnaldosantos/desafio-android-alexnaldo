package br.com.viavarejo.desafio.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemUrl(val type: String, val url: String) : Parcelable
