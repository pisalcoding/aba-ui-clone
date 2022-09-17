package me.pisal.abaclone.scene.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import me.pisal.abaclone.data.repo.menu.MenusRepository

class PaymentsViewModel(
    private val menusRepository: MenusRepository,
) : ViewModel() {

    fun menus() = liveData {
        emit(menusRepository.paymentChannelMenus())
    }
}