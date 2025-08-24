package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.session

import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.ULID

@JvmInline
value class SessionId private constructor(val value: ULID) : Comparable<SessionId> {
    override fun compareTo(other: SessionId): Int = this.value.compareTo(other.value)

    companion object {
        fun generate(): SessionId = SessionId(ULID.generate())

        fun of(value: String): SessionId = SessionId(ULID.of(value))
    }
}