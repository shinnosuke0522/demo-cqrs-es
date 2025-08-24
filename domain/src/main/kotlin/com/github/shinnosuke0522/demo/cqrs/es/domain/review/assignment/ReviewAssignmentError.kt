package com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment

import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.error.DomainError

sealed interface ReviewAssignmentError : DomainError

data class ReviewAssignmentAlreadyFixedError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None,
) : ReviewAssignmentError