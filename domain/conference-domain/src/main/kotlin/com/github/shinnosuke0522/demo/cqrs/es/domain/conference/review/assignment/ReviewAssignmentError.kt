package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.assignment

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.error.DomainError

sealed interface ReviewAssignmentError : DomainError

data class ReviewAssignmentAlreadyFixedError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None,
) : ReviewAssignmentError
