package uk.co.tezk.themeadow.repository

import retrofit2.http.GET
import rx.Single
import uk.co.tezk.themeadow.configuration.model.ConfigrationConstants.BASE_URL
import uk.co.tezk.themeadow.configuration.model.ConfigrationConstants.CONFIG_API
import uk.co.tezk.themeadow.configuration.model.MeadowConfig

interface MeadowRetrofit {
    @GET(BASE_URL + CONFIG_API)
    abstract fun getConfig(): Single<MeadowConfig>
}