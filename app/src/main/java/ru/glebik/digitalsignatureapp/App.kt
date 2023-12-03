package ru.glebik.digitalsignatureapp

import android.app.Application
import org.bouncycastle.jce.provider.BouncyCastleProvider
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import ru.glebik.core.utils.utilsModule
import ru.glebik.digitalsignatureapp.di.mainModule
import java.security.Security

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Security.addProvider(BouncyCastleProvider())

        startKoin {
            androidLogger()
            androidContext(this@App)

            modules(
                listOf(
                    mainModule,
                    utilsModule
                )
            )
        }
    }

}