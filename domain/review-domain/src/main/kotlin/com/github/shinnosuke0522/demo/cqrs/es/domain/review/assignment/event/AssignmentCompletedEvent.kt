package com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.event

import com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.ReviewAssignmentId
import java.time.Instant

/**
 * 承諾されたレビューアサインのレビューが完了したことを示すイベント
 */
data class AssignmentCompletedEvent(
    override val id: AssignmentEventId,
    override val aggregateId: ReviewAssignmentId,
    override val occurredAt: Instant,
    val completedAt: Instant,
) : AssignmentEvent {
    companion object {
        fun of(
            aggregateId: ReviewAssignmentId,
            completedAt: Instant,
        ): AssignmentCompletedEvent = run {
            val eventId = AssignmentEventId.generate()
            AssignmentCompletedEvent(
                id = eventId,
                aggregateId = aggregateId,
                occurredAt = eventId.value.toInstant(),
                completedAt = completedAt,
            )
        }
    }
}
