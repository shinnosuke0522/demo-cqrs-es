package com.github.shinnosuke0522.demo.cqrs.es.domain.organization.session

import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.ULID

@JvmInline
value class Proposal(val id: ULID) {
    companion object {
        fun of(value: String): Proposal = Proposal(ULID.of(value))
    }
}
