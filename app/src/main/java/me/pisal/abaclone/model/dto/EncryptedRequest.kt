package me.pisal.abaclone.model.dto

import com.google.gson.annotations.SerializedName
import me.pisal.hybridcrypto.hybrid.HttpFriendlyResult
import java.io.Serializable

data class EncryptedRequest(
    @field:SerializedName("a")
    val requestPassword: String,

    @field:SerializedName("b")
    val responsePassword: String,

    @field:SerializedName("c")
    val signature: String,

    @field:SerializedName("x")
    val iv: String,

    @field:SerializedName("y")
    val salt: String,

    @field:SerializedName("z")
    val cipherText: String,
) : Serializable {

    companion object {
        fun fromHybridCryptoResult(httpFriendlyResult: HttpFriendlyResult): EncryptedRequest {
            return EncryptedRequest(
                requestPassword = httpFriendlyResult.requestPassword,
                responsePassword = httpFriendlyResult.responsePassword,
                signature = httpFriendlyResult.signature,
                iv = httpFriendlyResult.iv,
                salt = httpFriendlyResult.salt,
                cipherText = httpFriendlyResult.encryptedData
            )
        }
    }
}