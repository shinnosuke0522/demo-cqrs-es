package com.github.shinnosuke0522.demo.cqrs.es.domain.review.proposal

import arrow.core.Either
import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.failure
import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.success

@ConsistentCopyVisibility
data class ProposalTopic private constructor(
    val id: ProposalId,
    val keyword: String,
) {
    companion object {
        private val SNAKE_CASE_REGEX = "^[a-z]+(_[a-z]+)*$".toRegex()
        private const val MAX_LENGTH = 50

        fun of(keyword: String): Either<ProposalError, ProposalTopic> =
            when {
                keyword.isEmpty() -> failure(
                    InvalidTopicError("ProposalTopic cannot be empty")
                )
                keyword.length > MAX_LENGTH -> failure(
                    InvalidTopicError("ProposalTopic cannot be longer than 50 characters")
                )
                !SNAKE_CASE_REGEX.matches(keyword) -> failure(
                    InvalidTopicError("ProposalTopic must be in snake_case format")
                )
                else -> success(
                    ProposalTopic(ProposalId.generate(), keyword)
                )
            }
    }
}
