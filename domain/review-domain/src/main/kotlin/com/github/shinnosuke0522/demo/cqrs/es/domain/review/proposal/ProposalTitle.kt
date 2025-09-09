package com.github.shinnosuke0522.demo.cqrs.es.domain.review.proposal

import arrow.core.Either
import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.failure
import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.success

@JvmInline
value class ProposalTitle(val value: String) {
    companion object {
        private const val MAX_LENGTH = 100

        fun of(value: String): Either<ProposalError, ProposalTitle> =
            when {
                value.isEmpty() -> failure(
                    InvalidProposalTitleError("ProposalTitle cannot be empty")
                )
                value.length > MAX_LENGTH -> failure(
                    InvalidProposalTitleError("ProposalTitle cannot be longer than 100 characters")
                )
                else -> success(ProposalTitle(value))
            }
    }
}
