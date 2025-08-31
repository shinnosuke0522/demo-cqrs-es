package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.assignment

import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.ULID

@JvmInline
value class ReviewAssignmentId private constructor(val value: ULID) : Comparable<ReviewAssignmentId> {
    override fun compareTo(other: ReviewAssignmentId): Int = this.value.compareTo(other.value)

    companion object {
        fun generate(): ReviewAssignmentId = ReviewAssignmentId(ULID.generate())

        fun of(value: String): ReviewAssignmentId = ReviewAssignmentId(ULID.of(value))
    }
}
