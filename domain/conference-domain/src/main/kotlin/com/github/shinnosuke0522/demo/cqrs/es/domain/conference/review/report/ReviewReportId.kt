package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.review.report

import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.ULID

@JvmInline
value class ReviewReportId private constructor(val value: ULID) : Comparable<ReviewReportId> {
    override fun compareTo(other: ReviewReportId): Int = this.value.compareTo(other.value)

    companion object {
        fun generate(): ReviewReportId = ReviewReportId(ULID.Companion.generate())
        fun of(value: String): ReviewReportId = ReviewReportId(ULID.Companion.of(value))
    }
}