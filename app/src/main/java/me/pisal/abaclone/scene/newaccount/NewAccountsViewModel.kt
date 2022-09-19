package me.pisal.abaclone.scene.newaccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import me.pisal.abaclone.data.repo.menu.MenusRepository

class NewAccountsViewModel(
    private val menusRepository: MenusRepository,
) : ViewModel() {

    fun menus() = liveData {
        emit(menusRepository.newAccountMenus())
    }
}