package com.github.shinnosuke0522.demo.cqrs.es.domain.shared.model

import arrow.core.Option

interface Repository<ID, E> {
    suspend fun exists(id: ID): Boolean
    suspend fun findById(id: ID): Option<E>
    suspend fun findByIds(ids: Collection<ID>): Map<ID, E>

    suspend fun save(entity: E)
}