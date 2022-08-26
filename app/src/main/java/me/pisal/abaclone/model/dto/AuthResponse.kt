package me.pisal.abaclone.model.dto

import me.pisal.abaclone.model.entity.User
import java.io.Serializable

data class AuthResponse(
    val user: User,
    val token: String
) : Serializable