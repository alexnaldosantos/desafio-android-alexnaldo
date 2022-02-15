package br.com.viavarejo.desafio.repositories

import br.com.viavarejo.desafio.BuildConfig
import br.com.viavarejo.desafio.api.MarvelApi
import br.com.viavarejo.desafio.utils.Utils
import java.util.*

open abstract class MarvelRepositoryBase(protected val api: MarvelApi) {
    protected val MARVEL_API_KEY : String
        get() = BuildConfig.MARVEL_API_KEY
    protected val MARVEL_PRIVATE_KEY : String
        get() = BuildConfig.MARVEL_PRIVATE_KEY
    protected val timestamp: Long = Date().time
    protected fun hash() = Utils.md5(timestamp.toString() + MARVEL_PRIVATE_KEY + MARVEL_API_KEY)

}