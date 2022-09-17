package me.pisal.abaclone.data.repo.account

import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.model.dto.ResponseWrapper
import me.pisal.abaclone.model.entity.Account

interface AccountsRepository {

    suspend fun list(): TResult<ResponseWrapper<List<Account>>>
}