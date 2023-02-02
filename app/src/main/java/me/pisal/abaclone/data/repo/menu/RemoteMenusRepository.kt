package me.pisal.abaclone.data.repo.menu

import io.realm.kotlin.Realm
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.data.api.MenusApi
import me.pisal.abaclone.data.api.helpers.safeApiCall
import me.pisal.abaclone.data.persistence.persistItems
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
        menuType: MenuType,
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
            result.data?.data?.let { list ->
                persistItems(dispatcher = dispatcher, realm = realm, items = list)
            }
        }
        return result
    }

    override suspend fun homeMenus(useCache: Boolean):
            TResult<ResponseWrapper<List<MbMenuDto>>> {
        val cache = local.homeMenus()
        return if (useCache && cache is TResult.Success && !cache.data?.data.isNullOrEmpty()) {
            cache
        } else {
            fetchAndCache(IO, HOME)
        }
    }

    override suspend fun transferChannelMenus(useCache: Boolean):
            TResult<ResponseWrapper<List<MbMenuDto>>> {
        val cache = local.transferChannelMenus()
        return if (useCache && cache is TResult.Success && !cache.data?.data.isNullOrEmpty()) {
            cache
        } else {
            fetchAndCache(IO, TRANSFER)
        }
    }

    override suspend fun paymentChannelMenus(useCache: Boolean):
            TResult<ResponseWrapper<List<MbMenuDto>>> {
        val cache = local.paymentChannelMenus()
        return if (useCache && cache is TResult.Success && !cache.data?.data.isNullOrEmpty()) {
            cache
        } else {
            fetchAndCache(IO, PAYMENT)
        }
    }

    override suspend fun newAccountMenus(useCache: Boolean):
            TResult<ResponseWrapper<List<MbMenuDto>>> {
        val cache = local.newAccountMenus()
        return if (useCache && cache is TResult.Success && !cache.data?.data.isNullOrEmpty()) {
            cache
        } else {
            fetchAndCache(IO, NEW_ACCOUNT)
        }
    }

    override suspend fun newLoanMenus(useCache: Boolean):
            TResult<ResponseWrapper<List<MbMenuDto>>> {
        val cache = local.newLoanMenus()
        return if (useCache && cache is TResult.Success && !cache.data?.data.isNullOrEmpty()) {
            cache
        } else {
            fetchAndCache(IO, LOANS)
        }
    }
}