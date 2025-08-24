package com.github.shinnosuke0522.demo.cqrs.es.domain.organization.membership

import java.time.Instant

sealed interface Membership {
    val id: MembershipId
    val role: Role
    val joinedAt: Instant

    enum class Role {
        ADMIN,
        MEMBER,
    }

    fun isAdmin(): Boolean = role == Role.ADMIN
}

data class ActiveMembership(
    override val id: MembershipId,
    override val role: Membership.Role,
    override val joinedAt: Instant = Instant.now(),
) : Membership

data class InactiveMembership(
    override val id: MembershipId,
    override val role: Membership.Role,
    override val joinedAt: Instant,
    val leftAt: Instant,
) : Membership
