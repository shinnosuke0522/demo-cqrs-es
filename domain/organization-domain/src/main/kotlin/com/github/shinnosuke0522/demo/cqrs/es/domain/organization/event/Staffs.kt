package com.github.shinnosuke0522.demo.cqrs.es.domain.organization.event

import arrow.core.Either
import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.failure
import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.success

@JvmInline
value class Staffs private constructor(val staffs: List<Staff>) {
    companion object {
        private const val MAX_STAFFS = 100
        private const val ADMIN_REQUIRED = 1

        fun of(staffs: List<Staff>): Either<ConferenceEventError, Staffs> {
            val adminCount = staffs.count { it.isAdmin() }
            return when {
                staffs.isEmpty() -> failure(
                    InvalidConferenceEvetStaffsError("Staffs must not be empty")
                )
                staffs.size > MAX_STAFFS -> failure(
                    InvalidConferenceEvetStaffsError("Staffs must not be more than 100")
                )
                adminCount < ADMIN_REQUIRED -> failure(
                    InvalidConferenceEvetStaffsError("Admin Staff is required")
                )
                adminCount > ADMIN_REQUIRED -> failure(
                    InvalidConferenceEvetStaffsError("Only one Admin Staff is allowed")
                )
                else -> success(Staffs(staffs))
            }
        }
    }
}
