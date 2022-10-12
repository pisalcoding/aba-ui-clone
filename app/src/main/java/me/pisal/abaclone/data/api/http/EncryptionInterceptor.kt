package me.pisal.abaclone.data.api.http

import android.util.Log
import com.google.gson.Gson
import me.pisal.abaclone.model.dto.EncryptedRequest
import me.pisal.abaclone.model.dto.EncryptedResponse
import me.pisal.hybridcrypto.aes.AESCipher
import me.pisal.hybridcrypto.hybrid.HybridCrypto
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import java.io.IOException

@Throws(IOException::class)
fun RequestBody.bodyToString(): String {
    val buffer = Buffer()
    this.writeTo(buffer)
    return buffer.readUtf8()
}

class EncryptionInterceptor(
    private val aesCipher: AESCipher,
    private val hybridCrypto: HybridCrypto,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        var result: Pair<String, Request>? = null
        while (result == null) {
            result = encryptRequest(request)
        }
        Log.d("Encryption", "Encrypted body: ${result.second.body?.bodyToString()}")
        return decryptResponse(chain.proceed(result.second), result.first)
    }

    private fun decryptResponse(response: Response, responseKey: String): Response {
        val newBuilder = response.newBuilder()
        val contentType = response.header("Content-Type") ?: "application/json"

        // Decrypt request body
        runCatching {
            val parsedBody = Gson().fromJson(response.body?.string(), EncryptedResponse::class.java)
            val decryptionResult = aesCipher.decrypt(
                password = responseKey,
                salt = parsedBody.salt,
                iv = parsedBody.iv,
                base64CipherText = parsedBody.cipherText
            )
            newBuilder.body(decryptionResult.toResponseBody(contentType.toMediaTypeOrNull()))
            return newBuilder.build()
        }.getOrElse {
            it.printStackTrace()
            return response
        }
    }

    /**
     * @return pairOf responseKey to encryptedRequest
     */
    private fun encryptRequest(request: Request): Pair<String, Request>? {
        runCatching {
            // Encrypt the cleaned request body
            val encResult = hybridCrypto.encrypt(request.body?.bodyToString() ?: "")
            val encryptedBody =
                Gson().toJson(EncryptedRequest.fromHybridCryptoResult(encResult.httpParams)) ?: ""
            return encResult.rawResponsePassword to request.rebuildRequest(encryptedBody)
        }.getOrElse {
            it.printStackTrace()
        }
        return null
    }

    /**
     * Build new request object from an existing one.
     * Update only body string and tag
     */
    private fun Request.rebuildRequest(encryptedBody: String): Request {
        val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
        val body: RequestBody = encryptedBody.toRequestBody(mediaType)
        return newBuilder()
            .header("Content-Type", body.contentType().toString())
            .header("Content-Length", body.contentLength().toString())
            .method(method, body)
            .build()
    }
}