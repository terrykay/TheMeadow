package uk.co.tezk.themeadow

import android.app.Application
import uk.co.tezk.themeadow.injection.NetworkComponent
import uk.co.tezk.themeadow.preferences.MeadowPreferences

class MeadowApplication : Application() {

    lateinit var networkComponent: NetworkComponent

    override fun onCreate() {
        super.onCreate()
        MeadowPreferences.initialise(applicationContext)
        //networkComponent = DaggerNetworkComponent.create()
    }
}