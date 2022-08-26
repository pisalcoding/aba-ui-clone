package me.pisal.abaclone.model.entity

import java.io.Serializable

data class User(
    val id: String,
    val name: String,
    val dob: String? = null,
    val occupation: String? = null,
    val profileUrl: String? = null,
): Serializable