package com.github.shinnosuke0522.demo.cqrs.es.domain.organization.membership

import com.github.shinnosuke0522.demo.cqrs.es.domain.organization.OrganizationId

data class MembershipId(
    val userId: UserId,
    val organizationId: OrganizationId,
)