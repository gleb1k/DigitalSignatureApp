package ru.glebik.core.utils

import org.koin.dsl.module
import ru.glebik.core.utils.base.FileHelper

val utilsModule = module {
    single<FileHelper> { FileHelper(get()) }
}