package me.pisal.abaclone.scene.newaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import me.pisal.abaclone.data.repo.menu.MenusRepository

class NewAccountsViewModel(
    private val menusRepository: MenusRepository,
) : ViewModel() {

    fun menus(useCache: Boolean) = liveData {
        /**
         * Return the cached data first
         */
        emit(menusRepository.newAccountMenus(useCache))

        /**
         * Then fetch in the background to update cache
         */
        menusRepository.newAccountMenus(false)
    }
}