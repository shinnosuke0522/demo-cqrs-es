package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event

import arrow.core.Either
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.failure
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.success

@JvmInline
value class EventTitle private constructor(val value: String) {
    companion object {
        fun of(value: String): Either<ConferenceEventError, EventTitle> =
            when {
                value.isEmpty() -> failure(
                    InvalidConferenceEventTitleError("EventTitle cannot be empty")
                )
                value.length > 100 -> failure(
                    InvalidConferenceEventTitleError("EventTitle cannot be longer than 100 characters")
                )
                else -> success(EventTitle(value))
            }
    }
}