package com.github.shinnosuke0522.demo.cqrs.es.domain.proposal

import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.ULID

@JvmInline
value class ProposalTopicId private constructor(val value: ULID) : Comparable<ProposalTopicId> {
    override fun compareTo(other: ProposalTopicId): Int = this.value.compareTo(other.value)

    companion object {
        fun generate(): ProposalTopicId = ProposalTopicId(ULID.generate())
        fun of(value: String): ProposalTopicId = ProposalTopicId(ULID.of(value))
    }
}