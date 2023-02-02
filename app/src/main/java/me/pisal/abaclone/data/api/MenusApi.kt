package me.pisal.abaclone.data.api

import androidx.annotation.WorkerThread
import me.pisal.abaclone.model.dto.ResponseWrapper
import me.pisal.abaclone.model.dto.MbMenuDto
import retrofit2.http.POST

@WorkerThread
interface MenusApi {
    @POST("menus/home-menus")
    suspend fun homeMenus(): ResponseWrapper<List<MbMenuDto>>

    @POST("menus/payment-channels")
    suspend fun paymentChannelMenus(): ResponseWrapper<List<MbMenuDto>>

    @POST("menus/transfer-channels")
    suspend fun transferChannelMenus(): ResponseWrapper<List<MbMenuDto>>

    @POST("menus/new-accounts")
    suspend fun newAccountMenus(): ResponseWrapper<List<MbMenuDto>>

    @POST("menus/loans")
    suspend fun newLoanMenus(): ResponseWrapper<List<MbMenuDto>>
}