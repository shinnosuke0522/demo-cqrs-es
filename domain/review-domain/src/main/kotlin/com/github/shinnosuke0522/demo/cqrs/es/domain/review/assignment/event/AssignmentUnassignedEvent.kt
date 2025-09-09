package com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.event

import com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.ReviewAssignmentId
import java.time.Instant

@ConsistentCopyVisibility
data class AssignmentUnassignedEvent private constructor(
    override val id: AssignmentEventId,
    override val aggregateId: ReviewAssignmentId,
    override val occurredAt: Instant,
    val unassignedAt: Instant
) : AssignmentEvent {
    companion object {
        fun of(
            aggregateId: ReviewAssignmentId,
            unassignedAt: Instant,
        ): AssignmentUnassignedEvent = run {
            val eventId = AssignmentEventId.generate()
            AssignmentUnassignedEvent(
                id = eventId,
                aggregateId = aggregateId,
                occurredAt = eventId.value.toInstant(),
                unassignedAt = unassignedAt,
            )
        }
    }
}
