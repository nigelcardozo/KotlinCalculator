package com.nigelcardozo.kotlincalculator.injection

import com.nigelcardozo.kotlincalculator.main.presenter.MainPresenter
import com.nigelcardozo.kotlincalculator.main.view.MainView
import org.koin.dsl.module.module

val appModule = module {

    single { (mainView: MainView) -> MainPresenter(mainView) }
}