package com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.event

import com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.ReviewAssignmentId
import java.time.Instant

@ConsistentCopyVisibility
data class AssignmentOverdueEvent private constructor(
    override val id: AssignmentEventId,
    override val aggregateId: ReviewAssignmentId,
    override val occurredAt: Instant,
) : AssignmentEvent {
    companion object {
        fun of(
            aggregateId: ReviewAssignmentId,
        ): AssignmentOverdueEvent = run {
            val eventId = AssignmentEventId.generate()
            AssignmentOverdueEvent(
                id = eventId,
                aggregateId = aggregateId,
                occurredAt = eventId.value.toInstant(),
            )
        }
    }
}
