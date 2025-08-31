package com.github.shinnosuke0522.demo.cqrs.es.domain.organization

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.error.DomainError

sealed interface OrganizationError : DomainError

data class InvalidOrganizationNameError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None,
) : OrganizationError
