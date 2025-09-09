package com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.event

import com.github.shinnosuke0522.demo.cqrs.es.domain.base.model.DomainEvent
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.ReviewAssignmentId

sealed interface AssignmentEvent : DomainEvent<AssignmentEventId, ReviewAssignmentId>
