package me.pisal.abaclone.module

import me.pisal.abaclone.ABAApplication
import me.pisal.abaclone.scene.MainViewModel
import me.pisal.abaclone.scene.loan.NewLoansViewModel
import me.pisal.abaclone.scene.newaccount.NewAccountsViewModel
import me.pisal.abaclone.scene.payment.PaymentsViewModel
import me.pisal.abaclone.scene.scanqr.ScanQrViewModel
import me.pisal.abaclone.scene.transfer.TransfersViewModel
import me.pisal.abaclone.thirdparty.mlkit.camera.WorkflowModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { ABAApplication() }
    single { MainViewModel() }
    viewModel { PaymentsViewModel(get(RepoQualifier.Remote)) }
    viewModel { TransfersViewModel(get(RepoQualifier.Remote)) }
    viewModel { NewAccountsViewModel(get(RepoQualifier.Remote)) }
    viewModel { NewLoansViewModel(get(RepoQualifier.Remote)) }

    viewModel { WorkflowModel(get()) }
    viewModel { ScanQrViewModel() }
}