package com.github.shinnosuke0522.demo.cqrs.es.domain.organization

import com.github.shinnosuke0522.demo.cqrs.es.domain.shared.model.Repository

interface OrganizationRepository : Repository<OrganizationId, Organization>