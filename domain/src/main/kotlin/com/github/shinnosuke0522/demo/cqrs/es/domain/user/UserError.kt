package com.github.shinnosuke0522.demo.cqrs.es.domain.user

import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.error.DomainError

sealed interface UserError: DomainError

data class InvalidUserError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None,
): UserError

data class InvalidUserEmailError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None,
): UserError