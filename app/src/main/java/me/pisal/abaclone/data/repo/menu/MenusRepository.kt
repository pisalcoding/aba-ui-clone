package me.pisal.abaclone.data.repo.menu

import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.model.dto.ResponseWrapper
import me.pisal.abaclone.model.dto.MbMenuDto

interface MenusRepository {
    suspend fun homeMenus(): TResult<ResponseWrapper<List<MbMenuDto>>>
    suspend fun transferChannelMenus(): TResult<ResponseWrapper<List<MbMenuDto>>>
    suspend fun paymentChannelMenus(): TResult<ResponseWrapper<List<MbMenuDto>>>
    suspend fun newAccountMenus(): TResult<ResponseWrapper<List<MbMenuDto>>>
    suspend fun newLoanMenus(): TResult<ResponseWrapper<List<MbMenuDto>>>
}