package uk.co.tezk.themeadow.injection

import dagger.Component
import uk.co.tezk.themeadow.MainActivity

@Component(modules = [NetworkModule::class])
interface NetworkComponent {
    fun inject(mainActivity: MainActivity)
}