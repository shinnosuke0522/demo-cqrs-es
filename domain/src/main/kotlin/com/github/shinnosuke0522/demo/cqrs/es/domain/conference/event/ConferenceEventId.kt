package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event

import arrow.core.Either
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.ULID
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.failure
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.util.success

@JvmInline
value class ConferenceEventId private constructor(val value: ULID) : Comparable<ConferenceEventId> {
    override fun compareTo(other: ConferenceEventId): Int = this.value.compareTo(other.value)

    companion object {
        fun generate(): ConferenceEventId = ConferenceEventId(ULID.Companion.generate())

        fun of(value: String): ConferenceEventId = ConferenceEventId(ULID.Companion.of(value))
    }
}