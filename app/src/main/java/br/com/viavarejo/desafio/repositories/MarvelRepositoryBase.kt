package br.com.viavarejo.desafio.repositories

import br.com.viavarejo.desafio.api.MarvelApi
import br.com.viavarejo.desafio.utils.Utils
import java.util.Date

// don't do it for real app !!!
const val MARVEL_PRIVATE_KEY = "b9e9cfcc343f1d38066f795f10bf2a313dab7452"
const val MARVEL_API_KEY = "8105ca2fc25bd49562b17cb07b576415"

open abstract class MarvelRepositoryBase(protected val api: MarvelApi) {
    protected val timestamp: Long = Date().time
    protected fun hash() = Utils.md5(timestamp.toString() + MARVEL_PRIVATE_KEY + MARVEL_API_KEY)
}