package com.github.shinnosuke0522.demo.cqrs.es.domain.organization.membership

import com.github.shinnosuke0522.demo.cqrs.es.domain.organization.OrganizationId
import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.model.Repository
import com.github.shinnosuke0522.demo.cqrs.es.domain.user.UserId

interface MembershipRepository : Repository<MembershipId, Membership> {
    fun findByOrganizationId(organizationId: OrganizationId): List<Membership>
    fun findByUserId(userId: UserId): List<Membership>
}