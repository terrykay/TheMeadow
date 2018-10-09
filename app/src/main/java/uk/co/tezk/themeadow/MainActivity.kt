package uk.co.tezk.themeadow

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.HttpException
import retrofit2.Retrofit
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import uk.co.tezk.themeadow.calendar.EventCalendar
import uk.co.tezk.themeadow.configuration.model.ConfigurationHandler
import uk.co.tezk.themeadow.injection.NetworkModule
import uk.co.tezk.themeadow.preferences.MeadowPreferences
import uk.co.tezk.themeadow.repository.MeadowRetrofit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    //@Inject
    lateinit var retrofitClient: Retrofit
    lateinit var meadowClient: MeadowRetrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //(application as MeadowApplication).networkComponent.inject(this)
        val nm = NetworkModule()
        retrofitClient = nm.provideRetrofitClient(nm.provideOkHttpclient(nm.provideInterceptor()))
        meadowClient = retrofitClient.create(MeadowRetrofit::class.java)
    }

    override fun onStart() {
        super.onStart()

        ConfigurationHandler().retrieveConfig(meadowClient) { EventCalendar(meadowClient).refreshCalendar() }
    }
}
