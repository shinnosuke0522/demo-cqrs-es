package com.github.shinnosuke0522.demo.cqrs.es.domain.conference.event

import com.github.shinnosuke0522.demo.cqrs.es.domain.organization.OrganizationId

@JvmInline
value class Organizer(val value: OrganizationId) {}