package com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals

import com.github.f4b6a3.ulid.Ulid
import com.github.f4b6a3.ulid.UlidCreator

@JvmInline
value class ULID private constructor(val value: Ulid): Comparable<ULID> {
    override fun compareTo(other: ULID): Int = this.value.compareTo(other.value)

    companion object {
        fun generate(): ULID = ULID(UlidCreator.getUlid())

        fun of(value: String): ULID = ULID(Ulid.from(value))
    }
}