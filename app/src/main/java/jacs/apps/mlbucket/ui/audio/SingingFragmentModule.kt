package jacs.apps.mlbucket.ui.audio


import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val singingFragmentModule = module {

    single { SingRecorder("hotKey", 0, get()) }

    // Use factory instead of single when user presses back button...
    // to force execution of init block when interpreter is closed
    factory { PitchModelExecutor(get(), getKoin().getProperty("koinUseGpu")!!) }

    viewModel {
        SingingFragmentViewModel(get())
    }
}