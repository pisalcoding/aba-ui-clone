package me.pisal.abaclone.model.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class EncryptedResponse(
    @field:SerializedName("x")
    val iv: String,

    @field:SerializedName("y")
    val salt: String,

    @field:SerializedName("z")
    val cipherText: String,
) : Serializable