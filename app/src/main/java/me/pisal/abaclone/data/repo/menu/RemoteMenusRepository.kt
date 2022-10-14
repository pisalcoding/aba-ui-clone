package me.pisal.abaclone.data.repo.menu

import io.realm.kotlin.Realm
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.data.api.MenusApi
import me.pisal.abaclone.data.api.helpers.safeApiCall
import me.pisal.abaclone.model.MenuType
import me.pisal.abaclone.model.MenuType.*
import me.pisal.abaclone.model.dao.MbMenuDao
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
            result.data?.data?.let { list ->
                withContext(dispatcher) {
                    realm.write {
                        list.forEach { dto ->
                            val dao = query(MbMenuDao::class, "id == $0", dto.id)
                                .first()
                                .find()
                            if (dao != null) {
                                dao.updateFromDto(dto)
                            } else {
                                this.copyToRealm(dto.toDao())
                            }
                        }
                    }
                }
            }
        }
        return result
    }

    override suspend fun homeMenus(): TResult<ResponseWrapper<List<MbMenuDto>>> {
        val cache = local.homeMenus()
        return if (cache is TResult.Success && !cache.data?.data.isNullOrEmpty()) {
            cache
        } else {
            fetchAndCache(IO, HOME)
        }
    }

    override suspend fun transferChannelMenus(): TResult<ResponseWrapper<List<MbMenuDto>>> {
        val cache = local.transferChannelMenus()
        return if (cache is TResult.Success && !cache.data?.data.isNullOrEmpty()) {
            coroutineScope {
                launch {
                    fetchAndCache(IO, TRANSFER)
                }
            }
            cache
        } else {
            fetchAndCache(IO, TRANSFER)
        }
    }

    override suspend fun paymentChannelMenus(): TResult<ResponseWrapper<List<MbMenuDto>>> {
        val cache = local.paymentChannelMenus()
        if (cache is TResult.Success && !cache.data?.data.isNullOrEmpty()) {
            return cache
        } else {
            return fetchAndCache(IO, PAYMENT)
        }
    }

    override suspend fun newAccountMenus(): TResult<ResponseWrapper<List<MbMenuDto>>> {
        val cache = local.newAccountMenus()
        return if (cache is TResult.Success && !cache.data?.data.isNullOrEmpty()) {
            cache
        } else {
            fetchAndCache(IO, NEW_ACCOUNT)
        }
    }

    override suspend fun newLoanMenus(): TResult<ResponseWrapper<List<MbMenuDto>>> {
        val cache = local.newLoanMenus()
        return if (cache is TResult.Success && !cache.data?.data.isNullOrEmpty()) {
            cache
        } else {
            fetchAndCache(IO, LOANS)
        }
    }
}