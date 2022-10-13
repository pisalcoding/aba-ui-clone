package me.pisal.abaclone.data.repo.menu

import io.realm.kotlin.Realm
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.data.api.MenusApi
import me.pisal.abaclone.data.api.helpers.safeApiCall
import me.pisal.abaclone.model.MenuType
import me.pisal.abaclone.model.MenuType.*
import me.pisal.abaclone.model.dto.MbMenuDto
import me.pisal.abaclone.model.dto.ResponseWrapper

class RemoteMenusRepository(
    private val api: MenusApi,
    private val local: MenusRepository,
    private val realm: Realm,
) : MenusRepository {

    private suspend fun fetchAndCache(
        dispatcher: CoroutineDispatcher = IO,
        menuType: MenuType
    ): TResult<ResponseWrapper<List<MbMenuDto>>> {
        val result = safeApiCall {
            when (menuType) {
                HOME -> {
                    api.homeMenus()
                }
                PAYMENT -> {
                    api.paymentChannelMenus()
                }
                TRANSFER -> {
                    api.transferChannelMenus()
                }
                NEW_ACCOUNT -> {
                    api.newAccountMenus()
                }
                LOANS -> {
                    api.newLoanMenus()
                }
            }
        }
        if (result is TResult.Success) {
            result.data?.data?.run {
                withContext(IO) {

                }
            }
        }
        return result
    }

    override suspend fun homeMenus(): TResult<ResponseWrapper<List<MbMenuDto>>> {
        val cache = local.transferChannelMenus()
        return if (cache is TResult.Success && !cache.data?.data.isNullOrEmpty()) {
            withContext(IO) { async { fetchAndCache(IO, HOME) } }.start()
            cache
        } else {
            fetchAndCache(IO, HOME)
        }
    }

    override suspend fun transferChannelMenus(): TResult<ResponseWrapper<List<MbMenuDto>>> {
        val cache = local.transferChannelMenus()
        return if (cache is TResult.Success && !cache.data?.data.isNullOrEmpty()) {
            withContext(IO) { async { fetchAndCache(IO, TRANSFER) } }.start()
            cache
        } else {
            fetchAndCache(IO, TRANSFER)
        }
    }

    override suspend fun paymentChannelMenus(): TResult<ResponseWrapper<List<MbMenuDto>>> {
        val cache = local.transferChannelMenus()
        return if (cache is TResult.Success && !cache.data?.data.isNullOrEmpty()) {
            withContext(IO) { async { fetchAndCache(IO, PAYMENT) } }.start()
            cache
        } else {
            fetchAndCache(IO, PAYMENT)
        }
    }

    override suspend fun newAccountMenus(): TResult<ResponseWrapper<List<MbMenuDto>>> {
        val cache = local.transferChannelMenus()
        return if (cache is TResult.Success && !cache.data?.data.isNullOrEmpty()) {
            withContext(IO) { async { fetchAndCache(IO, NEW_ACCOUNT) } }.start()
            cache
        } else {
            fetchAndCache(IO, NEW_ACCOUNT)
        }
    }

    override suspend fun newLoanMenus(): TResult<ResponseWrapper<List<MbMenuDto>>> {
        val cache = local.transferChannelMenus()
        return if (cache is TResult.Success && !cache.data?.data.isNullOrEmpty()) {
            withContext(IO) { async { fetchAndCache(IO, LOANS) } }.start()
            cache
        } else {
            fetchAndCache(IO, LOANS)
        }
    }
}