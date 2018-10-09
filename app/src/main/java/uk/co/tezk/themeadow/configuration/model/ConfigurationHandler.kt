package uk.co.tezk.themeadow.configuration.model

import retrofit2.HttpException
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import uk.co.tezk.themeadow.preferences.MeadowPreferences
import uk.co.tezk.themeadow.repository.MeadowRetrofit

class ConfigurationHandler {
    fun retrieveConfig(meadowClient: MeadowRetrofit, callBack: (() -> Unit)? = null) {
        meadowClient.getConfig()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ meadowConfig ->
                    MeadowPreferences.getInstance().set(MeadowPreferences.CALENDAR_URL, meadowConfig.calendarUrl)
                    callBack?.let { it() }
                }, {
                    it.printStackTrace()
                    if (it is HttpException) {
                        when (it.code()) {
                            404 -> { /*can't find config! */ }
                            else -> { }
                        }
                    }
                })
    }
}