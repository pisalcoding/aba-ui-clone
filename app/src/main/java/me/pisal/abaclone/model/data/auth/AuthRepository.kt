package me.pisal.abaclone.model.data.auth

import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.model.dto.AuthResponse
import me.pisal.abaclone.model.dto.ResponseWrapper

interface AuthRepository {

    suspend fun authenticate(pin: String): TResult<ResponseWrapper<AuthResponse>>
}