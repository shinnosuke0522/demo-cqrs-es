package com.github.shinnosuke0522.demo.cqrs.es.domain.organization.event

import arrow.core.Either
import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.failure
import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.success

@JvmInline
value class EventTitle private constructor(val value: String) {
    companion object {
        private const val MAX_LENGTH = 100

        fun of(value: String): Either<ConferenceEventError, EventTitle> =
            when {
                value.isEmpty() -> failure(
                    InvalidConferenceEventTitleError("EventTitle cannot be empty")
                )
                value.length > MAX_LENGTH -> failure(
                    InvalidConferenceEventTitleError("EventTitle cannot be longer than 100 characters")
                )
                else -> success(EventTitle(value))
            }
    }
}
