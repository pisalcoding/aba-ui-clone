package me.pisal.abaclone.di

import me.pisal.abaclone.ABAApplication
import me.pisal.abaclone.scene.MainViewModel
import me.pisal.abaclone.scene.loan.NewLoansViewModel
import me.pisal.abaclone.scene.newaccount.NewAccountsViewModel
import me.pisal.abaclone.scene.notifcation.NotificationsViewModel
import me.pisal.abaclone.scene.notifcation.announcement.AnnouncementNotiViewModel
import me.pisal.abaclone.scene.notifcation.transaction.TransactionNotiViewModel
import me.pisal.abaclone.scene.payment.PaymentsViewModel
import me.pisal.abaclone.scene.scanqr.ScanQrViewModel
import me.pisal.abaclone.scene.template.transfer.TransferTemplatesViewModel
import me.pisal.abaclone.scene.transfer.TransfersViewModel
import me.pisal.abaclone.thirdparty.mlkit.camera.WorkflowModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    single { ABAApplication() }
    single { MainViewModel() }
    viewModel { PaymentsViewModel(get(RepoQualifier.Local)) }
    viewModel { TransfersViewModel(get(RepoQualifier.Local)) }
    viewModel { NewAccountsViewModel(get(RepoQualifier.Local)) }
    viewModel { NewLoansViewModel(get(RepoQualifier.Local)) }

    viewModel { WorkflowModel(get()) }
    viewModel { ScanQrViewModel() }
    viewModel { NotificationsViewModel() }
    viewModel { TransactionNotiViewModel(get()) }
    viewModel { AnnouncementNotiViewModel(get()) }
    viewModel { TransferTemplatesViewModel(get()) }
}