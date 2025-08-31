package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event

import arrow.core.Either
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.failure
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.success

@JvmInline
value class MeetingUrl private constructor(val value: String) {

    companion object {
        fun of(value: String): Either<ConferenceEventError, MeetingUrl> {
            return when {
                value.isBlank() -> failure(
                    InvalidConferenceEventMeetingUrlError("MeetingUrl must not be blank")
                )
                value.length > 200 -> failure(
                    InvalidConferenceEventMeetingUrlError("MeetingUrl must be less than 200 characters")
                )
                !value.startsWith("https://") -> failure(
                    InvalidConferenceEventMeetingUrlError("MeetingUrl must start with https://")
                )
                else -> success(MeetingUrl(value))
            }
        }
    }
}
