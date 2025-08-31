package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event

import arrow.core.Either
import arrow.core.NonEmptyList
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.failure
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.success
import java.time.Instant

@ConsistentCopyVisibility
data class Schedule private constructor(
    val start: Instant,
    val end: Instant,
) {
    companion object {

        fun of(start: Instant, end: Instant): Either<NonEmptyList<ConferenceEventError>, Schedule> {
            return Either.zipOrAccumulate(
                validateStart(start),
                validateEnd(end, start)
            ) { validStart, validEnd ->
                Schedule(validStart, validEnd)
            }
        }

        private fun validateStart(start: Instant): Either<ConferenceEventError, Instant> =
            if (start.isBefore(Instant.now())) {
                failure(InvalidConferenceEventScheduleError("Start time must be in the future"))
            } else {
                success(start)
            }

        private fun validateEnd(end: Instant, start: Instant): Either<ConferenceEventError, Instant> =
            if (end.isBefore(Instant.now())) {
                failure(InvalidConferenceEventScheduleError("End time must be in the future"))
            } else if (end.isBefore(start) || end == start) {
                failure(InvalidConferenceEventScheduleError("End time must be after start time"))
            } else {
                success(end)
            }
    }
}
