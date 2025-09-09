package com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.event

import com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.ReviewAssignmentId
import java.time.Instant

@ConsistentCopyVisibility
data class AssignmentApprovedEvent private constructor(
    override val id: AssignmentEventId,
    override val aggregateId: ReviewAssignmentId,
    override val occurredAt: Instant,
    val approvedAt: Instant
) : AssignmentEvent {
    companion object {
        fun of(
            aggregateId: ReviewAssignmentId,
            approvedAt: Instant,
        ): AssignmentApprovedEvent = run {
            val eventId = AssignmentEventId.generate()
            AssignmentApprovedEvent(
                id = eventId,
                aggregateId = aggregateId,
                occurredAt = eventId.value.toInstant(),
                approvedAt = approvedAt,
            )
        }
    }
}
