package me.pisal.abaclone.model.entity

import java.io.Serializable

data class Card(
    val id: String,
    val cardNumber: String,
    val expiryYear: String,
    val expiryMonth: String,
    val cvv: String,
    val cardName: String,
    val holderName: String,
    val debit: Boolean,
    val virtual: Boolean,
    val frozen: Boolean,
    val linkedAccount: Account,
    val settingMenus: List<CardSettingMenu>,
    val networkBrand: CardNetworkBrand,
    val cardOption: String
): Serializable

data class CardNetworkBrand(
    val brandName: String,
    val brandLogoUri: String,
    val backgroundImageUri: String,
): Serializable

data class CardSettingMenu(
    val order: Int,
    val title: String,
    val iconUri: String,
): Serializable