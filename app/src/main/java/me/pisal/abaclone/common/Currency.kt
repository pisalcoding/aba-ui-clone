package me.pisal.abaclone.common

import java.io.Serializable

enum class Currency(
    val fraction: Int,
    val symbol: String,
    val isoCode: String,
) : Serializable {
    USD(2, "$", "840"),
    KHR(2, "៛", "116");

    companion object {
        operator fun get(name: String): Currency? {
            return when (name) {
                USD.name -> { USD }
                KHR.name -> { KHR }
                else -> { null }
            }
        }
    }
}