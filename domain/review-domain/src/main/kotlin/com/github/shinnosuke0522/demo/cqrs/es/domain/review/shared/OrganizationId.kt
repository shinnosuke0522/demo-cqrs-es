package com.github.shinnosuke0522.demo.cqrs.es.domain.review.shared

import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.ULID

@JvmInline
value class OrganizationId private constructor(val value: ULID) : Comparable<OrganizationId> {
    override fun compareTo(other: OrganizationId): Int = this.value.compareTo(other.value)

    companion object {
        fun of(value: String): OrganizationId = OrganizationId(ULID.of(value))
    }
}
