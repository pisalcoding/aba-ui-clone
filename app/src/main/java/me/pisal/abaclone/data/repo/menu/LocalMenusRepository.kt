package me.pisal.abaclone.data.repo.menu

import io.realm.kotlin.Realm
import me.pisal.abaclone.common.TResult
import me.pisal.abaclone.data.persistence.safeRealmCall
import me.pisal.abaclone.model.MenuType
import me.pisal.abaclone.model.dao.MbMenuDao
import me.pisal.abaclone.model.dto.MbMenuDto
import me.pisal.abaclone.model.dto.ResponseWrapper

class LocalMenusRepository(
    private val realm: Realm,
) : MenusRepository {

    private suspend fun queryMenu(menuType: MenuType): TResult<ResponseWrapper<List<MbMenuDto>>> {
        return safeRealmCall {
            realm.query(MbMenuDao::class, "type = $0", menuType.name)
                .find()
                .map { it.toDto() }
                .run {
                    ResponseWrapper(this)
                }
        }
    }

    override suspend fun homeMenus(useCache: Boolean):
            TResult<ResponseWrapper<List<MbMenuDto>>> {
        return queryMenu(menuType = MenuType.HOME)
    }

    override suspend fun transferChannelMenus(useCache: Boolean):
            TResult<ResponseWrapper<List<MbMenuDto>>> {
        return queryMenu(menuType = MenuType.TRANSFER)
    }

    override suspend fun paymentChannelMenus(useCache: Boolean):
            TResult<ResponseWrapper<List<MbMenuDto>>> {
        return queryMenu(menuType = MenuType.PAYMENT)
    }

    override suspend fun newAccountMenus(useCache: Boolean):
            TResult<ResponseWrapper<List<MbMenuDto>>> {
        return queryMenu(menuType = MenuType.NEW_ACCOUNT)
    }

    override suspend fun newLoanMenus(useCache: Boolean):
            TResult<ResponseWrapper<List<MbMenuDto>>> {
        return queryMenu(menuType = MenuType.LOANS)
    }
}