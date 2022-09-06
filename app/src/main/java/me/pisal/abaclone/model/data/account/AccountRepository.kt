package me.pisal.abaclone.model.data.account

import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.model.dto.ResponseWrapper
import me.pisal.abaclone.model.entity.Account

interface AccountRepository {

    suspend fun list(): TResult<ResponseWrapper<List<Account>>>
}