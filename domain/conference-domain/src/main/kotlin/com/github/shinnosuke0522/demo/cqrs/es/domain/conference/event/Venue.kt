package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event

import arrow.core.Either
import arrow.core.NonEmptyList
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.failure
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.success

@ConsistentCopyVisibility
data class Venue private constructor(
    val name: String,
    val address: String,
    val zipCode: String,
    val capacity: Int
) {
    companion object {
        private val ZIP_CODE_PATTERN = Regex("""\d{3}-\d{4}""")

        fun of(
            name: String,
            address: String,
            zipCode: String,
            capacity: Int
        ): Either<NonEmptyList<ConferenceEventError>, Venue> {
            return Either.zipOrAccumulate(
                validateName(name),
                validateAddress(address),
                validateZipCode(zipCode),
                validateCapacity(capacity)
            ) { validatedName, validatedAddress, validatedZipCode, validatedCapacity ->
                Venue(validatedName, validatedAddress, validatedZipCode, validatedCapacity)
            }
        }

        private fun validateName(name: String): Either<ConferenceEventError, String> =
            if (name.isBlank()) {
                failure(InvalidConferenceEventVenueError("venu name is required"))
            } else {
                success(name)
            }

        private fun validateAddress(address: String): Either<ConferenceEventError, String> =
            if (address.isBlank()) {
                failure(InvalidConferenceEventVenueError("address is required"))
            } else {
                success(address)
            }

        private fun validateCapacity(capacity: Int): Either<ConferenceEventError, Int> =
            if (capacity <= 0) {
                failure(InvalidConferenceEventVenueError("capacity size must be greater than 0"))
            } else {
                success(capacity)
            }

        private fun validateZipCode(zipCode: String): Either<ConferenceEventError, String> =
            if (zipCode.isBlank()) {
                failure(InvalidConferenceEventVenueError("zipcode is required"))
            } else if (!ZIP_CODE_PATTERN.matches(zipCode)) {
                failure(InvalidConferenceEventVenueError("zipcode format is not correct"))
            } else {
                success(zipCode)
            }
    }
}
