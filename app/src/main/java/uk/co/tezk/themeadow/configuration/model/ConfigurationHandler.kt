package uk.co.tezk.themeadow.configuration

import android.util.Log
import retrofit2.HttpException
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import uk.co.tezk.themeadow.configuration.model.MeadowConfig
import uk.co.tezk.themeadow.preferences.MeadowPreferences
import uk.co.tezk.themeadow.repository.MeadowRetrofit
import javax.net.ssl.SSLHandshakeException

class ConfigurationHandler {
    fun retrieveConfig(meadowClient: MeadowRetrofit, callBack: ((meadowConfig : MeadowConfig) -> Unit)? = null) {
        meadowClient.getConfig()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ meadowConfig ->
                    MeadowPreferences.getInstance().set(MeadowPreferences.CALENDAR_URL, meadowConfig.calendarUrl)
                    callBack?.let { it(meadowConfig) }
                }, {

                    when (it) {
                        is HttpException -> {
                            when (it.code()) {
                                404 -> { /*can't find config! */ }
                                else -> { }
                            }
                        }
                        is SSLHandshakeException -> {
                            Log.d("CH", "SSL handshake - ${it.message}")
                        }
                        else -> {}
                    }
                })
    }
}
