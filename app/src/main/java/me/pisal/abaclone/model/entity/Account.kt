package me.pisal.abaclone.model.entity

import android.graphics.Color
import me.pisal.abaclone.common.Currency
import me.pisal.abaclone.common.extension.formatted
import me.pisal.abaclone.common.extension.toMoney
import me.pisal.abaclone.model.dto.Money
import java.io.Serializable

data class Account(
    val id: String,
    val title: String,
    val category: String,
    val primary: Boolean,
    val accountNo: String,
    val colorHex: String,
    val money: Money,
) : Serializable {

    fun formattedBalance(): String {
        return money.formatted()
    }

    fun color(): Int {
        return Color.parseColor(colorHex)
    }
}

/**
 * Returns something like: "$ 12,124.00" or "៛ 12,124.00"
 *
 * @param withSymbol: whether to show "$|៛" or not. Default true.
 */
fun List<Account>.sumBalanceByCurrency(
    currency: Currency,
    withSymbol: Boolean = true
): String {
    return this.filter { it.money.currency == currency.name }
        .sumOf { it.money.amount }
        .toMoney(currency)
        .formatted()
        .run {
            if (withSymbol) {
                "${currency.symbol} $this"
            } else {
                this
            }
        }
}