package me.pisal.abaclone.common


sealed class TResult <out R> {
    data class Success<out T>(val data: T? = null): TResult<T>()
    data class Failure(val message: String? = null, val code: String? = null): TResult<Nothing>()
}