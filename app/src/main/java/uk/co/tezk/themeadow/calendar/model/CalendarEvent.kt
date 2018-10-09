package uk.co.tezk.themeadow.calendar.model

import net.fortuna.ical4j.model.Component
import net.fortuna.ical4j.model.property.*
import java.util.*

data class CalendarEvent (
        val startDate: Date,
        val endDate: Date,
        val summary: String,
        val url: String
) {
    constructor(icalComponent: Component): this(
            startDate = icalComponent.getProperty<DtStart>("DTSTART").date,
            endDate = icalComponent.getProperty<DtEnd>("DTEND").date,
            summary = icalComponent.getProperty<Summary>("SUMMARY").value,
            url = icalComponent.getProperty<Url>("URL").value)

}