package br.com.viavarejo.desafio.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Price (val type : String?, val price: Double) : Parcelable
