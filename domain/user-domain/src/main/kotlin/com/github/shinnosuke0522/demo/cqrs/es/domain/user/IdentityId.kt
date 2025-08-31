package com.github.shinnosuke0522.demo.cqrs.es.domain.user

import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.ULID

@JvmInline
value class IdentityId private constructor(val value: ULID) {
    companion object {
        fun generate(): IdentityId = IdentityId(ULID.generate())

        fun of(value: String): IdentityId = IdentityId(ULID.of(value))
    }
}
