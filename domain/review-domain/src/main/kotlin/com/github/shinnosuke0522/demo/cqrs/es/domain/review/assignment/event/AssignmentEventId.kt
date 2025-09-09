package com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.event

import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.ULID

@JvmInline
value class AssignmentEventId private constructor(val value: ULID) : Comparable<AssignmentEventId> {
    override fun compareTo(other: AssignmentEventId): Int = this.value.compareTo(other.value)

    companion object {
        fun generate(): AssignmentEventId = AssignmentEventId(ULID.Companion.generate())

        fun of(value: String): AssignmentEventId = AssignmentEventId(ULID.Companion.of(value))
    }
}
