package com.github.shinnosuke0522.demo.cqrs.es.domain.organization.membership

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.Repository
import com.github.shinnosuke0522.demo.cqrs.es.domain.organization.OrganizationId

interface MembershipRepository : Repository<Membership, MembershipId> {
    fun findByOrganizationId(organizationId: OrganizationId): List<Membership>
    fun findByUserId(userId: UserId): List<Membership>
}
