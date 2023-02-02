package me.pisal.abaclone.data.repo.menu

import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.model.dto.MbMenuDto
import me.pisal.abaclone.model.dto.ResponseWrapper

interface MenusRepository {
    suspend fun homeMenus(useCache: Boolean = true): TResult<ResponseWrapper<List<MbMenuDto>>>
    suspend fun transferChannelMenus(useCache: Boolean = true): TResult<ResponseWrapper<List<MbMenuDto>>>
    suspend fun paymentChannelMenus(useCache: Boolean = true): TResult<ResponseWrapper<List<MbMenuDto>>>
    suspend fun newAccountMenus(useCache: Boolean = true): TResult<ResponseWrapper<List<MbMenuDto>>>
    suspend fun newLoanMenus(useCache: Boolean = true): TResult<ResponseWrapper<List<MbMenuDto>>>
}