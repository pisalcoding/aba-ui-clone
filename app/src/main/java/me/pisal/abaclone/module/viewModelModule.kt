package me.pisal.abaclone.module

import me.pisal.abaclone.scene.payment.PaymentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { PaymentsViewModel(get()) }
}