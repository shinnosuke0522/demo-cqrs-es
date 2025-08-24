package com.github.shinnosuke0522.demo.cqrs.es.domain.organization.membership

import com.github.shinnosuke0522.demo.cqrs.es.domain.organization.OrganizationId
import com.github.shinnosuke0522.demo.cqrs.es.domain.user.UserId

data class MembershipId(
    val userId: UserId,
    val organizationId: OrganizationId,
)