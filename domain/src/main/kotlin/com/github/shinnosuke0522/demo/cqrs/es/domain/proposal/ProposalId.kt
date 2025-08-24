package com.github.shinnosuke0522.demo.cqrs.es.domain.proposal

import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.ULID

@JvmInline
value class ProposalId private constructor(val value: ULID) : Comparable<ProposalId> {
    override fun compareTo(other: ProposalId): Int = this.value.compareTo(other.value)

    companion object {
        fun generate(): ProposalId = ProposalId(ULID.generate())

        fun of(value: String): ProposalId = ProposalId(ULID.of(value))
    }
}