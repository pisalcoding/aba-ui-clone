package me.pisal.abaclone.data.repo.menu

import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.data.api.MenusApi
import me.pisal.abaclone.data.api.helpers.safeApiCall
import me.pisal.abaclone.model.dto.ResponseWrapper
import me.pisal.abaclone.model.entity.MbMenu

class MenusRepositoryImpl(
    private val api: MenusApi,
) : MenusRepository {

    override suspend fun homeMenus(): TResult<ResponseWrapper<List<MbMenu>>> {
        return safeApiCall {
            api.homeMenus()
        }
    }

    override suspend fun transferChannelMenus(): TResult<ResponseWrapper<List<MbMenu>>> {
        return safeApiCall {
            api.transferChannelMenus()
        }
    }

    override suspend fun paymentChannelMenus(): TResult<ResponseWrapper<List<MbMenu>>> {
        return safeApiCall {
            api.paymentChannelMenus()
        }
    }
}