package br.com.viavarejo.desafio.api

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val throwable: Throwable
    ) : Resource<Nothing>()
    object Requesting : Resource<Nothing>()
}