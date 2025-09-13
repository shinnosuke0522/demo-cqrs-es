package com.github.shinnosuke0522.demo.cqrs.es.domain.organization.event

import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.ULID

@JvmInline
value class ConferenceEventId private constructor(val value: ULID) : Comparable<ConferenceEventId> {
    override fun compareTo(other: ConferenceEventId): Int = this.value.compareTo(other.value)

    companion object {
        fun generate(): ConferenceEventId = ConferenceEventId(ULID.generate())

        fun of(value: String): ConferenceEventId = ConferenceEventId(ULID.of(value))
    }
}
