package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.proposal

import arrow.core.Either
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.failure
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.success

@JvmInline
value class ProposalTitle(val value: String) {
    companion object {
        fun of(value: String): Either<ProposalError, ProposalTitle> =
            when {
                value.isEmpty() -> failure(
                    InvalidProposalTitleError("ProposalTitle cannot be empty")
                )
                value.length > 100 -> failure(
                    InvalidProposalTitleError("ProposalTitle cannot be longer than 100 characters")
                )
                else -> success(ProposalTitle(value))
            }
    }
}