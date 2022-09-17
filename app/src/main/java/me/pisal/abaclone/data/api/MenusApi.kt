package me.pisal.abaclone.data.api

import androidx.annotation.WorkerThread
import me.pisal.abaclone.model.dto.ResponseWrapper
import me.pisal.abaclone.model.entity.MbMenu
import retrofit2.http.POST

@WorkerThread
interface MenusApi {
    @POST("menus/home-menus")
    suspend fun homeMenus(): ResponseWrapper<List<MbMenu>>

    @POST("menus/payment-channels")
    suspend fun paymentChannelMenus(): ResponseWrapper<List<MbMenu>>

    @POST("menus/transfer-channels")
    suspend fun transferChannelMenus(): ResponseWrapper<List<MbMenu>>
}