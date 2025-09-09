package com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.event

import com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.ReviewAssignmentId
import java.time.Instant

@ConsistentCopyVisibility
data class AssignmentRejectedEvent private constructor(
    override val id: AssignmentEventId,
    override val aggregateId: ReviewAssignmentId,
    override val occurredAt: Instant,
    val rejectedAt: Instant
) : AssignmentEvent {

    companion object {
        fun of(aggregateId: ReviewAssignmentId, rejectedAt: Instant): AssignmentRejectedEvent = run {
            val eventId = AssignmentEventId.generate()
            AssignmentRejectedEvent(
                id = eventId,
                aggregateId = aggregateId,
                occurredAt = eventId.value.toInstant(),
                rejectedAt = rejectedAt,
            )
        }
    }
}
