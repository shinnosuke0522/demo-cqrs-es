package com.github.shinnosuke0522.demo.cqrs.es.domain.shared.model

import java.time.Instant

interface DomainEvent<ID, AGGREGATE_ID> {
    val id: ID
    val aggregateId: AGGREGATE_ID
    val occurredAt: Instant
}