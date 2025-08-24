package com.github.shinnosuke0522.demo.cqrs.es.domain.organization

import java.time.Instant

data class Organization(
    val id: OrganizationId,
    val name: OrganizationName,
    val startedAt: Instant = Instant.now(),
)
