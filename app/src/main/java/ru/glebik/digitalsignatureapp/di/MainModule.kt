package ru.glebik.digitalsignatureapp.di

import org.koin.dsl.module
import ru.glebik.digitalsignatureapp.data.MainRepositoryImpl
import ru.glebik.digitalsignatureapp.domain.MainRepository
import ru.glebik.digitalsignatureapp.presentation.viewmodel.MainScreenModel

val mainModule = module {

    single<MainRepository> { MainRepositoryImpl(get()) }

    factory { MainScreenModel(get()) }

}