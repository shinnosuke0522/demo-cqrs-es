package com.github.shinnosuke0522.demo.cqrs.es.domain.base.model

abstract class AggregateRoot<ID> {
    protected val domainEvents: MutableList<DomainEvent<*, ID>> = mutableListOf()

    protected fun addDomainEvent(event: DomainEvent<*, ID>) {
        this.domainEvents.add(event)
    }

    fun domainEvents(): List<DomainEvent<*, ID>> = this.domainEvents.toList()

    fun clearDomainEvents() = this.domainEvents.clear()
}