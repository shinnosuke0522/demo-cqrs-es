package com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment

import com.github.shinnosuke0522.demo.cqrs.es.domain.review.assignment.event.AssignmentApprovedEvent
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.committee.ProgramCommitteeId
import com.github.shinnosuke0522.demo.cqrs.es.domain.review.proposal.ProposalId
import java.time.Instant

data class ApprovedAssignment(
    override val id: ReviewAssignmentId,
    override val proposalId: ProposalId,
    override val reviewerPcId: ProgramCommitteeId,
    override val dueAt: Instant,
    override val assignedAt: Instant,
    val approvedAt: Instant,
) : ReviewAssignment(id, proposalId, reviewerPcId, dueAt, assignedAt) {
    companion object {
        fun of(
            awaiting: AwaitingAssignment,
            approvedAt: Instant = Instant.now(),
        ): ApprovedAssignment = ApprovedAssignment(
            id = awaiting.id,
            proposalId = awaiting.proposalId,
            reviewerPcId = awaiting.reviewerPcId,
            dueAt = awaiting.dueAt,
            assignedAt = awaiting.assignedAt,
            approvedAt = approvedAt,
        ).apply {
            this.addDomainEvent(
                AssignmentApprovedEvent.of(
                    aggregateId = this.id,
                    approvedAt = this.approvedAt,
                )
            )
        }
    }
}
