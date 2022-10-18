package me.pisal.abaclone.scene.transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import me.pisal.abaclone.data.repo.menu.MenusRepository

class TransfersViewModel(
    private val menusRepository: MenusRepository,
) : ViewModel() {

    fun menus(useCache: Boolean) = liveData {
        /**
         * Return the cached data first
         */
        emit(menusRepository.transferChannelMenus(useCache))

        /**
         * Then fetch in the background to update cache
         */
        menusRepository.transferChannelMenus(false)
    }
}