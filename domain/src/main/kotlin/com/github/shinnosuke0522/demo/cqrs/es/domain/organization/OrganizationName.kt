package com.github.shinnosuke0522.demo.cqrs.es.domain.organization

import arrow.core.Either
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.failure
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.success

@JvmInline
value class OrganizationName private constructor(val value: String) {
    init {
        require(value.isNotBlank()) { "Organization name must not be blank" }
        require(value.length <= 100) { "Organization name must be less than or equal to 100 characters" }
    }

    companion object {
        fun of(value: String): Either<OrganizationError, OrganizationName> =
            when {
                value.isBlank() -> failure(
                    InvalidOrganizationNameError("Organization name must not be blank")
                )
                value.length > 100 -> failure(
                    InvalidOrganizationNameError("Organization name must be less than or equal to 100 characters")
                )
                else -> success(OrganizationName(value))
        }
    }

}