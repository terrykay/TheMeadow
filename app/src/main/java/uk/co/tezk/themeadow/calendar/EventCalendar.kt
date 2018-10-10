package uk.co.tezk.themeadow.calendar

import android.util.Log
import net.fortuna.ical4j.data.CalendarBuilder
import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.property.DtStart
import okhttp3.*
import uk.co.tezk.themeadow.preferences.MeadowPreferences
import uk.co.tezk.themeadow.repository.MeadowRetrofit
import uk.co.tezk.themeadow.calendar.model.CalendarEvent
import java.io.IOException
import java.io.StringReader
import java.util.*

class EventCalendar {
    /**
     * Deals with event calendar
     *
     */
    
    fun refreshCalendar() {
        val url = MeadowPreferences.getInstance().get(MeadowPreferences.CALENDAR_URL)

        val request = Request.Builder()
                .url(url)
                .build()
        OkHttpClient().newCall(request).enqueue(
                object : Callback {
                    override fun onFailure(call: Call, e: IOException) {

                    }

                    override fun onResponse(call: Call, response: Response) {
                        if (response.isSuccessful) response.body()?.let {
                            val calendar = CalendarBuilder().build(StringReader(it.string()))
                            EventCalendar.calendar = calendar
                            calendar.components.forEach { component ->
                                val event = CalendarEvent(component)
                                Log.d("EC", "event = $event")
                            }
                        }
                    }
                }
        )
    }

    companion object {
        var calendar: Calendar? = null
    }
}
