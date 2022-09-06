package me.pisal.abaclone.common.extension

import me.pisal.abaclone.common.Currency
import me.pisal.abaclone.model.entity.Money
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

/**
 * Convert 11234.5 => "11,234.50"
 */
fun BigDecimal.formatted(locale: Locale = Locale.US): String {
    val symbol = DecimalFormatSymbols.getInstance(locale)
    val numberFormatter = DecimalFormat("#,###,##0.00", symbol)
    numberFormatter.isDecimalSeparatorAlwaysShown = true
    numberFormatter.maximumFractionDigits = 2
    numberFormatter.minimumFractionDigits = 2
    return numberFormatter.format(this)
}

/**
 * Convert {11234.5, USD} => "11,234.50"
 */
fun Money.formatted(locale: Locale = Locale.US): String {
    val symbol = DecimalFormatSymbols.getInstance(locale)
    val numberFormatter = DecimalFormat("#,###,##0.00", symbol)
    numberFormatter.isDecimalSeparatorAlwaysShown = true
    numberFormatter.maximumFractionDigits = appCurrency().fraction
    numberFormatter.minimumFractionDigits = appCurrency().fraction
    return numberFormatter.format(this.amount)
}

/**
 * Convert {11234.5, USD} => "USD 11,234.50"
 */
fun Money.formattedWithName(locale: Locale = Locale.US): String {
    return currency + " " + formatted(locale)
}

fun Money.exchange(exchangeRate: BigDecimal): BigDecimal {
    return BigDecimal.ZERO
}

fun BigDecimal.toMoney(currency: Currency): Money {
    return Money(
        amount = this,
        currency = currency.name
    )
}
