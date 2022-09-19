package me.pisal.abaclone.scene.loan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import me.pisal.abaclone.data.repo.menu.MenusRepository

class NewLoansViewModel(
    private val menusRepository: MenusRepository,
) : ViewModel() {

    fun menus() = liveData {
        emit(menusRepository.newLoanMenus())
    }
}