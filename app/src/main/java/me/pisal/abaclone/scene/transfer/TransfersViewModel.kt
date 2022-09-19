package me.pisal.abaclone.scene.transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import me.pisal.abaclone.data.repo.menu.MenusRepository

class TransfersViewModel(
    private val menusRepository: MenusRepository,
) : ViewModel() {

    fun menus() = liveData {
        emit(menusRepository.transferChannelMenus())
    }
}