package mx.com.ediel.mv.punkapp.ui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PunkApp: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}