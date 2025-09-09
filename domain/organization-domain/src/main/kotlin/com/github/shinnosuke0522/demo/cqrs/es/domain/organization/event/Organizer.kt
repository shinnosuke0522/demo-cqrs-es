package com.github.shinnosuke0522.demo.cqrs.es.domain.organization.event

import com.github.shinnosuke0522.demo.cqrs.es.domain.organization.organization.OrganizationId

@JvmInline
value class Organizer(val value: OrganizationId)
