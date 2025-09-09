package com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment

import com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.event.AssignedEvent
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.committee.ProgramCommitteeId
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.proposal.ProposalId
import java.time.Instant

data class AwaitingAssignment(
    override val id: ReviewAssignmentId,
    override val proposalId: ProposalId,
    override val reviewerPcId: ProgramCommitteeId,
    override val dueAt: Instant,
    override val assignedAt: Instant,
) : ReviewAssignment(id, proposalId, reviewerPcId, dueAt, assignedAt) {
    companion object {
        fun of(
            proposalId: ProposalId,
            reviewerPcId: ProgramCommitteeId,
            dueAt: Instant,
        ): AwaitingAssignment = AwaitingAssignment(
            id = ReviewAssignmentId.generate(),
            proposalId = proposalId,
            reviewerPcId = reviewerPcId,
            dueAt = dueAt,
            assignedAt = Instant.now(),
        ).apply {
            this.addDomainEvent(
                AssignedEvent.of(
                    aggregateId = this.id,
                    proposalId = this.proposalId,
                    reviewerPcId = this.reviewerPcId,
                    dueAt = this.dueAt,
                    assignedAt = this.assignedAt
                )
            )
        }
    }
}
