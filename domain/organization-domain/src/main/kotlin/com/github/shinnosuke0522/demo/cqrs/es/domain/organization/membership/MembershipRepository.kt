package com.github.shinnosuke0522.demo.cqrs.es.domain.organization.membership

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.Repository
import com.github.shinnosuke0522.demo.cqrs.es.domain.organization.organization.OrganizationId

interface MembershipRepository : Repository<Membership, MembershipId> {
    suspend fun findByOrganizationId(organizationId: OrganizationId): List<Membership>
    suspend fun findByUserId(userId: UserId): List<Membership>
}
