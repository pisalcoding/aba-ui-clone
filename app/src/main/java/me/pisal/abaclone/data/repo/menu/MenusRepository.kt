package me.pisal.abaclone.data.repo.menu

import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.model.dto.ResponseWrapper
import me.pisal.abaclone.model.entity.MbMenu

interface MenusRepository {
    suspend fun homeMenus(): TResult<ResponseWrapper<List<MbMenu>>>
    suspend fun transferChannelMenus(): TResult<ResponseWrapper<List<MbMenu>>>
    suspend fun paymentChannelMenus(): TResult<ResponseWrapper<List<MbMenu>>>
}