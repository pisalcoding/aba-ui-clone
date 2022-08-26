package me.pisal.abaclone.model.data.auth

import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.model.dto.AuthResponse
import me.pisal.abaclone.model.dto.ResponseWrapper
import me.pisal.abaclone.model.entity.User

class AuthRepositoryImpl : AuthRepository {

    override suspend fun authenticate(pin: String): TResult<ResponseWrapper<AuthResponse>> {
        return if (pin == "1234") {
            TResult.Success(
                ResponseWrapper(
                    data = AuthResponse(
                        user = User(
                            id = "123456",
                            name = "John Doe"
                        ),
                        token = ""
                    )
                )
            )
        } else {
            TResult.Failure("Invalid PIN!")
        }
    }
}