package com.github.shinnosuke0522.demo.cqrs.es.domain.review.proposal

import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.ULID

@JvmInline
value class ProposalContentId private constructor(val value: ULID) : Comparable<ProposalContentId> {
    override fun compareTo(other: ProposalContentId): Int = this.value.compareTo(other.value)

    companion object {
        fun generate(): ProposalContentId = ProposalContentId(ULID.generate())

        fun of(value: String): ProposalContentId = ProposalContentId(ULID.of(value))
    }
}
