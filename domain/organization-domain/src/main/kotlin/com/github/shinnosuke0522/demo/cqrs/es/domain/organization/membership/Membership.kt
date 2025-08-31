package com.github.shinnosuke0522.demo.cqrs.es.domain.organization.membership

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.AggregateRoot
import java.time.Instant

sealed class Membership(
    open val id: MembershipId,
    open val role: Role,
    open val joinedAt: Instant
) : AggregateRoot<MembershipId>() {

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
) : Membership(id, role, joinedAt)

data class InactiveMembership(
    override val id: MembershipId,
    override val role: Membership.Role,
    override val joinedAt: Instant,
    val leftAt: Instant,
) : Membership(id, role, joinedAt)
