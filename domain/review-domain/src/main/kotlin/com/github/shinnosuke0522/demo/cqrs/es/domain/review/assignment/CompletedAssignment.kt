package com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment

import com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.event.AssignmentCompletedEvent
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.committee.ProgramCommitteeId
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.proposal.ProposalId
import java.time.Instant

data class CompletedAssignment(
    override val id: ReviewAssignmentId,
    override val proposalId: ProposalId,
    override val reviewerPcId: ProgramCommitteeId,
    override val dueAt: Instant,
    override val assignedAt: Instant,
    val approvedAt: Instant,
    val completedAt: Instant,
) : ReviewAssignment(id, proposalId, reviewerPcId, dueAt, assignedAt) {
    companion object {
        fun of(
            approved: ApprovedAssignment,
            completedAt: Instant = Instant.now(),
        ): CompletedAssignment = CompletedAssignment(
            id = approved.id,
            proposalId = approved.proposalId,
            reviewerPcId = approved.reviewerPcId,
            dueAt = approved.dueAt,
            assignedAt = approved.assignedAt,
            approvedAt = approved.approvedAt,
            completedAt = completedAt,
        ).apply {
            this.addDomainEvent(
                AssignmentCompletedEvent.of(
                    aggregateId = this.id,
                    completedAt = this.completedAt,
                )
            )
        }
    }
}
