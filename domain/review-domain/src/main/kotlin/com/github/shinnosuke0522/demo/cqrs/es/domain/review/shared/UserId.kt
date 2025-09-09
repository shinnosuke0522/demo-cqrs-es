package com.github.shinnosuke0522.demo.cqrs.es.domain.review.shared

import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.ULID

@JvmInline
value class UserId private constructor(val value: ULID) : Comparable<UserId> {
    override fun compareTo(other: UserId): Int = this.value.compareTo(other.value)

    companion object {
        fun of(value: String): UserId = UserId(ULID.of(value))
    }
}
