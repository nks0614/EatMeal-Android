package com.project.eatmeal.widget

import android.app.Application
import android.content.Context
import com.project.domain.usecase.LoginUseCase
import com.project.eatmeal.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EatMealApplication : Application() {

    companion object {
        const val SHARED = "user"
        const val ID = "ID"
        const val PW = "PW"
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@EatMealApplication)
            val modules = listOf(
                dataSourceModule, apiModule, networkModule, remoteModule, repositoryModule,
                useCaseModule, viewModelModule
            )
            this.modules(modules)
        }
    }

}