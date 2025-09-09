package com.github.shinnosuke0522.demo.cqrs.es.domain.organization.organization

import arrow.core.Either
import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.failure
import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.success

@JvmInline
value class OrganizationName private constructor(val value: String) {
    companion object {
        private const val MAX_LENGTH = 100
        fun of(value: String): Either<OrganizationError, OrganizationName> =
            when {
                value.isBlank() -> failure(
                    InvalidOrganizationNameError("Organization name must not be blank")
                )
                value.length > MAX_LENGTH -> failure(
                    InvalidOrganizationNameError("Organization name must be less than or equal to 100 characters")
                )
                else -> success(OrganizationName(value))
            }
    }
}
