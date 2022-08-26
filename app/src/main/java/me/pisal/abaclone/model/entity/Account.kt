package me.pisal.abaclone.model.entity

import java.io.Serializable
import java.math.BigDecimal

data class Account(
    val id: String,
    val title: String,
    val balance: BigDecimal,
    val primary: Boolean,
    val accountNo: String,
    val currency: String,
    val colorHex: String
) : Serializable