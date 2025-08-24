package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.session

import arrow.core.Either
import arrow.core.NonEmptyList
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.failure
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.success
import java.time.Instant


@ConsistentCopyVisibility
data class SessionSchedule private constructor(val start: Instant, val end: Instant) {
    companion object {

        fun of(start: Instant, end: Instant): Either<NonEmptyList<SessionError>, SessionSchedule> {
            return Either.zipOrAccumulate(
                validateStart(start),
                validateEnd(start, end)
            ) { validStart, validEnd ->
                SessionSchedule(validStart, validEnd)
            }
        }

        private fun validateStart(start: Instant) : Either<SessionError, Instant> =
            when {
                start.isBefore(Instant.now()) -> failure(
                    InvalidSessionScheduleError("Session start time must be in the future")
                )
                else -> success(start)
            }

        private fun validateEnd(start: Instant, end: Instant) : Either<SessionError, Instant> =
            when {
                end.isBefore(Instant.now()) -> failure(
                    InvalidSessionScheduleError("Session end time must be in the future")
                )
                end.isBefore(start) || end == start -> failure(
                    InvalidSessionScheduleError("Session end time must be after start time")
                )
                else -> success(end)
            }
    }
}
