package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.proposal

import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.error.DomainError

sealed interface ProposalError : DomainError

data class InvalidProposalTitleError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None,
) : ProposalError

data class InvalidTopicError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None,
) : ProposalError

data class ProposalAuthorNotFoundError(
    override val message: String,
    override val cause: DomainError.Cause = DomainError.Cause.None,
): ProposalError
