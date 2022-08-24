package me.pisal.abaclone.model.entity

import androidx.annotation.DrawableRes
import java.io.Serializable

data class MenuModel(
    val id: String,
    val title: String,
    @DrawableRes val iconResId: Int? = null,
    val subtitle: String? = null,
    val status: Boolean = true,
    val iconUrl: String? = null,
    val badges: List<MenuBadge>? = null
): Serializable {}

data class MenuBadge(
    @DrawableRes val iconResID: Int? = null,
    val iconUrl: String? = null,
    val status: Boolean
): Serializable