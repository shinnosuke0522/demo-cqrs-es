package com.github.shinnosuke0522.demo.cqrs.es.domain.organization.event

import arrow.core.Either
import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.failure
import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.success

@JvmInline
value class MeetingUrl private constructor(val value: String) {

    companion object {
        private const val MAX_LENGTH = 200

        fun of(value: String): Either<ConferenceEventError, MeetingUrl> {
            return when {
                value.isBlank() -> failure(
                    InvalidConferenceEventMeetingUrlError("MeetingUrl must not be blank")
                )
                value.length > MAX_LENGTH -> failure(
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
