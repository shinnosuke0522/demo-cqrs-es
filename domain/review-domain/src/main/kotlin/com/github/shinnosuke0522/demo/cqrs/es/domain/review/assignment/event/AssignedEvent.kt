package com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.event

import com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.ReviewAssignmentId
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.committee.ProgramCommitteeId
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.proposal.ProposalId
import java.time.Instant

@ConsistentCopyVisibility
data class AssignedEvent private constructor(
    override val id: AssignmentEventId,
    override val aggregateId: ReviewAssignmentId,
    override val occurredAt: Instant,
    val proposalId: ProposalId,
    val reviewerPcId: ProgramCommitteeId,
    val dueAt: Instant,
    val assignedAt: Instant,
) : AssignmentEvent {
    companion object {
        fun of(
            aggregateId: ReviewAssignmentId,
            proposalId: ProposalId,
            reviewerPcId: ProgramCommitteeId,
            dueAt: Instant,
            assignedAt: Instant,
        ): AssignedEvent = run {
            val eventId = AssignmentEventId.generate()
            AssignedEvent(
                id = eventId,
                aggregateId = aggregateId,
                occurredAt = eventId.value.toInstant(),
                proposalId = proposalId,
                reviewerPcId = reviewerPcId,
                dueAt = dueAt,
                assignedAt = assignedAt,
            )
        }
    }
}
