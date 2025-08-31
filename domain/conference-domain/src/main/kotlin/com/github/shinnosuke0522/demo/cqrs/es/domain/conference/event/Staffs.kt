package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event

import arrow.core.Either
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.failure
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.success

@JvmInline
value class Staffs private constructor(val staffs: List<Staff>) {
    companion object {
        fun of(staffs: List<Staff>): Either<ConferenceEventError, Staffs> {
            val adminCount = staffs.count { it.isAdmin() }
            return when {
                staffs.isEmpty() -> failure(
                    InvalidConferenceEvetStaffsError("Staffs must not be empty")
                )
                staffs.size > 100 -> failure(
                    InvalidConferenceEvetStaffsError("Staffs must not be more than 100")
                )
                adminCount == 0 -> failure(
                    InvalidConferenceEvetStaffsError("Admin Staff is required")
                )
                adminCount > 1 -> failure(
                    InvalidConferenceEvetStaffsError("Only one Admin Staff is allowed")
                )
                else -> success(Staffs(staffs))
            }
        }
    }
}