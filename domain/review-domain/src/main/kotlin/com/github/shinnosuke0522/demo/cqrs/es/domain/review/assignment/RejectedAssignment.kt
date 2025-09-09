package com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment

import com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.event.AssignmentRejectedEvent
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.committee.ProgramCommitteeId
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.proposal.ProposalId
import java.time.Instant

data class RejectedAssignment(
    override val id: ReviewAssignmentId,
    override val proposalId: ProposalId,
    override val reviewerPcId: ProgramCommitteeId,
    override val dueAt: Instant,
    override val assignedAt: Instant,
    val rejectedAt: Instant,
    val reason: String,
) : ReviewAssignment(id, proposalId, reviewerPcId, dueAt, assignedAt) {
    companion object {
        fun of(
            approved: ApprovedAssignment,
            reason: String,
            rejectedAt: Instant = Instant.now(),
        ): RejectedAssignment = RejectedAssignment(
            id = approved.id,
            proposalId = approved.proposalId,
            reviewerPcId = approved.reviewerPcId,
            dueAt = approved.dueAt,
            assignedAt = approved.assignedAt,
            rejectedAt = rejectedAt,
            reason = reason,
        ).apply {
            this.addDomainEvent(
                AssignmentRejectedEvent.of(
                    aggregateId = this.id,
                    rejectedAt = this.rejectedAt,
                )
            )
        }
    }
}
