package me.pisal.abaclone.model.entity

import android.content.Context
import android.graphics.Color
import java.io.Serializable
import java.math.BigDecimal

data class Account(
    val id: String,
    val title: String,
    val category: String,
    val balance: BigDecimal,
    val primary: Boolean,
    val accountNo: String,
    val currency: String,
    val colorHex: String,
) : Serializable {

    fun formattedBalance(): String {
        return "1,999.12"
    }

    fun color(context: Context): Int {
        return Color.parseColor(colorHex)
    }
}