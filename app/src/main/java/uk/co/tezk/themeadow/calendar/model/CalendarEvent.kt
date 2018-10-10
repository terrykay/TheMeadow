package uk.co.tezk.themeadow.calendar.model

import android.os.Parcel
import android.os.Parcelable
import net.fortuna.ical4j.model.Component
import net.fortuna.ical4j.model.property.*
import java.util.*

data class CalendarEvent (
        val startDate: Date,
        val endDate: Date,
        val summary: String,
        val url: String
) : Parcelable {
    constructor(icalComponent: Component): this(
            startDate = icalComponent.getProperty<DtStart>("DTSTART").date,
            endDate = icalComponent.getProperty<DtEnd>("DTEND").date,
            summary = icalComponent.getProperty<Summary>("SUMMARY").value,
            url = icalComponent.getProperty<Url>("URL").value)

    constructor(parcel: Parcel) : this (
            startDate = Date(parcel.readLong()),
            endDate = Date(parcel.readLong()),
            summary = parcel.readString(),
            url = parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(startDate.time)
        parcel.writeLong(endDate.time)
        parcel.writeString(summary)
        parcel.writeString(url)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<CalendarEvent> {
        override fun createFromParcel(parcel: Parcel): CalendarEvent {
            return CalendarEvent(parcel)
        }

        override fun newArray(size: Int): Array<CalendarEvent?> {
            return arrayOfNulls(size)
        }
    }
}
