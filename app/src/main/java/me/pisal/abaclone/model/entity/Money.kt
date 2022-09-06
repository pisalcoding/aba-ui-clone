package me.pisal.abaclone.model.entity

import me.pisal.abaclone.common.Currency
import me.pisal.abaclone.common.Currency.USD
import java.io.Serializable
import java.math.BigDecimal

data class Money(
    val amount: BigDecimal = BigDecimal.ZERO,
    val currency: String,
) : Serializable {

    fun appCurrency(): Currency {
        return Currency[currency] ?: USD
    }
}

