package com.github.shinnosuke0522.demo.cqrs.es.domain.review.shared

import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.ULID

@JvmInline
value class ConferenceEventId private constructor(val id: ULID) {
    companion object {
        fun of(value: String): ConferenceEventId = ConferenceEventId(ULID.of(value))
    }
}
