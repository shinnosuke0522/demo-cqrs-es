package com.github.shinnosuke0522.demo.cqrs.es.domain.review.shared

import com.github.shinnosuke0522.demo.cqrs.es.libraries.fundamentals.ULID

@JvmInline
value class OrganizationMembershipId private constructor(val value: ULID) : Comparable<OrganizationMembershipId> {
    override fun compareTo(other: OrganizationMembershipId): Int = this.value.compareTo(other.value)

    companion object {
        fun of(value: String): OrganizationMembershipId = OrganizationMembershipId(ULID.of(value))
    }
}
