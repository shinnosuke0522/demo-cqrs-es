package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event

import arrow.core.Either
import arrow.core.Option
import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.AggregateRoot
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.failure
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.success

@ConsistentCopyVisibility
data class ConferenceEvent private constructor(
    val id: ConferenceEventId,
    val title: EventTitle,
    val status: EventStatus,
    val hostingType: HostingType,
    val venue: Option<Venue>,
    val meetingUrl: Option<MeetingUrl>,
    val staff: Staffs,
    val organizer: Organizer,
) : AggregateRoot<ConferenceEventId>() {
    enum class HostingType {
        ONLINE, OFFLINE, HYBRID
    }

    companion object {
        fun of(
            id: ConferenceEventId,
            title: EventTitle,
            status: EventStatus,
            venue: Option<Venue>,
            meetingUrl: Option<MeetingUrl>,
            staff: Staffs,
            organizer: Organizer,
        ): Either<ConferenceEventError, ConferenceEvent> {
            val hostingType = when {
                venue.isSome() && meetingUrl.isSome() -> HostingType.HYBRID
                venue.isSome() -> HostingType.OFFLINE
                meetingUrl.isSome() -> HostingType.ONLINE
                else -> return failure(
                    InvalidConferenceEventHostingTypeError("Either venue or meetingUrl must be provided")
                )
            }
            return success(
                ConferenceEvent(
                    id = id,
                    title = title,
                    status = status,
                    venue = venue,
                    hostingType = hostingType,
                    meetingUrl = meetingUrl,
                    staff = staff,
                    organizer = organizer,
                )
            )
        }
    }
}
