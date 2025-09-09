package com.github.shinnosuke0522.demo.cqrs.es.domain.organization.event

import com.github.shinnosuke0522.demo.cqrs.es.domain.organization.membership.MembershipId

data class Staff(
    val memberShip: MembershipId,
    val role: Role,
) {
    enum class Role {
        ADMIN,
        REVIEW_ONLY,
        OPERATE_ONLY,
        REVIEW_AND_OPERATE,
    }

    fun isAdmin(): Boolean = role == Role.ADMIN
    fun isOperator(): Boolean = role == Role.ADMIN || role == Role.OPERATE_ONLY || role == Role.REVIEW_AND_OPERATE
    fun isReviewer(): Boolean = role == Role.ADMIN || role == Role.REVIEW_ONLY || role == Role.REVIEW_AND_OPERATE
}
