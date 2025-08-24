package com.github.shinnosuke0522.demo.cqrs.es.domain.organization.membership

import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.error.DomainError

sealed interface MembershipError: DomainError

data class OrganizationNotFoundError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None,
): MembershipError

data class OrganizationAlreadyJoinedError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None,
): MembershipError

data class UserNotFoundError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None,
): MembershipError