package com.github.shinnosuke0522.demo.cqrs.es.domain.proposal

import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.ULID

@JvmInline
value class DecisionId private constructor(val value: ULID) : Comparable<DecisionId> {
    override fun compareTo(other: DecisionId): Int = this.value.compareTo(other.value)

    companion object {
        fun generate(): DecisionId = DecisionId(ULID.generate())

        fun of(value: String): DecisionId = DecisionId(ULID.of(value))
    }
}